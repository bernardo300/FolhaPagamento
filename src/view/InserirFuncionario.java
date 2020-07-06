package view;

import controller.EmpregadoDao;
import model.Empregado;
import model.Funcionario;
import model.Gerente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 

public class InserirFuncionario extends JFrame {
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbFuncao;
    private JLabel lbSalario;

    private JTextField txNome;
    private JTextField txSalario;
    JComboBox<String> boxFuncao;
 
    public InserirFuncionario(DefaultTableModel md) {
        super("inserir Funcionario");
        criaJanela();
        modelo = md;
    }
 
    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("         Nome.:   ");
        lbFuncao = new JLabel("         Funcao.:   ");
        lbSalario = new JLabel("         Salario.:   ");
        txNome = new JTextField(10);
        txSalario = new JTextField();
        txSalario.setEnabled(false);
 
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbFuncao);

        boxFuncao = new JComboBox();
        boxFuncao.addItem("Gerente");
        boxFuncao.addItem("Funcionario");
        boxFuncao.setSelectedIndex(1);
        txSalario.setText(String.valueOf(2500));
        boxFuncao.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String f = boxFuncao.getItemAt(boxFuncao.getSelectedIndex());
                        if (f.equals("Gerente")){
                            txSalario.setText(String.valueOf(4000));
                        }
                        else if (f.equals("Funcionario")){
                            txSalario.setText(String.valueOf(2500));
                        }
                    }
                }
        );

        painelFundo.add(boxFuncao);
        painelFundo.add(lbSalario);
        painelFundo.add(txSalario);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
 
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setVisible(true);
        btSalvar.addActionListener(new BtSalvarListener());
        btLimpar.addActionListener(new BtLimparListener());
    }
 
    private class BtSalvarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            Empregado empregado = null;
            String funcao = boxFuncao.getItemAt(boxFuncao.getSelectedIndex());
            if (funcao.equals("Gerente")){
                empregado = new Gerente();
                empregado.setNome(txNome.getText());
                empregado.setCargo(funcao);
                empregado.setSalario(Float.parseFloat(txSalario.getText()));

            }else if (funcao.equals("Funcionario")){
                empregado = new Funcionario();
                empregado.setNome(txNome.getText());
                empregado.setCargo(funcao);
                empregado.setSalario(Float.parseFloat(txSalario.getText()));
            }

            EmpregadoDao dao = new EmpregadoDao();
            dao.inserir(empregado);
            ListarFuncionario.pesquisar(modelo);
            setVisible(false);
        }
    }
 
 
 
    private class BtLimparListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txSalario.setText("");
        }
    }
}