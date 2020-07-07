package view;

import controller.EmpregadoDao;
import controller.GratificacaoDao;
import model.ContraCheque;
import model.Empregado;
import model.Folha;
import model.Gratificacao;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ListarFolha extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btGratificacao;
    private DefaultTableModel modelo = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int col) { return false; }
    };

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
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Quantidade Gratificacoes");
        modelo.addColumn("Valor Gratificacoes");
        modelo.addColumn("Salario Mensal");

        //tornar coluna id invisivel
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);

        tabela.setFont(new Font("Serif", Font.PLAIN, 18));
        tabela.setRowHeight(26);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(20);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int linhaSelecionada = -1;
                    linhaSelecionada = tabela.getSelectedRow();
                    if (linhaSelecionada >= 0) {
                        int idFuncionario = (int) tabela.getValueAt(linhaSelecionada, 0);
                        EmpregadoDao daoF = new EmpregadoDao();
                        GratificacaoDao daoG = new GratificacaoDao();
                        Empregado empregado = daoF.getFuncionarioById(idFuncionario);
                        ContraCheque contraCheque = daoG.getContraChequeByFuncionario(empregado);

                        JOptionPane.showMessageDialog(ListarFolha.this,
                                        contraCheque,"Contra cheque",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(ListarFolha.this,
                                "É necesário selecionar uma linha.");
                    }
                }
            }
        });
        pesquisar(modelo);
    }
    private String montarContracheque(List<Gratificacao> list, Empregado empregado){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Funcionario: ".toUpperCase()+empregado.getNome()+"\n");
        stringBuilder.append("Cargo: ".toLowerCase()+empregado.getCargo()+"\n");
        stringBuilder.append("Salario Base: ".toUpperCase()+empregado.getSalario()+"\n");
        float toG = 0;
        for (Gratificacao g:list) {
            stringBuilder.append("Gratificacao: ".toUpperCase()+g.getTipo() +"   "+g.getValor()+"\n");
            toG = toG + g.getValor();
        }
        stringBuilder.append("Total Gratificacoes :".toUpperCase()+toG + "\n");
        stringBuilder.append("Salario mensal  :".toUpperCase()+(empregado.getSalario() + toG )+ "\n");
        return stringBuilder.toString();
    }

    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        EmpregadoDao dao = new EmpregadoDao();

        for (Folha c : dao.getFolhaPagamento()) {
            modelo.addRow(new Object[]{c.getIdFuncionario(), c.getNomeEmpregado(), c.getFuncaoEmpregado(),
                    c.getSalarioBase(), c.getTotalGratificacoes(), c.getValorGratificacoes(), c.getSalarioMensal()});
        }
    }


    public static void main(String[] args) {
        ListarFuncionario lc = new ListarFuncionario();
        lc.setVisible(true);
    }
}