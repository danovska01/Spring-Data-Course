package exam.service.impl;

import exam.model.dtos.ShopImportDTO;
import exam.model.dtos.ShopImportRootDTO;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;
    private final Path xmlPath =
            Path.of("src", "main", "resources", "files", "xml", "shops.xml");

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) throws JAXBException {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;

        JAXBContext context = JAXBContext.newInstance(ShopImportRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(xmlPath);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ShopImportRootDTO shopDTOs = (ShopImportRootDTO) this.unmarshaller.unmarshal(
                new FileReader(xmlPath.toAbsolutePath().toString()));

        return shopDTOs
                .getShops()
                .stream()
                .map(this::importShop)
                .collect(Collectors.joining("\n"));

    }

    private String importShop(ShopImportDTO dto) {
        Set<ConstraintViolation<ShopImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid Shop";
        }

        Optional<Shop> optShop = this.shopRepository.findByName(dto.getName());

        if (optShop.isPresent()) {
            return "Invalid Shop";
        }


        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());


        Shop shop = this.modelMapper.map(dto, Shop.class);

        shop.setTown(town.get());

        this.shopRepository.save(shop);

        return "Successfully imported Shop " + shop.getName() + " " +
                shop.getIncome();

    }
}
