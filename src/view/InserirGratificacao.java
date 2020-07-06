package view;

import controller.EmpregadoDao;
import model.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InserirGratificacao extends JFrame {

    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;

    private JLabel lbTipo;
    private JLabel lbValor;
    private JLabel lbId;

    private JTextField txId;
    private JTextField txValor;
    JComboBox<String> boxTipo;

    Empregado empregado;
    private int linhaSelecionada;


    public InserirGratificacao(DefaultTableModel md, int id, int linha) {
        super("Adicionar Gratificacao");
        criaJanela();
        modelo = md;
        EmpregadoDao dao = new EmpregadoDao();
        empregado = dao.getFuncionarioById(id);
        txId.setText(Integer.toString(empregado.getId()));
        txValor.setText(String.valueOf(empregado.getSalario() * 0.01));
        txValor.setEnabled(false);
        linhaSelecionada = linha;
    }

    private void verificaFuncaoAtiva(String funcao1) {
        if (funcao1.equals("Desempenho")){
            txValor.setText(String.valueOf(empregado.getSalario() * 0.05f));
        }
        if (funcao1.equals("Hora Extra")){
            txValor.setText(String.valueOf(empregado.getSalario() * 0.01f));
        }
    }

    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");

        lbId = new JLabel("Id.:");
        lbTipo = new JLabel("Tipo.:");
        lbValor = new JLabel("Valor.:");
        txValor = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbTipo);

        boxTipo = new JComboBox();
        boxTipo.addItem("Desempenho");
        boxTipo.addItem("Hora Extra");
        boxTipo.setSelectedIndex(1);
        boxTipo.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String f = boxTipo.getItemAt(boxTipo.getSelectedIndex());
                        verificaFuncaoAtiva(f);


                    }
                }
        );

        painelFundo.add(boxTipo);
        painelFundo.add(lbValor);
        painelFundo.add(txValor);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 160);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);

        btSalvar.addActionListener(new
                InserirGratificacao.BtSalvarListener());
        btLimpar.addActionListener(new
                InserirGratificacao.BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Gratificacao gratificacao = null;
            String tipo = boxTipo.getItemAt(boxTipo.getSelectedIndex());
            EmpregadoDao dao = new EmpregadoDao();
            if (tipo.equals("Desempenho")){
                gratificacao = new GratificacaoDesempenho();
                empregado.setGratificacao(gratificacao);
            }
            else if(tipo.equals("Hora Extra")){
                gratificacao = new GratificacaoHoraExtra();
                empregado.setGratificacao(gratificacao);
            }
            dao.addGratificacao(empregado);
            setVisible(false);
        }
    }

    private class BtLimparListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            txValor.setText("");
        }
    }
}
