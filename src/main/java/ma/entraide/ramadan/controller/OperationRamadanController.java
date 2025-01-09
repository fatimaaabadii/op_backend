package ma.entraide.ramadan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ma.entraide.ramadan.entity.OperationRamadan;
import ma.entraide.ramadan.repository.DetailsOperationRepository;
import ma.entraide.ramadan.repository.StockRepository;
import ma.entraide.ramadan.service.DetailsOperationService;
import ma.entraide.ramadan.service.OperationRamadanService;
import ma.entraide.ramadan.service.ProductStats;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/operations")
public class OperationRamadanController {

    @Autowired
    private OperationRamadanService operationRamadanService;
    
    @Autowired
    private DetailsOperationService detailsOperationService;  
   

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<OperationRamadan> getAllOperations() {
        return operationRamadanService.getAllOperations();
    }

    @GetMapping("/delegation/{delegationId}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<OperationRamadan> getOperationsByDelegation(@PathVariable Long delegationId) {
        return operationRamadanService.getOperationsByDelegation(delegationId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public OperationRamadan getOperationById(@PathVariable Long id) {
        return operationRamadanService.getOperationById(id)
                .orElseThrow(() -> new RuntimeException("Operation not found with id: " + id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public OperationRamadan createOperation(@RequestBody OperationRamadan operation) {
        return operationRamadanService.createOperation(operation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public void deleteOperation(@PathVariable Long id) {
        operationRamadanService.deleteOperation(id);
    }
    
    
    @GetMapping("/totalproduits")
    public List<ProductStats> getTotalEtPourcentagePourTousLesProduits() {
        return operationRamadanService.calculerTotalEtPourcentagePourTousLesProduits();
    }
    
    
    
    
    
    @GetMapping("/pourcentages-par-delegation")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<?> getPourcentagesParDelegation() {
        try {
            // Appel au service de OperationRamadanService pour obtenir les pourcentages
            Map<String, Map<Long, List<ProductStats>>> pourcentages = operationRamadanService.calculerPourcentagesParDelegation();

            // Si aucun pourcentage n'est trouvé
            if (pourcentages.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Aucun pourcentage calculé pour les délégations.");
            }

            // Retourner les données avec un statut 200 OK
            return ResponseEntity.ok(pourcentages);
        } catch (IllegalArgumentException e) {
            // Gérer une erreur spécifique liée aux arguments invalides
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur dans les paramètres de la requête : " + e.getMessage());
        } catch (Exception e) {
            // Gérer une erreur générale
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors du calcul des pourcentages : " + e.getMessage());
        }
    }

}

