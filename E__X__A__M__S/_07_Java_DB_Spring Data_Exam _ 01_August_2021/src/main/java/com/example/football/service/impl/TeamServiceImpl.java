package com.example.football.service.impl;

import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importTeams() {
        return null;
    }
}
