package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.*;

@Repository
public interface LogsConnexionRepo extends JpaRepository<LogsConnexion, Long> {



}
