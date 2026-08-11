package io.github.bortoletoeric.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Aplicação Spring Boot principal para o projeto Arquitetura Spring.
 * 
 * <p>Esta aplicação demonstra os conceitos fundamentais de arquitetura
 * em aplicações Spring Boot, incluindo:</p>
 * <ul>
 *   <li>Injeção de Dependência (Dependency Injection)</li>
 *   <li>Configuração de Beans do Spring</li>
 *   <li>Anotações customizadas para Qualifiers</li>
 *   <li>REST Controllers</li>
 * </ul>
 * 
 * <p>O tema pedagógico utilizado é uma "Montadora de Carros" onde demonstramos
 * como injetar diferentes tipos de motors em um veículo.</p>
 * 
 * @author Eric Bortoleto
 * @version 0.0.1-SNAPSHOT
 * @since 1.0
 */
@SpringBootApplication
public class ArquiteturaspringApplication {

    /**
     * Ponto de entrada da aplicação Spring Boot.
     * 
     * @param args argumentos da linha de comando passados para a aplicação
     */
    public static void main(String[] args) {
//		SpringApplication.run(ArquiteturaspringApplication.class, args);

        SpringApplicationBuilder builder =
                new SpringApplicationBuilder(ArquiteturaspringApplication.class);
        builder.run(args);

        builder.bannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext applicationContext = builder.context();
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        builder.profiles("production");

        String applicationName = env.getProperty("spring.application.name");
        System.out.println("Application Name: " + applicationName);

        ExemploValue value = applicationContext.getBean(ExemploValue.class);
        value.imprimirVariavel();
    }

}
