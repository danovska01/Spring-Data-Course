package exam.repository;


import exam.model.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//ToDo:
@Repository
public interface TownRepository extends JpaRepository<Town, Long> {


    Town getTownByName(String name);

    Optional<Town> findByName(String name);
}
