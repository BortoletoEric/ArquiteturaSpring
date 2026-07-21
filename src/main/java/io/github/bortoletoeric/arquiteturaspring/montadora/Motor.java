package io.github.bortoletoeric.arquiteturaspring.montadora;

/**
 * Representa um motor de um automóvel com suas características técnicas.
 * 
 * <p>A classe encapsula as informações sobre um motor, como:</p>
 * <ul>
 *   <li>Modelo do motor</li>
 *   <li>Potência em cavalos-vapor (HP)</li>
 *   <li>Número de cilindros</li>
 *   <li>Litragem (deslocamento)</li>
 *   <li>Tipo de motor (Aspirado, Turbo ou Elétrico)</li>
 * </ul>
 * 
 * @author Eric Bortoleto
 * @version 1.0
 * @see TipoMotor
 * @see Carro
 */
public class Motor {
    /**
     * Modelo do motor (ex: "TH-40", "X-PIRITO").
     */
    private String modelo;
    /**
     * Potência do motor em cavalos-vapor (HP).
     */
    private Integer potencia;
    /**
     * Número de cilindros do motor.
     */
    private Integer cilindros;
    /**
     * Litragem (deslocamento) do motor em litros.
     */
    private Double litragem;
    /**
     * Tipo de motor (Aspirado, Turbo ou Elétrico).
     */
    private TipoMotor tipo;

    /**
     * Obtém o modelo do motor.
     * 
     * @return a string com o modelo do motor
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do motor.
     * 
     * @param modelo a string com o nome/modelo do motor
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o tipo de motor.
     * 
     * @return o tipo do motor (Aspirado, Turbo ou Elétrico)
     */
    public TipoMotor getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de motor.
     * 
     * @param tipo o tipo do motor (Aspirado, Turbo ou Elétrico)
     */
    public void setTipo(TipoMotor tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém a litragem (deslocamento) do motor.
     * 
     * @return a litragem em litros
     */
    public Double getLitragem() {
        return litragem;
    }

    /**
     * Define a litragem (deslocamento) do motor.
     * 
     * @param litragem a litragem em litros
     */
    public void setLitragem(Double litragem) {
        this.litragem = litragem;
    }

    /**
     * Obtém o número de cilindros do motor.
     * 
     * @return o número de cilindros
     */
    public Integer getCilindros() {
        return cilindros;
    }

    /**
     * Define o número de cilindros do motor.
     * 
     * @param cilindros o número de cilindros
     */
    public void setCilindros(Integer cilindros) {
        this.cilindros = cilindros;
    }

    /**
     * Obtém a potência do motor em cavalos-vapor.
     * 
     * @return a potência em HP
     */
    public Integer getPotencia() {
        return potencia;
    }

    /**
     * Define a potência do motor em cavalos-vapor.
     * 
     * @param potencia a potência em HP
     */
    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    /**
     * Retorna uma representação em string do motor com todas as suas características.
     * 
     * @return uma string contendo o modelo, potência, cilindros, litragem e tipo
     */
    @Override
    public String toString() {
        return "Motor{" +
                "modelo='" + modelo + '\'' +
                ", potencia=" + potencia +
                ", cilindros=" + cilindros +
                ", litragem=" + litragem +
                ", tipo=" + tipo +
                '}';
    }
}
