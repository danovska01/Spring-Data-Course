package hiberspring.service.serviceImpl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.BranchImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
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
public class BranchServiceImpl implements BranchService {

    // SHOULD IMPORT "static hiberspring.common.Constants.*;" IN ORDER NOT TO READ THE CLASS NAME FIRST

    private static final String BRANCHES_FILE_PATH = Constants.PATH_TO_FILES + "branches.json";
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validator;

    private final Gson gson;

    public BranchServiceImpl(BranchRepository branchRepository,
                             TownRepository townRepository,
                             ModelMapper modelMapper,
                             ValidationUtilImpl validator,
                             Gson gson) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }


    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(BRANCHES_FILE_PATH));
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        String json = this.readBranchesJsonFile();

        BranchImportDTO[] importDTOs = this.gson.fromJson(json, BranchImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(BranchImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        //In case of ERROR you always print “Error: Invalid data.”.
        if (!isValid) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Optional<Branch> optBranch = this.branchRepository.findByName(dto.getName());


        if (optBranch.isPresent()) {
            return Constants.INCORRECT_DATA_MESSAGE;
        }

        Branch branch = this.modelMapper.map(dto, Branch.class);
        Optional<Town> town = this.townRepository.findByName(dto.getTown());
        branch.setTown(town.get());


        this.branchRepository.save(branch);

        //SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s %s.";
        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, branch.getClass().getSimpleName(), branch.getName());
    }
}
