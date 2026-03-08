package com.quamtechs.CityFix.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quamtechs.CityFix.Entity.Signalement;
import com.quamtechs.CityFix.enumeration.Statut;
import com.quamtechs.CityFix.enumeration.TypeSignalement;

@Repository
public interface SignalementRepository extends JpaRepository<Signalement, Long> {
    List<Signalement> findByUtilisateurId(Long utilisateurId);
    List<Signalement> findByTypeSignalement(TypeSignalement typesignalement);
    List<Signalement> findByStatut(Statut statut);

}
