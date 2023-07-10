package com.example.itau.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoModel {
    private int id_contrato;
    private String numero_cpf_cnpj_cliente;
    private String data_contratacao;
    private boolean ativo;
    private boolean parcelas_em_atraso;

}
