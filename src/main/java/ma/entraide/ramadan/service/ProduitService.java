package ma.entraide.ramadan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.ramadan.entity.Produit;
import ma.entraide.ramadan.repository.ProduitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produitDetails) {
        return produitRepository.findById(id).map(produit -> {
            produit.setNomProduit(produitDetails.getNomProduit());
            produit.setUniteMesure(produitDetails.getUniteMesure());
            return produitRepository.save(produit);
        }).orElseThrow(() -> new RuntimeException("Produit not found with id: " + id));
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
