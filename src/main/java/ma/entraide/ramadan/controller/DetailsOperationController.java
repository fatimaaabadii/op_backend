package ma.entraide.ramadan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ma.entraide.ramadan.entity.DetailsOperation;
import ma.entraide.ramadan.service.DetailsOperationService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/details")
public class DetailsOperationController {

    @Autowired
    private DetailsOperationService detailsOperationService;

    @GetMapping("/operation/{operationId}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<DetailsOperation> getDetailsByOperation(@PathVariable Long operationId) {
        return detailsOperationService.getDetailsByOperationId(operationId);
    }

    @GetMapping("/produit/{produitId}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<DetailsOperation> getDetailsByProduit(@PathVariable Long produitId) {
        return detailsOperationService.getDetailsByProduitId(produitId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public DetailsOperation createDetails(@RequestBody DetailsOperation details) {
        return detailsOperationService.createDetails(details);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public void deleteDetails(@PathVariable Long id) {
        detailsOperationService.deleteDetails(id);
    }
}
