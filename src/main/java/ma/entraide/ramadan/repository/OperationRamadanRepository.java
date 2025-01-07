package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.DetailsOperation;
import ma.entraide.ramadan.entity.OperationRamadan;

import java.util.List;

@Repository
public interface OperationRamadanRepository extends JpaRepository<OperationRamadan, Long> {
    // Récupérer toutes les opérations pour une délégation donnée
    List<OperationRamadan> findByDelegationDelegationId(Long delegationId);

    // Récupérer toutes les opérations pour une région via la délégation
    List<OperationRamadan> findByDelegationRegionRegionId(Long regionId);
    
    
    
   /* @Query("SELECT new ma.entraide.ramadan.repository.DelegationProduitDTO(d.id, p.id, SUM(do.quantite)) " +
    	       "FROM OperationRamadan o " +
    	       "JOIN o.details do " +
    	       "JOIN do.produit p " +
    	       "JOIN o.delegation d " +
    	       "WHERE d.id = :delegationId " +
    	       "GROUP BY d.id, p.id")
    	List<DelegationProduitDTO> findQuantiteTotaleParDelegationEtProduit(
    	    @Param("delegationId") Long delegationId);


    	// Optionnel : Requête pour récupérer toutes les quantités par délégation et produit sans filtre
    
    @Query("SELECT new ma.entraide.ramadan.repository.DelegationProduitDTO(d.nom, p.nom, SUM(do.quantite)) " +
    	       "FROM Delegation d " +
    	       "JOIN d.operations o " +  // Assurez-vous que l'entité Delegation a une relation avec OperationRamadan
    	       "JOIN o.details do " +
    	       "JOIN do.produit p " +
    	       "GROUP BY d.nom, p.nom")
    	List<DelegationProduitDTO> findAllQuantitesTotalesParDelegationEtProduit();
*/
    	
    	


}