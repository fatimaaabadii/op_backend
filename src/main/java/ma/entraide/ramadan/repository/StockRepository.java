package ma.entraide.ramadan.repository;

import ma.entraide.ramadan.entity.OperationRamadan;
import ma.entraide.ramadan.entity.Stock;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Custom query examples (if needed):
	List<Stock> findByDelegationDelegationId(Long delegationId); // Navigation correcte pour Delegation
    List<Stock> findByProduitProduitId(Long produitId); 

	//Optional<OperationRamadan> findByProduitId(Long produitId);

}
