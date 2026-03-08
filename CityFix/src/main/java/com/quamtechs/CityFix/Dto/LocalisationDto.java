package com.quamtechs.CityFix.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDto {
    @NotBlank(message = "adresse requise")
    private String adresse;
    @NotNull(message = "latitude non vide")
    private Double lattitude;
    @NotNull(message = "longitude non vide")
    private Double longitude;

}
