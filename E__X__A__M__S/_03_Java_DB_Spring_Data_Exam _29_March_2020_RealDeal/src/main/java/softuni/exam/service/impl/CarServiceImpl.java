package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarImportDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class CarServiceImpl implements CarService {

    private static final String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validator;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validator) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        String json = this.readCarsFileContent();

        CarImportDTO[] importDTOs = this.gson.fromJson(json, CarImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(CarImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Car";
        }
        //The combination of make, model and kilometers makes a car unique.
        Optional<Car> optCar = this.carRepository.findByMakeAndModelAndKilometers(dto.getMake(), dto.getModel(), dto.getKilometers());


        if (optCar.isPresent()) {
            return "Invalid Car";
        }

        Car car = this.modelMapper.map(dto, Car.class);
        this.carRepository.save(car);
        return String.format("Successfully imported Car %s - %s", car.getMake(), car.getModel());
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        //Export cars order by pictures count in descending order, then by make
        //Pictures Count in Descending Order:
        // It first compares the pictures count of two cars (car1 and car2).

        // If the pictures count of car2 is greater than the pictures count of car1, it returns 1 (indicating that car2 should come after car1).
        // If the pictures count of car1 is greater than the pictures count of car2, it returns -1 (indicating that car1 should come after car2).
        List<Car> cars = this.carRepository.findAll();

        cars.sort((car1, car2) -> {
            int picturesCount1 = car1.getPictures().size();
            int picturesCount2 = car2.getPictures().size();

            if (picturesCount2 > picturesCount1) {
                return 1;
            }
            if (picturesCount1 > picturesCount2) {
                return -1;
            }
            return car1.getMake().compareTo(car2.getMake());
        });
        // If the pictures count of both cars is equal (or if the ordering based on pictures count has been determined),
        // it then compares the make of the two cars using the compareTo method.

        return cars.stream()
                .map(Car::toString)
                .collect(Collectors.joining("\n"));


    }
}
