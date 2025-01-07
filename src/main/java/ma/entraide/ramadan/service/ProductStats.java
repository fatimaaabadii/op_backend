package ma.entraide.ramadan.service;

public class ProductStats {
    private String nomProduit;  // Ajouter un attribut pour le nom du produit
    private Double quantiteActuelle;
    private Integer quantiteMax;
    private Double pourcentage;

    public ProductStats(String nomProduit, Double quantiteActuelle, Integer quantiteMax, Double pourcentage) {
        this.nomProduit = nomProduit;
        this.quantiteActuelle = quantiteActuelle;
        this.quantiteMax = quantiteMax;
        this.pourcentage = pourcentage;
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
}
