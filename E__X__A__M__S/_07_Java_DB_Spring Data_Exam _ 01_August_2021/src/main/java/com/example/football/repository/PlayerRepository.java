package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

//ToDo:
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
