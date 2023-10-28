package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportOfferDTO;
import softuni.exam.models.dto.ImportOfferRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;
    private final CarRepository carRepository;

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParserImpl xmlParser;
    private final ValidationUtilImpl validator;

    public OfferServiceImpl(OfferRepository offerRepository,
                            CarRepository carRepository,
                            SellerRepository sellerRepository,
                            ModelMapper modelMapper,
                            XmlParserImpl xmlParser,
                            ValidationUtilImpl validator) {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportOfferRootDTO offersRootDTOs = this.xmlParser.fromFile(OFFERS_FILE_PATH, ImportOfferRootDTO.class);
        return offersRootDTOs.getOffers().stream().map(this::importOffer).collect(Collectors.joining("\n"));
    }

    private String importOffer(ImportOfferDTO dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Offer";
        }

        Optional<Offer> optOffer = this.offerRepository.findByAddedOnAndDescription(dto.getAddedOn(), dto.getDescription());


        if (optOffer.isPresent()) {
            return "Invalid Offer";
        }

        Optional<Car> car = this.carRepository.findById(dto.getCar().getId());
        Optional<Seller> seller = this.sellerRepository.findById(dto.getSeller().getId());

        Offer offer = this.modelMapper.map(dto, Offer.class);

        offer.setCar(car.get());
        offer.setSeller(seller.get());

        this.offerRepository.save(offer);

        return String.format("Successfully imported Offer %s - %b", dto.getAddedOn(), dto.isHasGoldStatus());
    }
}
