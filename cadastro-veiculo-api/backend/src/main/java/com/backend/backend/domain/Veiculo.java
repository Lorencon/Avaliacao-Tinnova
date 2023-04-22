package com.backend.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "veiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do veículo é obrigatório")
    private String veiculo;

    @NotBlank(message = "O nome da marca é obrigatório")
    private String marca;

    @Min(value = 1900, message = "O ano mínimo permitido é 1900")
    @Max(value = 2100, message = "O ano máximo permitido é 2100")
    private Integer ano;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private Boolean vendido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(updatable = false)
    private LocalDate created;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updated;

    private String cor;

    public Integer getDecada() {
        return (this.ano / 10) * 10;
    }

    // getters e setters
}
