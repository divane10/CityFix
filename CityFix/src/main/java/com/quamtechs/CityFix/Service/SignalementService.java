package com.quamtechs.CityFix.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.quamtechs.CityFix.Entity.Localisation;
import com.quamtechs.CityFix.Entity.Signalement;
import com.quamtechs.CityFix.Entity.Utilisateur;

import com.quamtechs.CityFix.Repository.LocalisationRepository;
import com.quamtechs.CityFix.Repository.SignalementRepository;
import com.quamtechs.CityFix.Repository.UtilisateurRepository;
import com.quamtechs.CityFix.enumeration.Statut;
import com.quamtechs.CityFix.enumeration.TypeSignalement;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignalementService {

    private final SignalementRepository signalementRepo;
    private final UtilisateurRepository utilisateurRepo;
    private final LocalisationRepository localisationRepo;

    public Signalement creerSignalement(String titre,
             String description,
             TypeSignalement typeSignalement,
             String statut,
             Long utilisateurId,  
             Long localisationId,
             String photoUrl) {

        Utilisateur user = utilisateurRepo.findById(utilisateurId)
                             .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Localisation localisation = localisationRepo.findById(localisationId)
                                     .orElseThrow(() -> new RuntimeException("Localisation non trouvée"));

        Signalement s = new Signalement();
        s.setTitre(titre);
        s.setDescription(description);
        s.setTypeSignalement(typeSignalement);
        s.setStatut(Statut.valueOf(statut.toUpperCase())); 
        s.setPhotoUrl(photoUrl);
        s.setUtilisateur(user);
        s.setDateCreation(LocalDateTime.now());
        s.setLocalisation(localisation);

        return signalementRepo.save(s);
    }

    public List<Signalement> getSignalementsParUtilisateur(Long utilisateurId) {
        return signalementRepo.findByUtilisateurId(utilisateurId);
    }

    public List<Signalement> getTousLesSignalements() {
        return signalementRepo.findAll();
    }

    public Optional<Signalement> getParId(Long id) {
        return signalementRepo.findById(id);
    }

    // Methode pour recuperer un signalement avec ses relations (utilisateur et localisation)
    public Signalement getSignalementAvecRelations(Long id) {
        return signalementRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Signalement non trouvé"));
    }

    public Signalement changerStatut(Long id, Statut nouveauStatut) {
        Signalement signalement = signalementRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Signalement non trouvé"));

        signalement.setStatut(nouveauStatut);

        return signalementRepo.save(signalement);
    }

    public void supprimer(Long id) {
        if (!signalementRepo.existsById(id)) {
            throw new RuntimeException("Signalement introuvable");
        }
        signalementRepo.deleteById(id);
    }
}
