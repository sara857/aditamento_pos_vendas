package com.example.itau.dtos;

public record ContratoDTO(int id_contrato, String numero_cpf_cnpj_cliente, String data_contratacao, boolean ativo, boolean parcelas_em_atraso) {

}
