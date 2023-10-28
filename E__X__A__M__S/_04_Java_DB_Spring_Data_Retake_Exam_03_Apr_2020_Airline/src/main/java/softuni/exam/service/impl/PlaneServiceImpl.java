package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPlaneDTO;
import softuni.exam.models.dto.ImportPlaneRootDTO;
import softuni.exam.models.entity.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl implements PlaneService {
    private static final String PLANE_FILE_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;

    private final ModelMapper modelMapper;
    private final XmlParserImpl xmlParser;
    private final ValidationUtilImpl validator;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParserImpl xmlParser, ValidationUtilImpl validator) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANE_FILE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException {
        ImportPlaneRootDTO planesRootDTOs = this.xmlParser.fromFile(PLANE_FILE_PATH, ImportPlaneRootDTO.class);
        return planesRootDTOs.getPlanes().stream().map(this::importPlane).collect(Collectors.joining("\n"));

    }

    private String importPlane(ImportPlaneDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Plane";
        }

        Optional<Plane> optPlane = this.planeRepository.findByRegisterNumber(dto.getRegisterNumber());


        if (optPlane.isPresent()) {
            return "Invalid Plane";
        }


        Plane plane = this.modelMapper.map(dto, Plane.class);


        this.planeRepository.save(plane);

        return String.format("Successfully imported Plane %s", dto.getRegisterNumber());
    }
}
