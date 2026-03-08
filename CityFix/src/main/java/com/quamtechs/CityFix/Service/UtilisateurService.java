package com.quamtechs.CityFix.Service;

import java.util.Optional;

import com.quamtechs.CityFix.enumeration.Role;
import org.springframework.stereotype.Service;

import com.quamtechs.CityFix.Dto.UtilisateurDto;
import com.quamtechs.CityFix.Entity.Utilisateur;
import com.quamtechs.CityFix.Repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepo;

     // Inscription et verification de l'email d'un utilisateur

    //private String prenom;
    //private String nom;
    //private String email

    public Utilisateur inscrire(UtilisateurDto dto) {
      
        Utilisateur user =Utilisateur.builder()
        .email(dto.getEmail())
        .motDePasse(dto.getMotDePasse())
        .nom(dto.getNom())
        .prenom(dto.getPrenom())
        .role(dto.getRole() != null ? dto.getRole() : Role.USER)// Par défaut USER
        .build();
        return utilisateurRepo.save(user);
    }
    //public Utilisateur inscrire(Utilisateur util) {
      //  if (utilisateurRepo.existsByEmail(util.getEmail())) {
         //   throw new RuntimeException("Email déjà utilisé");
           //.email.setemail(utilisateur),
        //   .
      //  }
      //  return utilisateurRepo.save(util);
   // }

    // Connexion d'un utilisateur

    public Optional<Utilisateur> connecter(String email, String motDePasse) {
        return utilisateurRepo.findByEmail(email)
                .filter(u -> u.getMotDePasse().equals(motDePasse));
    }

}

