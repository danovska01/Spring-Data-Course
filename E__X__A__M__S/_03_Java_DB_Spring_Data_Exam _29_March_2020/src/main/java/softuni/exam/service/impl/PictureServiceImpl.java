package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureImportDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validator;

    public PictureServiceImpl(PictureRepository pictureRepository,
                              CarRepository carRepository,
                              ModelMapper modelMapper, Gson gson,
                              ValidationUtilImpl validator) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readPicturesFromFile();

        PictureImportDTO[] importDTOs = this.gson.fromJson(json, PictureImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PictureImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Picture";
        }
        //The name of a picture is unique.
        Optional<Picture> optPicture = this.pictureRepository.findByName(dto.getName());


        if (optPicture.isPresent()) {
            return "Invalid Picture";
        }
        Picture picture = this.modelMapper.map(dto, Picture.class);
        Optional<Car> car = this.carRepository.findById(dto.getCar());
        picture.setCar(car.get());
        this.pictureRepository.save(picture);


        return String.format("Successfully imported Picture %s", picture.getName());
    }
}
