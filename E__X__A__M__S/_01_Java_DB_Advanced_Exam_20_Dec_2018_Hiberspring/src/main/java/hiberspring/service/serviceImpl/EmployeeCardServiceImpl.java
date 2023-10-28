package hiberspring.service.serviceImpl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.EmployeeCardsImportDTO;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
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
public class EmployeeCardServiceImpl implements EmployeeCardService {
    // SHOULD IMPORT "static hiberspring.common.Constants.*;" IN ORDER NOT TO READ THE CLASS NAME FIRST

    private static final String EMPLOYEE_CARDS_FILE_PATH = Constants.PATH_TO_FILES + "employee-cards.json";
    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;
    private final Gson gson;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, ValidationUtilImpl validator, Gson gson) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(Path.of(EMPLOYEE_CARDS_FILE_PATH));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        String json = this.readEmployeeCardsJsonFile();

        EmployeeCardsImportDTO[] importDTOs = this.gson.fromJson(json, EmployeeCardsImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(EmployeeCardsImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        //In case of ERROR you always print “Error: Invalid data.”.
        if (!isValid) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Optional<EmployeeCard> optCard = this.employeeCardRepository.findByNumber(dto.getNumber());


        if (optCard.isPresent()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        EmployeeCard employeeCard = this.modelMapper.map(dto, EmployeeCard.class);

        this.employeeCardRepository.save(employeeCard);

        //SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";
        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Employee Card", employeeCard.getNumber());
    }

}
