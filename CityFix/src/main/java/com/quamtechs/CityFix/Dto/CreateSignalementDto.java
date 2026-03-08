package com.quamtechs.CityFix.Dto;

 
import com.quamtechs.CityFix.enumeration.TypeSignalement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSignalementDto {
    @NotBlank(message = "titre requis")
    private String titre;
    @NotBlank(message = "description requis")
    private String description;
    @NotNull(message = "typeSignalement requis")
    private TypeSignalement typeSignalement;
    @NotBlank(message = "description requis")
    private String photoUrl;
    @NotBlank(message = "statut requis")
    private String statut;
    @NotNull(message = "utilisateurId requis")
    private Long utilisateurId;   
    @NotNull(message = "localisationId requis")
    private Long localisationId;    
    

}
