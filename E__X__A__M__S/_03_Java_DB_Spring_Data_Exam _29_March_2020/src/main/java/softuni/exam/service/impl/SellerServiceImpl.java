package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportSellerDTO;
import softuni.exam.models.dto.ImportSellerRootDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParserImpl xmlParser;
    private final ValidationUtilImpl validator;

    public SellerServiceImpl(SellerRepository sellerRepository,
                             ModelMapper modelMapper,
                             XmlParserImpl xmlParser,
                             ValidationUtilImpl validator) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        ImportSellerRootDTO sellersRootDTOs = this.xmlParser.fromFile(SELLERS_FILE_PATH, ImportSellerRootDTO.class);
        return sellersRootDTOs.getSellers().stream().map(this::importSeller).collect(Collectors.joining("\n"));
    }

    private String importSeller(ImportSellerDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Seller";
        }

        Optional<Seller> optSeller = this.sellerRepository.findByEmail(dto.getEmail());


        if (optSeller.isPresent()) {
            return "Invalid Seller";
        }
        Seller seller = this.modelMapper.map(dto, Seller.class);

        this.sellerRepository.save(seller);
        return String.format("Successfully imported Seller %s - %s", dto.getLastName(), dto.getEmail());

    }

}
