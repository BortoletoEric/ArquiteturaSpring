package io.github.bortoletoeric.arquiteturaspring.montadora;

public class Motor {
    private String modelo;
    private Integer potencia;
    private Integer cilindros;
    private Double litragem;
    private TipoMotor tipo;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TipoMotor getTipo() {
        return tipo;
    }

    public void setTipo(TipoMotor tipo) {
        this.tipo = tipo;
    }

    public Double getLitragem() {
        return litragem;
    }

    public void setLitragem(Double litragem) {
        this.litragem = litragem;
    }

    public Integer getCilindros() {
        return cilindros;
    }

    public void setCilindros(Integer cilindros) {
        this.cilindros = cilindros;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

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
