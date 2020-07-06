package view;

import controller.EmpregadoDao;
import model.Folha;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ListarFolha extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btGratificacao;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListarFolha() {
        super("Folha de Pagamento");
        criaJTable();
        criaJanela();
    }

    public void criaJanela() {

        btGratificacao = new JButton("Gratificacao");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setResizable(false);
        setVisible(true);
    }

    private void criaJTable() {
        tabela = new JTable(modelo);
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Quantidade Gratificacoes");
        modelo.addColumn("Valor Gratificacoes");
        modelo.addColumn("Salario Mensal");

        tabela.setFont(new Font("Serif", Font.PLAIN, 18));
        tabela.setRowHeight(26);

        tabela.getColumnModel().getColumn(0)
                .setPreferredWidth(180);
        tabela.getColumnModel().getColumn(1)
                .setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2)
                .setPreferredWidth(30);
        tabela.getColumnModel().getColumn(3)
                .setPreferredWidth(10);
        tabela.getColumnModel().getColumn(4)
                .setPreferredWidth(20);
        tabela.getColumnModel().getColumn(5)
                .setPreferredWidth(20);
        pesquisar(modelo);
    }

    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        EmpregadoDao dao = new EmpregadoDao();

        for (Folha c : dao.getFolhaPagamento()) {
            modelo.addRow(new Object[]{c.getNomeEmpregado(), c.getFuncaoEmpregado(),
                    c.getSalarioBase(), c.getTotalGratificacoes(), c.getValorGratificacoes(), c.getSalarioMensal()});
        }
    }


    public static void main(String[] args) {
        ListarFuncionario lc = new ListarFuncionario();
        lc.setVisible(true);
    }
}