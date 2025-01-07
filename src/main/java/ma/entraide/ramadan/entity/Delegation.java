package ma.entraide.ramadan.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.entraide.ramadan.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Delegation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long delegationId;

    @Column(name = "nom_delegation", nullable = false, length = 100)
    private String nomDelegation;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    @JsonBackReference
    private Region region;

    /*@OneToMany(mappedBy = "delegation", cascade = CascadeType.ALL)
    // @JsonManagedReference
    private List<OperationRamadan> operations;*/

    // Getters and Setters
    public Long getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Long delegationId) {
        this.delegationId = delegationId;
    }

    public String getNomDelegation() {
        return nomDelegation;
    }

    public void setNomDelegation(String nomDelegation) {
        this.nomDelegation = nomDelegation;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
/*
    public List<OperationRamadan> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationRamadan> operations) {
        this.operations = operations;
    }*/
}