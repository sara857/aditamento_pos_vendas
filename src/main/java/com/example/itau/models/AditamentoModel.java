package com.example.itau.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class AditamentoModel implements Serializable {

    private int nova_quantidade_parcelas;
    private int nova_data_pagamento;

}
