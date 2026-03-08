package com.quamtechs.CityFix.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatutDto {
     private String nom; // Ex: "En attente", "En cours", "Resolu"

}
