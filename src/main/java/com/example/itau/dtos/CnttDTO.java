package com.example.itau.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CnttDTO {
    private String definir_data_contratacao;
    private String definir_criterio_calculo;
    private int definir_quantidade_parcelas;
    private double definir_valor_contratacao;

}
