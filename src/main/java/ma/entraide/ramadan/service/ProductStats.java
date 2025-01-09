package ma.entraide.ramadan.service;

public class ProductStats {
    private String nomProduit;  // Nom du produit
    private Double quantiteActuelle;  // Quantité actuelle en opérations
    private Integer quantiteMax;  // Quantité maximale en stock
    private Double pourcentage;  // Pourcentage entre quantités
    private Double totalOperationQuantite;  // Total des quantités en opérations
    private Double totalStockQuantite;  // Total des quantités en stock

    // Constructeur principal
    public ProductStats(String nomProduit, Double quantiteActuelle, Integer quantiteMax, Double pourcentage) {
        this.nomProduit = nomProduit;
        this.quantiteActuelle = quantiteActuelle;
        this.quantiteMax = quantiteMax;
        this.pourcentage = pourcentage;
    }

    // Constructeur avec tous les champs
    public ProductStats(String nomProduit, Double quantiteActuelle, Integer quantiteMax, Double pourcentage,
                        Double totalOperationQuantite, Double totalStockQuantite) {
        this.nomProduit = nomProduit;
        this.quantiteActuelle = quantiteActuelle;
        this.quantiteMax = quantiteMax;
        this.pourcentage = pourcentage;
        this.totalOperationQuantite = totalOperationQuantite;
        this.totalStockQuantite = totalStockQuantite;
    }

    // Getters et Setters
    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Double getQuantiteActuelle() {
        return quantiteActuelle;
    }

    public void setQuantiteActuelle(Double quantiteActuelle) {
        this.quantiteActuelle = quantiteActuelle;
    }

    public Integer getQuantiteMax() {
        return quantiteMax;
    }

    public void setQuantiteMax(Integer quantiteMax) {
        this.quantiteMax = quantiteMax;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Double getTotalOperationQuantite() {
        return totalOperationQuantite;
    }

    public void setTotalOperationQuantite(Double totalOperationQuantite) {
        this.totalOperationQuantite = totalOperationQuantite;
    }

    public Double getTotalStockQuantite() {
        return totalStockQuantite;
    }

    public void setTotalStockQuantite(Double totalStockQuantite) {
        this.totalStockQuantite = totalStockQuantite;
    }

    @Override
    public String toString() {
        return "ProductStats{" +
                "nomProduit='" + nomProduit + '\'' +
                ", quantiteActuelle=" + quantiteActuelle +
                ", quantiteMax=" + quantiteMax +
                ", pourcentage=" + pourcentage +
                ", totalOperationQuantite=" + totalOperationQuantite +
                ", totalStockQuantite=" + totalStockQuantite +
                '}';
    }
}
