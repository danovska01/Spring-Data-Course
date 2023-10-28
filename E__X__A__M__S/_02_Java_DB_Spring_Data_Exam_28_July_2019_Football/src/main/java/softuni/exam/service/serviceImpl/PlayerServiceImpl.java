package softuni.exam.service.serviceImpl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.dtos.PlayerImportDTO;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PlayerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/json/players.json";
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validator;
    private final FileUtil fileUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             PictureRepository pictureRepository,
                             ModelMapper modelMapper,
                             Gson gson,
                             ValidationUtilImpl validator,
                             FileUtil fileUtil) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() {
        try {
            return fileUtil.readFile(PLAYERS_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Error reading XML file: " + PLAYERS_FILE_PATH, e);
        }
    }

    @Override
    public String importPlayers() {
        String json = this.readPlayersJsonFile();

        PlayerImportDTO[] importDTOs = this.gson.fromJson(json, PlayerImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PlayerImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Player";
        }

        Optional<Player> optPlayer = this.playerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());


        if (optPlayer.isPresent()) {
            return "Invalid Player";
        }
        Player player = this.modelMapper.map(dto, Player.class);


        Optional<Picture> picture = this.pictureRepository.findByUrl(dto.getPicture().getUrl());
        Optional<Team> team = this.teamRepository.findByName(dto.getTeam().getName());

        player.setPicture(picture.get());
        player.setTeam(team.get());

        this.playerRepository.save(player);


        return String.format("Successfully imported Player %s %s", player.getFirstName(), player.getLastName());


    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {

        //Export players with salary bigger than 100000

        List<Player> players = playerRepository.findBySalaryGreaterThan(BigDecimal.valueOf(100000));

        StringBuilder sb = new StringBuilder();

        players
                .forEach(player -> {
                    String playerInfo = String.format("Player name: %s %s\n" +
                                    "Number: %d\n" +
                                    "Salary: %.2f\n" +
                                    "Team: %s\n", player.getFirstName(), player.getLastName(), player.getNumber(),
                            player.getSalary(), player.getTeam().getName());
                    sb.append(playerInfo);
                });
        // Removal of last line separator, if that is necessary
        // return (sb).substring(0, sb.toString().length()-1);
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {

        List<Player> players = playerRepository.findAllByTeamNameOrderById("North Hub");

        StringBuilder sb = new StringBuilder("Team: North Hub\n");

        String playersInfo = players.stream()
                .map(player -> {
                    return String.format("Player name: %s %s - %s\n" +
                            "Number: %d", player.getFirstName(), player.getLastName(), player.getPosition(), player.getNumber());
                })
                .collect(Collectors.joining("\n"));

        return (sb.append(playersInfo)).toString();
    }
}
