package com.quamtechs.CityFix.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.quamtechs.CityFix.Entity.Localisation;
import com.quamtechs.CityFix.Repository.LocalisationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LocalisationService {

    private final LocalisationRepository localisationRepository;

public Localisation enregistrer(Localisation localisation) {
        Localisation loc = Localisation.builder()
            .adresse(localisation.getAdresse() != null ? localisation.getAdresse() : "Localisation GPS (lat/lng)")
            .lattitude(localisation.getLattitude())
            .longitude(localisation.getLongitude())
            .build();
        
        return localisationRepository.save(loc);
    }

    public Optional<Localisation> getParId(Long id) {
        return localisationRepository.findById(id);
    }

    public List<Localisation> getToutes() {
        return localisationRepository.findAll();
    }
}
