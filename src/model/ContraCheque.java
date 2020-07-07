package model;

import java.util.List;

public class ContraCheque {
    private Empregado empregado;
    private float salarioBase;
    private List<Gratificacao>gratificacaos;
    private float salarioMensal;
    private float valorGratificacoes;

    public Empregado getEmpregado() {
        return empregado;
    }

    public float getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public List<Gratificacao> getGratificacaos() {
        return gratificacaos;
    }

    public void setGratificacaos(List<Gratificacao> gratificacaos) {
        this.gratificacaos = gratificacaos;
    }

    public float getSalarioMensal() {
        return salarioMensal;
    }


    public void setSalarioMensal(float salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public float getValorGratificacoes() {

        return valorGratificacoes;
    }

    public void setValorGratificacoes(float valorGratificacoes) {
        this.valorGratificacoes = valorGratificacoes;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Gratificacao g:getGratificacaos()) {
            stringBuilder.append("GRATIGICACACAO: === ".toUpperCase()+g.getTipo() +"   "+g.getValor()+"\n");
        }
        return "FUNCIONARIO: === " + empregado.getNome() +"\n"+
                "CARGO: === " + empregado.getCargo() + "\n" +
                "SALARIO BASE: === " + salarioBase + "\n" +
                stringBuilder.toString() +
                "TOTAL GRATIFICACOES: === " + valorGratificacoes + "\n" +
                "SALARIO MENSAL: === " + salarioMensal + "\n";
    }

}
