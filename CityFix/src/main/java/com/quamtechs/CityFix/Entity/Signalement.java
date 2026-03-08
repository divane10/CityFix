package com.quamtechs.CityFix.Entity;

import java.time.LocalDateTime;

import com.quamtechs.CityFix.enumeration.Statut;
import com.quamtechs.CityFix.enumeration.TypeSignalement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Signalement")
public class Signalement {
    @Id
    @GeneratedValue
    private long id;
    private String titre;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeSignalement typeSignalement; // Ex: "Voirie", "Dechets", "Eclairage", "NidDePoule", "Raquetage" ou valeur saisie via "Autre"
    private String photoUrl;
    //@Builder.Default
    private LocalDateTime dateCreation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Utilisateurid")
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "LocalisationId")
    private Localisation localisation;

}
