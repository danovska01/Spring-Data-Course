package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Town_Import_DTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();

//        modelMapper.addConverter(new Converter<String, LocalDate>() {
//            @Override
//            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
//                return LocalDate.parse(mappingContext.getSource(),
//                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//            }
//        });

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        Town_Import_DTO[] importDTOs = this.gson.fromJson(json, Town_Import_DTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }


    private String importDTO(Town_Import_DTO dto) {

        if (!isValid(dto)) {
            return "Invalid Town";
        }

        Optional<Town> optTown = this.townRepository.findByName(dto.getName());


        if (optTown.isPresent()) {
            return "Invalid Town";
        }

        Town town = this.modelMapper.map(dto, Town.class);

        this.townRepository.save(town);

        return "Successfully imported Town " + town.getName() + " " + town.getPopulation();
    }

}
