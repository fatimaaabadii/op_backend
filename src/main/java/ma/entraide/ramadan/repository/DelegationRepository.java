package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.*;

import java.util.*;


@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {
    // Récupérer toutes les délégations appartenant à une région
    List<Delegation> findByRegionRegionId(Long regionId);
}