package test;
import controller.EmpregadoDao;
import junit.framework.TestCase;
import model.Folha;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

public class FolhaTeste extends TestCase {

    @Test
    public void testFolhaNull() {
        EmpregadoDao dao = new EmpregadoDao();
        List<Folha> folhas = dao.getFolhaPagamento();
        assertEquals(folhas.isEmpty(), false);
    }


    @Test
    public void testFolhaAtributoSalarioVazio() {
        EmpregadoDao dao = new EmpregadoDao();
        List<Folha> folhas = dao.getFolhaPagamento();
        for (Folha f: folhas) {
            float salario = f.getSalarioBase();
            assertEquals(salario <= 0, false);
        }
    }

    @Test
    public void testFolhaAtributoSalarioMesalVazio() {
        EmpregadoDao dao = new EmpregadoDao();
        List<Folha> folhas = dao.getFolhaPagamento();
        for (Folha f: folhas) {
            float salario = f.getSalarioMensal();
            assertEquals(salario <= 0, false);
        }
    }

}
