package com.example.itau;

import com.example.itau.controllers.ProductController;
import com.example.itau.dtos.CnttDTO;
import com.example.itau.dtos.JurosDTO;
import com.example.itau.repositories.JurosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ItauApplication{

	public static void main(String[] args) {
		SpringApplication.run(ItauApplication.class, args);
//		ProductController controller = new ProductController();
//		var cntt = new CnttDTO("2021-07-30", "JUROS_SIMPLES",54,50000.00);
//		var dto = new JurosDTO(cntt);
//
//		System.out.println(controller.calculaJuros(dto));
	}


//	@Autowired
//	private JurosRepository jurosRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		var cntt = new CnttDTO("2021-07-30", "JUROS_SIMPLES",54,50000.00);
//		var dto = new JurosDTO(cntt);
//		this.jurosRepository.postJuros(dto);
//	}
}
