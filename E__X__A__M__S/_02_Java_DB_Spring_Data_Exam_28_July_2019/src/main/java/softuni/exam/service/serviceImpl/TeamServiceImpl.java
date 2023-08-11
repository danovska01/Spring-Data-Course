package softuni.exam.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.dtos.ImportTeamDTO;
import softuni.exam.dtos.ImportTeamRootDTO;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.TeamService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAMS_FILE_PATH = "src/main/resources/files/xml/teams.xml";
    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtilImpl validator;
    private final FileUtil fileUtil;

    public TeamServiceImpl(TeamRepository teamRepository,
                           PictureRepository pictureRepository,
                           ModelMapper modelMapper,
                           XmlParser xmlParser,
                           ValidationUtilImpl validator,
                           FileUtil fileUtil) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        try {
            return fileUtil.readFile(TEAMS_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Error reading XML file: " + TEAMS_FILE_PATH, e);
        }
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }


    @Override
    public String importTeams() throws JAXBException {
        ImportTeamRootDTO importTeamRootDTOs = this.xmlParser.fromFile(TEAMS_FILE_PATH, ImportTeamRootDTO.class);
        return importTeamRootDTOs.getTeams().stream().map(this::importTeam).collect(Collectors.joining("\n"));

    }

    private String importTeam(ImportTeamDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Team";
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());

        if (optTeam.isPresent()) {
            return "Invalid Team";
        }

        Optional<Picture> optPicture = this.pictureRepository.findByUrl(dto.getPicture().getUrl());

        Team team = this.modelMapper.map(dto, Team.class);

        if (optPicture.isPresent()) {
            Picture picture = optPicture.get();
            team.setPicture(picture);
        } else {
            // Create and save the Picture entity before setting it for the Team
            Picture newPicture = new Picture();
            newPicture.setUrl(dto.getPicture().getUrl());
            newPicture = this.pictureRepository.save(newPicture);

            team.setPicture(newPicture);
        }

        this.teamRepository.save(team);

        return String.format("Successfully imported Team - %s", dto.getName());
    }


}
