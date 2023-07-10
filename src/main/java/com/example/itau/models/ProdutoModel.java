package com.example.itau.models;

import com.example.itau.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProdutoModel {

    ContratoModel contrato;
    FinanceiroModel financeiro;
    AditamentoModel aditamento;

    private LocalDate data_calculo;
    private String tipo_calculo;
    private double valor_total;
    private int quantidade_parcelas;
    private double valor_parcelas;
    private int dia_pagamento;
    private double percentual_taxa_juro;

}
