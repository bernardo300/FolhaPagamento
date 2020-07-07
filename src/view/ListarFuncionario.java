package view;

import controller.EmpregadoDao;
import model.Empregado;
import model.Folha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ListarFuncionario extends JFrame {
 
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private JButton btGratificacao;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListarFuncionario() {
        super("Sistema Funcionarios");
        criaJTable();
        criaJanela();
    }
 
    public void criaJanela() {
        btInserir = new JButton("Inserir");
        btExcluir = new JButton("Excluir");
        btEditar = new JButton("Editar");
        btGratificacao = new JButton("Gratificacao");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btGratificacao);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        getContentPane().add(painelFundo);


        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();
        // Adiciona a barra de menu ao  frame
        setJMenuBar(menuBar);
        // Define e adiciona dois menus drop down na barra de menus
        JMenu funcionarioMenu = new JMenu("Funcionario");
        JMenu gratificacaoMenu = new JMenu("Gratificacoes");
        menuBar.add(funcionarioMenu);
        menuBar.add(gratificacaoMenu);
        // Cria e adiciona um item simples para o menu
        JMenuItem fNovo = new JMenuItem("Novo");
        JMenuItem fLista = new JMenuItem("Funcionarios");
        JMenuItem fFolha = new JMenuItem("Folha");

        JMenuItem gNovo = new JMenuItem("Nova");
        JMenuItem gLista = new JMenuItem("Graticacoes");

        funcionarioMenu.add(fNovo);
        funcionarioMenu.add(fLista);
        funcionarioMenu.add(fFolha);

        gratificacaoMenu.add(gNovo);
        gratificacaoMenu.add(gLista);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setResizable(false);
        setResizable(false);

        fLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar(modelo);
                painelBotoes.setVisible(true);
            }
        });

        gLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarGratificacao ic = new ListarGratificacao();
                ic.setVisible(true);
            }
        });

        fFolha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarFolha ic = new ListarFolha();
                ic.setVisible(true);
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
        btInserir.addActionListener(new BtInserirListener());
        btEditar.addActionListener(new BtEditarListener());
        btExcluir.addActionListener(new BtExcluirListener());
        btGratificacao.addActionListener(new BtGratificacaoListener());
    }

    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        EmpregadoDao dao = new EmpregadoDao();

        for (Empregado c : dao.getFuncionarios()) {
            modelo.addRow(new Object[]{c.getId(), c.getNome(),
                    c.getCargo(), c.getSalario()});
        }
    }

    private void criaJTable() {

        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Funcao");
        modelo.addColumn("Salario");
        tabela.setFont(new Font("Serif", Font.PLAIN, 18));
        tabela.setRowHeight(26);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        pesquisar(modelo);
    }

 
    private class BtInserirListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            InserirFuncionario ic = new InserirFuncionario(modelo);
            ic.setVisible(true);
        }
    }

    private class BtGratificacaoListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idFuncionario = (int) tabela.getValueAt(linhaSelecionada, 0);
                InserirGratificacao ic = new InserirGratificacao(modelo, idFuncionario, linhaSelecionada);
                ic.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(ListarFuncionario.this,
                        "É necesário selecionar uma linha.");
            }
        }
    }

    private class BtEditarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idFuncionario = (int) tabela
                .getValueAt(linhaSelecionada, 0);
                AtualizarFuncionario ic = new AtualizarFuncionario(modelo, idFuncionario, linhaSelecionada);
                ic.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(ListarFuncionario.this,
                "É necesário selecionar uma linha.");
            }
        }
    }
 
    private class BtExcluirListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int idContato = (int) 
                tabela.getValueAt(linhaSelecionada, 0);
                EmpregadoDao dao = new EmpregadoDao();
                dao.remover(idContato);
                modelo.removeRow(linhaSelecionada);
            } else {
                JOptionPane.showMessageDialog(ListarFuncionario.this,
                        "É necesário selecionar uma linha.");
            }
        }
    }

}