package view;

import controller.GratificacaoDao;
import model.Gratificacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListarGratificacao extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListarGratificacao() {
        super("Gratificacoes");
        criaJTable();
        criaJanela();
    }

    public void criaJanela() {
        btInserir = new JButton("Inserir");
        btExcluir = new JButton("Excluir");
        btEditar = new JButton("Editar");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        getContentPane().add(painelFundo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setResizable(false);
        setResizable(false);

        setVisible(true);
        btInserir.addActionListener(new BtInserirListener());
        btEditar.addActionListener(new BtEditarListener());
        btExcluir.addActionListener(new BtExcluirListener());
    }

    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        GratificacaoDao dao = new GratificacaoDao();

        for (Gratificacao c : dao.getGratificacao()) {
            modelo.addRow(new Object[]{c.getId(), c.getTipo(),
                    c.getIdFuncionario(), c.getValor()});
        }
    }

    private void criaJTable() {

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Id Funcionario");
        modelo.addColumn("valor");
        tabela.setFont(new Font("Serif", Font.PLAIN, 18));
        tabela.setRowHeight(26);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        pesquisar(modelo);
    }


    private class BtInserirListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idFuncionario = (int) tabela.getValueAt(linhaSelecionada, 2);
                InserirGratificacao ic = new InserirGratificacao(modelo, idFuncionario,  linhaSelecionada);
                ic.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(ListarGratificacao.this,
                        "É necesário selecionar uma linha.");
            }
        }
    }


    private class BtEditarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idGratificacao = (int) tabela.getValueAt(linhaSelecionada, 0);
                int idFuncionario = (int) tabela.getValueAt(linhaSelecionada, 2);
                AtualizarGratificacao ic = new AtualizarGratificacao(modelo, idGratificacao,idFuncionario,  linhaSelecionada);
                ic.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(ListarGratificacao.this,
                        "É necesário selecionar uma linha.");
            }
        }
    }

    private class BtExcluirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idGratificacao = (int) tabela.getValueAt(linhaSelecionada, 0);
                GratificacaoDao dao = new GratificacaoDao();
                dao.remover(idGratificacao);
                modelo.removeRow(linhaSelecionada);
            } else {
                JOptionPane.showMessageDialog(ListarGratificacao.this,
                        "É necesário selecionar uma linha.");
            }
        }
    }

    public static void main(String[] args) {
        ListarGratificacao lc = new ListarGratificacao();
        lc.setVisible(true);
    }
}