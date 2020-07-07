package model;

public class Folha {
    private float salarioBase;
    private int totalGratificacoes;
    private float valorGratificacoes;
    private float salarioMensal;
    private String nomeEmpregado;
    private String funcaoEmpregado;
    private int idFuncionario;

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getFuncaoEmpregado() {
        return funcaoEmpregado;
    }

    public void setFuncaoEmpregado(String funcaoEmpregado) {
        this.funcaoEmpregado = funcaoEmpregado;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getTotalGratificacoes() {
        return totalGratificacoes;
    }

    public void setTotalGratificacoes(int totalGratificacoes) {
        this.totalGratificacoes = totalGratificacoes;
    }

    public float getValorGratificacoes() {
        return valorGratificacoes;
    }

    public void setValorGratificacoes(float valorGratificacoes) {
        this.valorGratificacoes = valorGratificacoes;
    }

    public float getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(float salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    @Override
    public String toString() {
        return "Folha{" +
                "nome=" + nomeEmpregado+
                " Funcao=" + funcaoEmpregado +
                " SalarioBase=" + salarioBase +
                " totalGratificacoes=" + totalGratificacoes +
                " valorGratificacoes=" + valorGratificacoes +
                " valorGratificacoes=" + salarioMensal +
                '}';
    }
}
