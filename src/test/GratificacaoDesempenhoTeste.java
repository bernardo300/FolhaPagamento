package test;

import controller.EmpregadoDao;
import junit.framework.TestCase;
import model.Empregado;
import model.Folha;
import model.Gerente;
import model.GratificacaoDesempenho;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


public class GratificacaoDesempenhoTeste extends TestCase {

    @Test
    public void testGratificacaoDesempenho() {
        GratificacaoDesempenho gratificacao = new GratificacaoDesempenho();
        assertEquals(gratificacao.getTipo().equals("Desempenho"), true);
    }


    @Test
    public void testGratificacao5() {
        GratificacaoDesempenho gratificacao = new GratificacaoDesempenho();
        assertEquals(gratificacao.getValor(),0.05f);
    }

}
