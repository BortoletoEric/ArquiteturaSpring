package io.github.bortoletoeric.arquiteturaspring.montadora;

/**
 * Enumeração dos tipos de motores disponíveis.
 * 
 * <p>Define os três tipos de motores suportados pela aplicação:</p>
 * <ul>
 *   <li><b>ASPIRADO</b>: Motor atmosférico sem sobrealimentação</li>
 *   <li><b>TURBO</b>: Motor com turbocompressor para maior potência</li>
 *   <li><b>ELETRICO</b>: Motor elétrico para veículos elétricos</li>
 * </ul>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Motor
 */
public enum TipoMotor {
    ASPIRADO,
    TURBO,
    ELETRICO
}
