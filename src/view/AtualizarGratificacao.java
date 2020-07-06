package view;

import controller.EmpregadoDao;
import controller.GratificacaoDao;
import model.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AtualizarGratificacao extends JFrame {

    private DefaultTableModel modelo = new DefaultTableModel();

    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;

    private JLabel lbValor;
    private JLabel lbTipo;
    private JLabel lbId;

    private JTextField txValor;
    private JTextField txId;
    JComboBox<String> boxTipo;

    Gratificacao gratificacao;
    private int linhaSelecionada;
    private int idFuncionario;
    private Empregado empregado;

    public AtualizarGratificacao(DefaultTableModel md, int idG,int idF, int linha) {
        super("Editar Gratificacao");
        criaJanela();
        modelo = md;
        EmpregadoDao daoE = new EmpregadoDao();
        empregado = daoE.getFuncionarioById(idF);
        GratificacaoDao daoG = new GratificacaoDao();
        gratificacao = daoG.getGratificacaoById(idG);
        txId.setText(Integer.toString(gratificacao.getId()));
        boxTipo.setSelectedItem(gratificacao.getTipo());
        txValor.setText(String.valueOf(gratificacao.getValor()));
        linhaSelecionada = linha;
    }

    private void verificaGratificacaoAtiva(String gratificao1) {
        if (gratificao1.equals("Desempenho")){
            txValor.setText(String.valueOf(empregado.getSalario() * 0.05f));
        }
        if (gratificao1.equals("Hora Extra")){
            txValor.setText(String.valueOf(empregado.getSalario() * 0.01f));
        }
    }
    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbValor = new JLabel("Valor.:");
        lbTipo = new JLabel("Tipo.:");
        lbId = new JLabel("Id.:");
        txValor = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbValor);
        painelFundo.add(txValor);
        painelFundo.add(lbTipo);

        boxTipo = new JComboBox();
        boxTipo.addItem("Desempenho");
        boxTipo.addItem("Hora Extra");
        boxTipo.setSelectedIndex(1);
        boxTipo.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String f = boxTipo.getItemAt(boxTipo.getSelectedIndex());
                        verificaGratificacaoAtiva(f);
                    }
                }
        );


        painelFundo.add(boxTipo);

        //painelFundo.add(txTipo);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 160);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);

        btSalvar.addActionListener(new AtualizarGratificacao.BtSalvarListener());
        btLimpar.addActionListener(new AtualizarGratificacao.BtLimparListener());
    }

    private class BtLimparListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            txValor.setText("");
        }
    }

    private class BtSalvarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Gratificacao gratificacao1 = null;
            String tipo = boxTipo.getItemAt(boxTipo.getSelectedIndex());
            if (tipo.equals("Desempenho")){
                gratificacao1 = new GratificacaoDesempenho();
                gratificacao1.setId(Integer.parseInt(txId.getText()));
                gratificacao1.setTipo(tipo);
                gratificacao1.setIdFuncionario(empregado.getId());
                gratificacao1.setValor(Float.parseFloat(txValor.getText()));
            }
            else if(tipo.equals("Hora Extra")){
                gratificacao1 = new GratificacaoHoraExtra();
                gratificacao1.setId(Integer.parseInt(txId.getText()));
                gratificacao1.setTipo(tipo);
                gratificacao1.setIdFuncionario(empregado.getId());
                gratificacao1.setValor(Float.parseFloat(txValor.getText()));
            }

            GratificacaoDao dao = new GratificacaoDao();
            dao.atualizar(gratificacao1);
            modelo.removeRow(linhaSelecionada);
            modelo.addRow(new Object[]{gratificacao1.getId(),
                    gratificacao1.getTipo(), gratificacao1.getIdFuncionario(), gratificacao1.getValor()});
            setVisible(false);
        }
    }


}