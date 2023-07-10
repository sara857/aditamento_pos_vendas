package com.example.itau.repositories;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.UUID;

@FeignClient(value = "calculo-juros", url = "https://juros-api.itau.br")
public interface JurosRepository {

    @RequestLine("POST /calculo-juros")
    @Headers("Content-Type: application/json")
    public abstract void realizarChamadaAPI(@Param("itau-pos-venda-teste") UUID uuid);
}