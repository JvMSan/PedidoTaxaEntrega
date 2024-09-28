package ufes.br.pedido;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ufes.br")
public class Principal {

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }
}
