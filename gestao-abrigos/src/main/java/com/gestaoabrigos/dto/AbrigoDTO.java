package com.gestaoabrigos.dto;

import com.gestaoabrigos.model.Abrigo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbrigoDTO {

    private Long id;

    @NotBlank(message = "O nome do abrigo é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "A capacidade é obrigatória")
    @Positive(message = "A capacidade deve ser um número positivo")
    private Integer capacidade;

    @NotBlank(message = "A localização é obrigatória")
    @Size(max = 200, message = "A localização deve ter no máximo 200 caracteres")
    private String localizacao;

    @NotNull(message = "A latitude é obrigatória")
    @DecimalMin(value = "-90.0", message = "Latitude inválida")
    @DecimalMax(value = "90.0", message = "Latitude inválida")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória")
    @DecimalMin(value = "-180.0", message = "Longitude inválida")
    @DecimalMax(value = "180.0", message = "Longitude inválida")
    private Double longitude;


    public static AbrigoDTO fromEntity(Abrigo abrigo) {
        return new AbrigoDTO(
                abrigo.getId(),
                abrigo.getNome(),
                abrigo.getCapacidade(),
                abrigo.getLocalizacao(),
                abrigo.getLatitude(),
                abrigo.getLongitude()
        );
    }

    public Abrigo toEntity() {
        Abrigo abrigo = new Abrigo();
        abrigo.setId(this.id);
        abrigo.setNome(this.nome);
        abrigo.setCapacidade(this.capacidade);
        abrigo.setLocalizacao(this.localizacao);
        abrigo.setLatitude(this.latitude);
        abrigo.setLongitude(this.longitude);
        return abrigo;
    }
}