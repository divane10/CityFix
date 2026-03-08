package com.quamtechs.CityFix.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.Resource;

import org.springframework.http.MediaType;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import com.quamtechs.CityFix.Entity.Signalement;
import com.quamtechs.CityFix.Repository.SignalementRepository;
import com.quamtechs.CityFix.Service.SignalementService;
import com.quamtechs.CityFix.enumeration.Statut;
import com.quamtechs.CityFix.enumeration.TypeSignalement;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@Controller
@RestController
@RequestMapping("/api/signalements")
@RequiredArgsConstructor
public class SignalementController {

    private final SignalementRepository signalementRepo;
    private final SignalementService signalementService;
    private final String uploadDir = "uploads/";

    //creer un signalement lié a un utilisateur:
     // Créer un signalement avec photo
     // signalement envoyé en JSON
     // photo envoyée en multipart
     // utilisateurId et localisationId pour la liaison
  
     // Créer un signalement avec photo

    @PostMapping(value = "/ajouter", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Signalement> creerSignalement(
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("typeSignalement") TypeSignalement typeSignalement,
            @RequestParam("statut") String statut,
            @RequestParam("utilisateurId") Long utilisateurId,  
            @RequestParam("localisationId") Long localisationId,
            @RequestPart("photo") MultipartFile photo) {

        try {
            // Sauvegarde de la photo

            String nomFichier = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path chemin = Paths.get(uploadDir + nomFichier);
            Files.createDirectories(chemin.getParent());
            Files.write(chemin, photo.getBytes());

            // Stocker le chemin dans le DTO
            String savedPhotoUrl = nomFichier;

            Signalement saved = signalementService.creerSignalement(titre,description,typeSignalement,statut,utilisateurId,localisationId, savedPhotoUrl);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Changer le statut

    @PutMapping("/statut/{id}")
    public ResponseEntity<Signalement> changerStatut(@PathVariable Long id,
                                                     @RequestParam Statut nouveauStatut) {
        return ResponseEntity.ok(signalementService.changerStatut(id, nouveauStatut));
    }

    //Récuperer tous les signalements

    @GetMapping
    public List<Signalement> getTous() {
        return signalementService.getTousLesSignalements();
    }

    //Récuperer un signalement par son ID

    @GetMapping("/{id}")
    public ResponseEntity<Signalement> getParId(@PathVariable Long id) {
        try {
            Signalement signalement = signalementService.getSignalementAvecRelations(id);
            return ResponseEntity.ok(signalement);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Récuperer les signalements faits par un utilisateur

    @GetMapping("/utilisateur/{id}")
    public List<Signalement> getParUtilisateur(@PathVariable Long id) {
        return signalementService.getSignalementsParUtilisateur(id);
    }


     // Supprimer un signalement

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (signalementRepo.existsById(id)) {
            signalementRepo.deleteById(id);
            return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.noContent().build();
            }
     }

     // Récupérer une photo

    @GetMapping("/photo/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path path = Paths.get(uploadDir + filename);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/par-type")
    public List<Signalement> getParType(@RequestParam String type) {
        try {
            TypeSignalement typeEnum = TypeSignalement.valueOf(type); // convertit le String en enum
            return signalementRepo.findByTypeSignalement(typeEnum);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TypeSignalement invalide !");
        }
    }

}