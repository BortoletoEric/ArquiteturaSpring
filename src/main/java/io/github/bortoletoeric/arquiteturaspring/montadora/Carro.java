package io.github.bortoletoeric.arquiteturaspring.montadora;

import java.awt.*;

/**
 * Representa um automóvel genérico com suas características.
 * 
 * <p>A classe encapsula as propriedades de um carro como:</p>
 * <ul>
 *   <li>Modelo do veículo</li>
 *   <li>Cor</li>
 *   <li>Motor</li>
 *   <li>Montadora fabricante</li>
 * </ul>
 * 
 * <p>Fornece funcionalidades para ligar o carro através de uma chave,
 * validando se a chave pertence à mesma montadora.</p>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Motor
 * @see Montadora
 * @see Chave
 * @see CarroStatus
 */
public class Carro {
    /**
     * Modelo do carro (ex: "HRV", "Corolla").
     */
    private String modelo;
    /**
     * Cor do veículo.
     */
    private Color cor;
    /**
     * Motor acoplado ao carro.
     */
    private Motor motor;
    /**
     * Montadora fabricante do carro.
     */
    private Montadora montadora;

    /**
     * Construtor que inicializa o carro com um motor específico.
     * 
     * @param motor o motor a ser acoplado ao carro
     */
    public Carro(Motor motor) {
        this.motor = motor;
    }

    /**
     * Obtém o modelo do carro.
     * 
     * @return o modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do carro.
     * 
     * @param modelo o nome/modelo do veículo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém a cor do carro.
     * 
     * @return a cor do veículo
     */
    public Color getCor() {
        return cor;
    }

    /**
     * Define a cor do carro.
     * 
     * @param cor a cor do veículo
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * Obtém o motor do carro.
     * 
     * @return o motor acoplado ao veículo
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Define o motor do carro.
     * 
     * @param motor o motor a ser acoplado
     */
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    /**
     * Obtém a montadora do carro.
     * 
     * @return a montadora fabricante do veículo
     */
    public Montadora getMontadora() {
        return montadora;
    }

    /**
     * Define a montadora do carro.
     * 
     * @param montadora a montadora fabricante
     */
    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    /**
     * Tenta ligar o carro usando uma chave.
     * 
     * <p>A chave deve ser da mesma montadora do carro para ligar com sucesso.</p>
     * 
     * @param chave a chave para ligar o carro
     * @return um CarroStatus indicando se a ignição foi bem-sucedida
     */
    public CarroStatus darIgnicao(Chave chave) {
        if(chave.getMontadora() != this.montadora) {
            return new CarroStatus("Não é possível dar Ignição com essa chave");
        }
        return new CarroStatus("Carro ligado. Rodando com o motor " + motor);
    }
}
