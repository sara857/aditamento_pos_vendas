package com.example.itau.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    ContratoDTO contrato;
    FinanceiroDTO financeiro;
    AditamentoDTO aditamento;

}
