package com.example.itau.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataModel {
    private double percentual_juros;
    private double valor_total;

    @Override
    public String toString() {
        return "DataModel{" +
                "percentual_juros=" + percentual_juros +
                ", valor_total=" + valor_total +
                '}';
    }
}
