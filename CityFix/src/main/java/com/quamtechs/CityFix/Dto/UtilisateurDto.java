package com.quamtechs.CityFix.Dto;

import com.quamtechs.CityFix.enumeration.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UtilisateurDto {

    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private Role role; // ADMIN ou USER

}
