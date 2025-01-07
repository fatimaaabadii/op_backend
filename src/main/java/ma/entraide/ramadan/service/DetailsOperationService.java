package ma.entraide.ramadan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.ramadan.entity.DetailsOperation;
import ma.entraide.ramadan.repository.DetailsOperationRepository;

import java.util.List;

@Service
public class DetailsOperationService {

    @Autowired
    private DetailsOperationRepository detailsOperationRepository;

    public List<DetailsOperation> getDetailsByOperationId(Long operationId) {
        return detailsOperationRepository.findByOperationOperationId(operationId);
    }

    public List<DetailsOperation> getDetailsByProduitId(Long produitId) {
        return detailsOperationRepository.findByProduitProduitId(produitId);
    }

    public DetailsOperation createDetails(DetailsOperation details) {
        return detailsOperationRepository.save(details);
    }

    public void deleteDetails(Long id) {
        detailsOperationRepository.deleteById(id);
    }
}

