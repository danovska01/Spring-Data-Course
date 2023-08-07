package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Agent_Import_DTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {
    private static final String AGENTS_FILE_PATH = "src/main/resources/files/json/agents.json";
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;


    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {

        String json = this.readAgentsFromFile();


        Agent_Import_DTO[] importDTOs = gson.fromJson(json, Agent_Import_DTO[].class);


        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(Agent_Import_DTO dto) {
        if (!isValid(dto)) {
            return "Invalid Agent";
        }

        Optional<Agent> optAgent = this.agentRepository.findByEmail(dto.getEmail());

        if (optAgent.isPresent()) {
            return "Invalid Agent";
        }

        Optional<Agent> optAgentByFirstName = this.agentRepository.findByFirstName(dto.getFirstName());

        //!!!!! по условие не трябва да връща това, но правя тук валидация за повтарящи се имена, които в базата се изисква да са уникални

        if (optAgentByFirstName.isPresent()) {
            return "Duplicate Agent: " + dto.getFirstName();
        }

        Agent agent = this.modelMapper.map(dto, Agent.class);
        Town town = townRepository.getTownByName(dto.getTown());
        agent.setTown(town);

        this.agentRepository.save(agent);

        return "Successfully imported Agent " + agent.getFirstName() + " " + agent.getLastName();
    }


    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();

    }
}
