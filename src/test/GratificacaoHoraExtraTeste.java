package test;

import controller.EmpregadoDao;
import junit.framework.TestCase;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


public class GratificacaoHoraExtraTeste extends TestCase {

    @Test
    public void testGratificacaoHoraExtra() {
        GratificacaoHoraExtra gratificacao = new GratificacaoHoraExtra();
        assertEquals(gratificacao.getTipo().equals("Hora Extra"), true);
    }


    @Test
    public void testGratificacao1() {
        GratificacaoHoraExtra gratificacao = new GratificacaoHoraExtra();
        assertEquals(gratificacao.getValor(),0.01f);
    }

}
