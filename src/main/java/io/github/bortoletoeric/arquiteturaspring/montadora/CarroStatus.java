package io.github.bortoletoeric.arquiteturaspring.montadora;

/**
 * Registro que representa o status/resultado de uma operação sobre um carro.
 * 
 * <p>Encapsula uma mensagem descrevendo o resultado de uma ação, como
 * ligar ou desligar o veículo.</p>
 * 
 * @param mensagem a mensagem de status da operação
 * @author Eric Bortoleto
 * @version 1.0
 * @see Carro
 */
public record CarroStatus(String mensagem) {
}
