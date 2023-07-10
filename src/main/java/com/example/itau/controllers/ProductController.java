package com.example.itau.controllers;

import com.example.itau.dtos.RequestDTO;
import com.example.itau.exception.BusinessException;
import com.example.itau.models.*;
import com.example.itau.repositories.JurosRepository;
import com.example.itau.repositories.PostsRepository;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class ProductController {
    private static final long serialVersionUID = 1L;
    private UUID uuid = UUID.randomUUID();
    @Autowired
    private PostsRepository postsRepository;

    @PostMapping("/aditamento/altera-quantidade-parcelas")
    public ProdutoModel alteraQuantidadeParcela(@RequestBody RequestDTO dto) {

        if (dto.getAditamento().nova_quantidade_parcelas() < dto.getFinanceiro().quantidade_parcelas()) {
            throw new BusinessException("Quantidade de parcelas não pode ser inferior à atual");
        }
        if (!dto.getContrato().ativo()) {
            throw new BusinessException("Contrato precisa estar ativo");
        }
        if (dto.getAditamento().nova_data_pagamento() > 0) {
            throw new BusinessException("Só é permitido um aditamento por requisição");
        }

        var adit = new AditamentoModel();
        adit.setNova_quantidade_parcelas(dto.getAditamento().nova_quantidade_parcelas());

        var con = new ContratoModel();
        con.setAtivo(dto.getContrato().ativo());
        con.setData_contratacao(dto.getContrato().data_contratacao());
        con.setId_contrato(dto.getContrato().id_contrato());
        con.setParcelas_em_atraso(dto.getContrato().parcelas_em_atraso());
        con.setNumero_cpf_cnpj_cliente(dto.getContrato().numero_cpf_cnpj_cliente());

        double percentual = dto.getFinanceiro().percentual_taxa_juro() / 100;

        double juros = dto.getFinanceiro().valor_total() * percentual;

        double vlTotal = dto.getFinanceiro().valor_total() + juros;

        var finan = new FinanceiroModel();
        finan.setData_calculo(dto.getFinanceiro().data_calculo());
        finan.setValor_total(dto.getFinanceiro().valor_total());
        finan.setValor_parcelas(dto.getFinanceiro().valor_parcelas());
        finan.setDia_pagamento(dto.getFinanceiro().dia_pagamento());
        finan.setTipo_calculo(dto.getFinanceiro().tipo_calculo());
        finan.setQuantidade_parcelas(dto.getFinanceiro().quantidade_parcelas());
        finan.setPercentual_taxa_juro(dto.getFinanceiro().percentual_taxa_juro());

        var p = new ProdutoModel();
        LocalDate localDate = LocalDate.now();
        p.setValor_total(vlTotal);
        p.setValor_parcelas(vlTotal / dto.getAditamento().nova_quantidade_parcelas());
        p.setQuantidade_parcelas(dto.getAditamento().nova_quantidade_parcelas());
        p.setData_calculo(localDate);
        p.setTipo_calculo("ADITAMENTO");
        p.setDia_pagamento(dto.getFinanceiro().dia_pagamento());
        p.setPercentual_taxa_juro(dto.getFinanceiro().percentual_taxa_juro());

        p.setFinanceiro(finan);
        p.setAditamento(adit);
        p.setContrato(con);

        return p;
    }

    @PostMapping("/aditamento/altera-dia-pagamento")
    public ProdutoModel alteraDiaPagamento(@RequestBody RequestDTO dto) {

        if (!dto.getContrato().ativo()) {
            throw new BusinessException("Contrato precisa estar ativo");
        }
        if (dto.getContrato().parcelas_em_atraso()) {
            throw new BusinessException("Contrato não pode ter parcelas em atraso");
        }
        if (dto.getAditamento().nova_quantidade_parcelas() > 0) {
            throw new BusinessException("Só é permitido um aditamento por requisição");
        }
//        DateTime dataInicial = new DateTime();

        var p = new ProdutoModel();
        var adit = new AditamentoModel();
        adit.setNova_quantidade_parcelas(dto.getAditamento().nova_quantidade_parcelas());

        var con = new ContratoModel();
        con.setAtivo(dto.getContrato().ativo());
        con.setData_contratacao(dto.getContrato().data_contratacao());
        con.setId_contrato(dto.getContrato().id_contrato());
        con.setParcelas_em_atraso(dto.getContrato().parcelas_em_atraso());
        con.setNumero_cpf_cnpj_cliente(dto.getContrato().numero_cpf_cnpj_cliente());

        var finan = new FinanceiroModel();
        finan.setData_calculo(dto.getFinanceiro().data_calculo());
        finan.setValor_total(dto.getFinanceiro().valor_total());
        finan.setValor_parcelas(dto.getFinanceiro().valor_parcelas());
        finan.setDia_pagamento(dto.getAditamento().nova_data_pagamento());
        finan.setTipo_calculo(dto.getFinanceiro().tipo_calculo());
        finan.setQuantidade_parcelas(dto.getFinanceiro().quantidade_parcelas());
        finan.setPercentual_taxa_juro(dto.getFinanceiro().percentual_taxa_juro());

        LocalDate localDate = LocalDate.now();

        p.setValor_total(dto.getFinanceiro().valor_total());
        p.setValor_parcelas(dto.getFinanceiro().valor_parcelas());
        p.setQuantidade_parcelas(dto.getFinanceiro().quantidade_parcelas());
        p.setData_calculo(localDate);
        p.setTipo_calculo("ADITAMENTO");
        p.setDia_pagamento(dto.getAditamento().nova_data_pagamento());
        p.setPercentual_taxa_juro(dto.getFinanceiro().percentual_taxa_juro());

        p.setFinanceiro(finan);
        p.setContrato(con);

        return p;
    }

    JurosRepository jurosRepository = Feign.builder()
    .decoder(new GsonDecoder())
    .target(JurosRepository.class, "https://juros-api.itau.br");

//    UUID uuid = UUID.randomUUID();
//    jurosRepository.realizarChamadaAPI(uuid);

}
