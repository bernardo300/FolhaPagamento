package test;

import controller.EmpregadoDao;
import junit.framework.TestCase;
import model.Empregado;
import model.Folha;
import model.Funcionario;
import model.Gerente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

public class FuncionaioTeste extends TestCase {

    @Test
    public void testSalarioMaior2500() {
        Empregado funcionario = new Funcionario();
        assertEquals(funcionario.getSalario() >= 2500, true);
    }


    @Test
    public void testTipoFuncionario() {
        Empregado funcionario = new Funcionario();
        assertEquals(funcionario.getCargo().equals("Funcionario"),true);
    }
    @Test
    public void testNomeLetraFuncionario() {
        Empregado funcionario = new Funcionario();
        String nome = "Marcos Vinicus";
        assertEquals(funcionario.getNome().equals("Funcionario"),true);
    }

}