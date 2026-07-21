package io.github.bortoletoeric.arquiteturaspring.montadora;

/**
 * Representa a chave de um automóvel.
 * 
 * <p>A chave possui informações sobre a montadora proprietária e seu tipo,
 * permitindo validação de acesso ao veículo. Apenas chaves da mesma montadora
 * podem ligar um carro.</p>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see Montadora
 * @see Carro
 */
public class Chave {
    /**
     * A montadora proprietária da chave.
     */
    private Montadora montadora;
    /**
     * O tipo da chave (ex: "normal", "presença").
     */
    private String tipo;

    /**
     * Obtém a montadora proprietária da chave.
     * 
     * @return a montadora da chave
     */
    public Montadora getMontadora() {
        return montadora;
    }

    /**
     * Define a montadora proprietária da chave.
     * 
     * @param montadora a montadora da chave
     */
    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    /**
     * Obtém o tipo da chave.
     * 
     * @return uma string descrevendo o tipo da chave
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da chave.
     * 
     * @param tipo uma string descrevendo o tipo da chave
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
