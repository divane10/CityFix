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
    public ResponseEntity<?> inscrire(@RequestBody UtilisateurDto util) {
        try {
            System.out.println("Inscription attempt: " + util.getEmail() + " role=" + util.getRole());
            Utilisateur user = utilisateurService.inscrire(util);
            System.out.println("User created ID: " + user.getId());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.err.println("Inscription ERROR: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur inscription: " + e.getMessage());
        }
    }

    // Super Admin creation (master key protected)
    @PostMapping("/superadmin/create")
    public ResponseEntity<?> createSuperAdmin(
            @RequestBody Map<String, String> request,
            @RequestParam String masterKey) {
        
        if (!"supersecret2024".equals(masterKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Master key invalide");
        }
        
        String email = request.get("email");
        String password = request.get("motDePasse");
        
        Utilisateur superAdmin = utilisateurService.createSuperAdmin(email, password);
        return ResponseEntity.ok(superAdmin);
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
