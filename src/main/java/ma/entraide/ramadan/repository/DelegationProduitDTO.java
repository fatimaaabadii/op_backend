package ma.entraide.ramadan.repository;



public class DelegationProduitDTO {

    private Long delegationId;
    private String delegationNom;
    private Long produitId;
    private String produitNom;
    private Double quantite;

    // Constructeur pour la première requête : avec id de délégation et produit
    public DelegationProduitDTO(Long delegationId, Long produitId, Double quantite) {
        this.delegationId = delegationId;
        this.produitId = produitId;
        this.quantite = quantite;
    }

    // Constructeur pour la deuxième requête : avec noms de délégation et produit
    public DelegationProduitDTO(String delegationNom, String produitNom, Double quantite) {
        this.delegationNom = delegationNom;
        this.produitNom = produitNom;
        this.quantite = quantite;
    }

    // Getters et Setters
    public Long getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Long delegationId) {
        this.delegationId = delegationId;
    }

    public String getDelegationNom() {
        return delegationNom;
    }

    public void setDelegationNom(String delegationNom) {
        this.delegationNom = delegationNom;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getProduitNom() {
        return produitNom;
    }

    public void setProduitNom(String produitNom) {
        this.produitNom = produitNom;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
}
