package softuni.exam.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.dtos.ImportPictureDTO;
import softuni.exam.dtos.ImportPictureRootDTO;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/xml/pictures.xml";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtilImpl validator;
    private final FileUtil fileUtil;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository,
                              ModelMapper modelMapper,
                              XmlParser xmlParser,
                              ValidationUtilImpl validator,
                              FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() {
        try {
            return fileUtil.readFile(PICTURES_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Error reading XML file: " + PICTURES_FILE_PATH, e);
        }
    }

    @Override
    public String importPictures() throws JAXBException {
        ImportPictureRootDTO picturesRootDTOs = this.xmlParser.fromFile(PICTURES_FILE_PATH, ImportPictureRootDTO.class);
        return picturesRootDTOs.getPictures().stream().map(this::importOffer).collect(Collectors.joining("\n"));
    }

    private String importOffer(ImportPictureDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Picture";
        }

        Optional<Picture> optPicture = this.pictureRepository.findByUrl(dto.getUrl());


        if (optPicture.isPresent()) {
            return "Invalid Picture";
        }
        Picture picture = this.modelMapper.map(dto, Picture.class);
        this.pictureRepository.save(picture);

        return String.format("Successfully imported Picture - %s", dto.getUrl());
    }


}
