package io.github.bortoletoeric.arquiteturaspring.montadora.configuration;

import io.github.bortoletoeric.arquiteturaspring.montadora.Montadora;
import io.github.bortoletoeric.arquiteturaspring.montadora.Motor;
import io.github.bortoletoeric.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Primary
    @Bean("motorAspirado")
    public Motor motorAspirado(){
        var motor = new Motor();
        motor.setPotencia(130);
        motor.setCilindros(4);
        motor.setModelo("X-PIRITO");
        motor.setLitragem(1.7);
        motor.setTipo(TipoMotor.ASPIRADO);
        return motor;
    }

    @Bean("motorEletrico")
    public Motor motorEletrico(){
        var motor = new Motor();
        motor.setPotencia(200);
        motor.setModelo("XPTO-50");
        motor.setLitragem(1.0);
        motor.setTipo(TipoMotor.ELETRICO);
        return motor;
    }

    @Bean("motorTurbo")
    public Motor motorTurbo(){
        var motor = new Motor();
        motor.setPotencia(250);
        motor.setCilindros(4);
        motor.setModelo("TH-40");
        motor.setLitragem(1.5);
        motor.setTipo(TipoMotor.TURBO);
        return motor;
    }
}
