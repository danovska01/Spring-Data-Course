package hiberspring.service.serviceImpl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.TownImportDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    // SHOULD IMPORT "static hiberspring.common.Constants.*;" IN ORDER NOT TO READ THE CLASS NAME FIRST
    private static final String TOWNS_FILE_PATH = Constants.PATH_TO_FILES + "towns.json";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtilImpl validator, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        String json = this.readTownsJsonFile();

        TownImportDTO[] importDTOs = this.gson.fromJson(json, TownImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(TownImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        //In case of ERROR you always print “Error: Invalid data.”.
        if (!isValid) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Optional<Town> optTown = this.townRepository.findByName(dto.getName());


        if (optTown.isPresent()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }
        Town town = this.modelMapper.map(dto, Town.class);


        this.townRepository.save(town);

        //SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";
        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, town.getClass().getSimpleName(), town.getName());
    }
}
