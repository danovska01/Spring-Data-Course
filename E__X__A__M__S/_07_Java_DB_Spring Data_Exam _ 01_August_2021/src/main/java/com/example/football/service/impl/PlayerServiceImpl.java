package com.example.football.service.impl;

import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "players.xml");
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importPlayers() {
        return null;
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
