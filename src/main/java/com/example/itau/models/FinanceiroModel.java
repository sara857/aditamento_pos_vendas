package com.example.itau.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinanceiroModel {

    private String data_calculo;
    private String tipo_calculo;
    private double valor_total;
    private int quantidade_parcelas;
    private double valor_parcelas;
    private int dia_pagamento;
    private double percentual_taxa_juro;

}
