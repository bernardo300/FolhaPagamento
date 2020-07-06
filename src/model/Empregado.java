package model;

public abstract class Empregado {
 
    private int id;
    private String nome;
    private String cargo;
    private float salario;
    private Gratificacao gratificacao;

    public Gratificacao getGratificacao() {
        return gratificacao;
    }

    public void setGratificacao(Gratificacao gratificacao) {
        this.gratificacao = gratificacao;
        this.gratificacao.setValor(this.getSalario() * gratificacao.getValor());
    }

    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getCargo() {
        return cargo;
    }
 
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
 
    public float getSalario() {
        return salario;
    }
 
    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "id=" + id + "\n" +
                "nome='" + nome + "\n" +
                "funcao='" + cargo + "\n" +
                "salario=" + salario + "\n" +
                '}';
    }
}