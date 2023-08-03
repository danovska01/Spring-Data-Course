package com.example.football.repository;

import com.example.football.models.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

//ToDo:
public interface TeamRepository extends JpaRepository<Team, Long> {
}
