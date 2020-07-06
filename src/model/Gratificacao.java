package model;

import java.util.Date;

public abstract class Gratificacao{
    private int id;
    private int idFuncionario;
    private float valor;
    private Date date;
    private String tipo;


    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int getIdFuncionario) {
        this.idFuncionario = getIdFuncionario;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Gratificacao{" +
                "id=" + id +
                ", valor=" + valor +
                '}'+"\n";
    }
}
