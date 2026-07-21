package io.github.bortoletoeric.arquiteturaspring.montadora.configuration;

import io.github.bortoletoeric.arquiteturaspring.montadora.Motor;
import io.github.bortoletoeric.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Classe de configuração do Spring para a montadora.
 * 
 * <p>Define os Beans (motores) que serão gerenciados pelo container do Spring.
 * Cada Bean representa um tipo diferente de motor que pode ser injetado
 * em outras classes através da Dependency Injection.</p>
 * 
 * <p>Demonstrates:</p>
 * <ul>
 *   <li>Definição de Beans com @Bean</li>
 *   <li>Uso de nomes com @Bean("nome")</li>
 *   <li>Qualificação primária com @Primary</li>
 *   <li>Injeção de dependência em Spring</li>
 * </ul>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Motor
 * @see TipoMotor
 */
@Configuration
public class MontadoraConfiguration {

    /**
     * Define um Bean para um motor aspirado.
     * 
     * <p>Cria uma instância de Motor com especificações de motor atmosférico:</p>
     * <ul>
     *   <li>Potência: 130 HP</li>
     *   <li>Cilindros: 4</li>
     *   <li>Modelo: X-PIRITO</li>
     *   <li>Litragem: 1.7L</li>
     *   <li>Tipo: ASPIRADO</li>
     * </ul>
     * 
     * @return uma nova instância de Motor configurada como aspirado
     */
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

    /**
     * Define um Bean para um motor elétrico.
     * 
     * <p>Cria uma instância de Motor com especificações de motor elétrico:</p>
     * <ul>
     *   <li>Potência: 200 HP</li>
     *   <li>Modelo: XPTO-50</li>
     *   <li>Litragem: 1.0L</li>
     *   <li>Tipo: ELETRICO</li>
     * </ul>
     * 
     * @return uma nova instância de Motor configurada como elétrico
     */
    @Bean("motorEletrico")
    public Motor motorEletrico(){
        var motor = new Motor();
        motor.setPotencia(200);
        motor.setModelo("XPTO-50");
        motor.setLitragem(1.0);
        motor.setTipo(TipoMotor.ELETRICO);
        return motor;
    }

    /**
     * Define um Bean para um motor turbo (Bean primário).
     * 
     * <p>Cria uma instância de Motor com especificações de motor turboalimentado.
     * Este Bean é marcado como @Primary, o que significa que será injetado
     * quando não houver uma qualificação específica.</p>
     * <ul>
     *   <li>Potência: 250 HP</li>
     *   <li>Cilindros: 4</li>
     *   <li>Modelo: TH-40</li>
     *   <li>Litragem: 1.5L</li>
     *   <li>Tipo: TURBO</li>
     * </ul>
     * 
     * @return uma nova instância de Motor configurada como turbo
     */
    @Primary
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
