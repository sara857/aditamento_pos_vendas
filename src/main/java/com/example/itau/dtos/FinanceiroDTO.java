package com.example.itau.dtos;

public record FinanceiroDTO(String data_calculo, String tipo_calculo, double valor_total, int quantidade_parcelas, double valor_parcelas, int dia_pagamento, double percentual_taxa_juro) {

}
