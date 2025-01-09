package ma.entraide.ramadan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.ramadan.entity.Delegation;
import ma.entraide.ramadan.entity.DetailsOperation;
import ma.entraide.ramadan.entity.OperationRamadan;
import ma.entraide.ramadan.entity.Produit;
import ma.entraide.ramadan.entity.Stock;
import ma.entraide.ramadan.repository.DetailsOperationRepository;
import ma.entraide.ramadan.repository.OperationRamadanRepository;
import ma.entraide.ramadan.repository.ProduitRepository;
import ma.entraide.ramadan.repository.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OperationRamadanService {

    @Autowired
    private OperationRamadanRepository operationRamadanRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    
    @Autowired
    private ProduitRepository produitRepository;
    
    
    @Autowired
    private DetailsOperationRepository detailsOperationRepository;
    
   
    
    
    
   
    
    public Map<String, Map<Long, List<ProductStats>>> calculerPourcentagesParDelegation() {
        // Récupérer tous les détails des opérations
        List<DetailsOperation> detailsOperations = detailsOperationRepository.findAll();

        // Structure pour stocker les résultats : { "nomDelegation" -> { produitId -> List<ProductStats> } }
        Map<String, Map<Long, List<ProductStats>>> pourcentagesParDelegation = new HashMap<>();

        // Structure pour accumuler les quantités par délégation et produit
        Map<String, Map<Long, Double>> quantitesParDelegationProduit = new HashMap<>();

        // Parcours des détails des opérations
        for (DetailsOperation detail : detailsOperations) {
            // Récupérer la délégation et le nom
            Delegation delegation = detail.getOperation().getDelegation();
            String nomDelegation = delegation.getNomDelegation();

            // Récupérer le produit et les informations associées
            Produit produit = detail.getProduit();
            Long produitId = produit.getProduitId();
            Double quantiteActuelle = detail.getQuantite();

            // Accumuler les quantités par produit et délégation
            quantitesParDelegationProduit
                .computeIfAbsent(nomDelegation, k -> new HashMap<>())
                .merge(produitId, quantiteActuelle, Double::sum);
        }

        // Récupérer tous les stocks pour vérifier les quantités max par délégation et produit
        List<Stock> stocks = stockRepository.findAll();

        // Parcourir les délégations et les produits pour calculer les pourcentages
        for (Stock stock : stocks) {
            Delegation delegation = stock.getDelegation(); // Supposons que `Stock` contient une référence à `Delegation`
            String nomDelegation = delegation.getNomDelegation();
            Produit produit = stock.getProduit();
            Long produitId = produit.getProduitId();
            Integer quantiteMax = stock.getQuantiteMax();
            String nomProduit = produit.getNomProduit();

            // Vérifier la quantité actuelle accumulée pour cette délégation et ce produit
            Double quantiteTotaleActuelle = quantitesParDelegationProduit
                .getOrDefault(nomDelegation, new HashMap<>())
                .getOrDefault(produitId, 0.0);

            // Calculer le pourcentage
            Double pourcentage;
            if (quantiteTotaleActuelle == null || quantiteTotaleActuelle == 0) {
                pourcentage = 0.0; // Pas de quantité enregistrée
            } else {
                pourcentage = (quantiteTotaleActuelle / quantiteMax) * 100;
            }

            // Créer un objet ProductStats
            ProductStats productStats = new ProductStats(nomProduit, quantiteTotaleActuelle, quantiteMax, pourcentage);

            // Ajouter dans la map par délégation et produit
            pourcentagesParDelegation
                .computeIfAbsent(nomDelegation, k -> new HashMap<>())
                .computeIfAbsent(produitId, k -> new ArrayList<>())
                .add(productStats);
        }

        return pourcentagesParDelegation;
    }

    
    
    public List<ProductStats> calculerTotalEtPourcentagePourTousLesProduits() {
    	List<ProductStats> resultats = new ArrayList<>();

        // Récupérer tous les détails des opérations
        List<DetailsOperation> detailsOperations = detailsOperationRepository.findAll();

        // Structure pour accumuler les quantités des opérations par produit
        Map<Long, Double> quantitesOperations = new HashMap<>();

        // Parcours des détails des opérations pour accumuler les quantités
        for (DetailsOperation detail : detailsOperations) {
            Long produitId = detail.getProduit().getProduitId();
            Double quantiteActuelle = detail.getQuantite();

            // Accumuler les quantités des opérations par produit
            quantitesOperations.merge(produitId, quantiteActuelle, Double::sum);
        }

        // Récupérer tous les stocks
        List<Stock> stocks = stockRepository.findAll();

        // Structure pour accumuler les quantités maximales de stock par produit
        Map<Long, Double> quantitesMaxStock = new HashMap<>();

        // Parcourir les stocks pour additionner les quantités maximales de stock par produit
        for (Stock stock : stocks) {
            Long produitId = stock.getProduit().getProduitId(); // Produit lié au stock
            Double quantiteMax = stock.getQuantiteMax().doubleValue(); // Convertir en Double pour une addition plus facile

            // Additionner les quantités maximales pour chaque produit
            quantitesMaxStock.merge(produitId, quantiteMax, Double::sum);
        }

        // Pour chaque produit, calculer le total des opérations, le total des stocks et le pourcentage
        for (Long produitId : quantitesMaxStock.keySet()) {
            // Quantité maximale totale du stock pour ce produit
            Double quantiteMax = quantitesMaxStock.get(produitId);
            // Quantité totale des opérations pour ce produit
            Double quantiteTotaleOperation = quantitesOperations.getOrDefault(produitId, 0.0);

            // Calculer le pourcentage
            Double pourcentage = 0.0;
            if (quantiteMax > 0) {
                pourcentage = (quantiteTotaleOperation / quantiteMax) * 100;
            }

            // Récupérer le produit lié à ce produitId (directement depuis les stocks)
            Produit produit = null;
            for (Stock stock : stocks) {
                if (stock.getProduit().getProduitId().equals(produitId)) {
                    produit = stock.getProduit();
                    break;
                }
            }

            // Créer un objet ProductStats avec le total des opérations, le total des stocks et le pourcentage
            ProductStats productStats = new ProductStats(
                produit != null ? produit.getNomProduit() : "Produit Inconnu", // Nom du produit
                quantiteTotaleOperation, // Total des opérations
                quantiteMax.intValue(), // Total des stocks (somme des quantités maximales)
                pourcentage // Pourcentage
            );

            // Ajouter à la liste des résultats
            resultats.add(productStats);
        }

        return resultats;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    

    public List<OperationRamadan> getAllOperations() {
        return operationRamadanRepository.findAll();
    }

    public List<OperationRamadan> getOperationsByDelegation(Long delegationId) {
        return operationRamadanRepository.findByDelegationDelegationId(delegationId);
    }

    public Optional<OperationRamadan> getOperationById(Long id) {
        return operationRamadanRepository.findById(id);
    }

    public OperationRamadan createOperation(OperationRamadan operation) {
        if (operation.getDetails() != null) {
            for (DetailsOperation detail : operation.getDetails()) {
                detail.setOperation(operation); // Synchroniser la relation
            }
        }
        return operationRamadanRepository.save(operation);
    }

    public void deleteOperation(Long id) {
        operationRamadanRepository.deleteById(id);
    }
}
