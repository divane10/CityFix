package com.quamtechs.CityFix.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quamtechs.CityFix.Dto.UtilisateurDto;
import com.quamtechs.CityFix.Entity.Utilisateur;
import com.quamtechs.CityFix.Service.UtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    // Inscription d'un utilisateur
    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscrire(@RequestBody UtilisateurDto util) {
        Utilisateur user = utilisateurService.inscrire(util);
        return ResponseEntity.ok(user);
    }

    // Connexion d'un utilisateur
    @PostMapping("/connexion")
    public ResponseEntity<?> connecter(@RequestBody Map<String, String> creds) {

        String email = creds.get("email");
        String motDePasse = creds.get("motDePasse");

        Optional<Utilisateur> userOpt = utilisateurService.connecter(email, motDePasse);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Identifiants invalides");
        }
    }
}