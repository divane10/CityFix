package com.quamtechs.CityFix.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quamtechs.CityFix.Entity.Localisation;
import com.quamtechs.CityFix.Service.LocalisationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LocalisationController {
    
    private final LocalisationService localisationService;
    // Récuperer toutes les localisation

    @GetMapping("/recupererLocalisation")
    public List<Localisation> getToutes() {
        return localisationService.getToutes();
    }

    //Récuperer une localisation par son ID

    @GetMapping("/{id}")
    public ResponseEntity<Localisation> getParId(@PathVariable Long id) {
        return localisationService.getParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // si pas trouvé
    }

    // creer une nouvelle localisation 

    @PostMapping("/creer")
    public ResponseEntity<Localisation> creer(@RequestBody Localisation localisation) {
        return ResponseEntity.ok(localisationService.enregistrer(localisation));
    }

}
