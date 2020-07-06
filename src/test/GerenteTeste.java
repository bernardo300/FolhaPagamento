package test;

import controller.EmpregadoDao;
import junit.framework.TestCase;
import model.Empregado;
import model.Folha;
import model.Gerente;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

public class GerenteTeste extends TestCase {

    @Test
    public void testSalarioMaior4000() {
        Empregado gerente = new Gerente();
        assertEquals(gerente.getSalario() >= 4000, true);
    }


    @Test
    public void testTipoFuncionario() {
        Empregado gerente = new Gerente();
        assertEquals(gerente.getCargo().equals("Gerente"),true);
    }

}

