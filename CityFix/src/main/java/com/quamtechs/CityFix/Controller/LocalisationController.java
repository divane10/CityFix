package com.quamtechs.CityFix.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quamtechs.CityFix.Entity.Localisation;
import com.quamtechs.CityFix.Service.LocalisationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/localisations")
@RequiredArgsConstructor
public class LocalisationController {
    
    private final LocalisationService localisationService;

    // Récuperer toutes les localisations
    @GetMapping("/recupererLocalisation")
    public List<Localisation> getToutes() {
        return localisationService.getToutes();
    }

    // Récuperer une localisation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Localisation> getParId(@PathVariable Long id) {
        return localisationService.getParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // creer une nouvelle localisation 
@PostMapping("/creer")
    public ResponseEntity<Localisation> creer(@RequestBody Localisation localisation) {
        try {
            System.out.println("Creating localisation: lat=" + localisation.getLattitude() + 
                             ", lng=" + localisation.getLongitude() + 
                             ", addr=" + localisation.getAdresse());
            Localisation saved = localisationService.enregistrer(localisation);
            System.out.println("Localisation saved with ID: " + saved.getId());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.err.println("ERROR creating localisation: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}