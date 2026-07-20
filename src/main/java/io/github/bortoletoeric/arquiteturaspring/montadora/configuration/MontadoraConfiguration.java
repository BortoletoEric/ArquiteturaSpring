package io.github.bortoletoeric.arquiteturaspring.montadora.configuration;

import io.github.bortoletoeric.arquiteturaspring.montadora.Montadora;
import io.github.bortoletoeric.arquiteturaspring.montadora.Motor;
import io.github.bortoletoeric.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {
    @Bean
    public Montadora montadora(){

        return null;
    }

    @Bean
    public Motor motor(){
        var motor = new Motor();
        motor.setPotencia(200);
        motor.setCilindros(4);
        motor.setModelo("Civic G7");
        motor.setLitragem(1.7);
        motor.setTipo(TipoMotor.TURBO);
        return motor;
    }
}
