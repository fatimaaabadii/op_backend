package ma.entraide.ramadan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.ramadan.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    // Méthode pour trouver un produit par son nom
    Produit findByNomProduit(String nomProduit);
}