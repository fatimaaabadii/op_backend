package ma.entraide.ramadan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.ramadan.entity.*;
import ma.entraide.ramadan.repository.*;

import java.util.*;

@Service
public class DelegationService {
    
	@Autowired
    private DelegationRepository delegationRepository;

    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }

    public List<Delegation> getDelegationsByRegion(Long regionId) {
        return delegationRepository.findByRegionRegionId(regionId);
    }

    public Optional<Delegation> getDelegationById(Long id) {
        return delegationRepository.findById(id);
    }

    public Delegation createDelegation(Delegation delegation) {
        return delegationRepository.save(delegation);
    }

    public Delegation updateDelegation(Long id, Delegation delegationDetails) {
        return delegationRepository.findById(id).map(delegation -> {
            delegation.setNomDelegation(delegationDetails.getNomDelegation());
            delegation.setRegion(delegationDetails.getRegion());
            return delegationRepository.save(delegation);
        }).orElseThrow(() -> new RuntimeException("Delegation not found with id: " + id));
    }

    public void deleteDelegation(Long id) {
        delegationRepository.deleteById(id);
    }
}