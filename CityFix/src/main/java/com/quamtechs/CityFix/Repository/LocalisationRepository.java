package com.quamtechs.CityFix.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quamtechs.CityFix.Entity.Localisation;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
}
