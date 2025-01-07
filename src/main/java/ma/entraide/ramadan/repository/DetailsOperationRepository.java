package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.DetailsOperation;
import ma.entraide.ramadan.entity.OperationRamadan;

import java.util.List;

@Repository
public interface DetailsOperationRepository extends JpaRepository<DetailsOperation, Long> {
    // Récupérer les détails d'une opération spécifique
    List<DetailsOperation> findByOperationOperationId(Long operationId);

    // Récupérer les détails pour un produit donné
    List<DetailsOperation> findByProduitProduitId(Long produitId);
    
    
    
}
