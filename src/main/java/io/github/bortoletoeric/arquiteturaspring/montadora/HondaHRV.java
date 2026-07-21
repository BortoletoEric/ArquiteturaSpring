package io.github.bortoletoeric.arquiteturaspring.montadora;

import java.awt.*;

/**
 * Representa um modelo específico de carro: Honda HRV.
 * 
 * <p>Especialização da classe Carro que pré-configura as propriedades
 * específicas do modelo Honda HRV:</p>
 * <ul>
 *   <li>Modelo: "HRV"</li>
 *   <li>Cor: Preto</li>
 *   <li>Montadora: HONDA</li>
 * </ul>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Carro
 * @see Motor
 */
public class HondaHRV extends Carro {
    /**
     * Construtor que inicializa um HondaHRV com um motor específico.
     * 
     * <p>Configura automaticamente o modelo como "HRV", a cor como preta
     * e a montadora como Honda.</p>
     * 
     * @param motor o motor a ser acoplado ao HondaHRV
     */
    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.BLACK);
        setMontadora(Montadora.HONDA);
    }
}
