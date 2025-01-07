package ma.entraide.ramadan.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.ramadan.entity.*;
import ma.entraide.ramadan.repository.*;

import java.util.*;


@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region updateRegion(Long id, Region regionDetails) {
        return regionRepository.findById(id).map(region -> {
            region.setNomRegion(regionDetails.getNomRegion());
            return regionRepository.save(region);
        }).orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
    }

    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }
}
