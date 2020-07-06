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

public class AtualizarFuncionario extends JFrame {
 
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;

    private JLabel lbNome;
    private JLabel lbFuncao;
    private JLabel lbSalario;
    private JLabel lbId;

    private JTextField txNome;
    private JTextField txId;
    private JTextField txSalario;
    JComboBox<String> boxFuncao;
    Empregado empregado;
    private int linhaSelecionada;
 
    public AtualizarFuncionario(DefaultTableModel md, int id, int linha) {
        super("Editar Funcionario");
        criaJanela();
        modelo = md;
        EmpregadoDao dao = new EmpregadoDao();
        empregado = dao.getFuncionarioById(id);
        txId.setText(Integer.toString(empregado.getId()));
        txNome.setText(empregado.getNome());
        boxFuncao.setSelectedItem(empregado.getCargo());
        txSalario.setText(String.valueOf(empregado.getSalario()));
        linhaSelecionada = linha;
    }

    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");

        lbId = new JLabel("Id:");
        lbNome = new JLabel("Nome.:");
        lbFuncao = new JLabel("Funcao.:");
        lbSalario = new JLabel("Salario.:");

        txNome = new JTextField(10);
        txSalario = new JTextField();
        txId = new JTextField();
        txId.setEnabled(false);

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 4));

        painelFundo.add(lbId);
        painelFundo.add(txId);
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
        btSalvar.addActionListener(new AtualizarFuncionario.BtSalvarListener());
        btLimpar.addActionListener(new AtualizarFuncionario.BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {
 
        public void actionPerformed(ActionEvent e) {
            Empregado empregado = null;
            String funcao = boxFuncao.getItemAt(boxFuncao.getSelectedIndex());
            if (funcao.equals("Gerente")){
                empregado = new Gerente();
                empregado.setId(Integer.parseInt(txId.getText()));
                empregado.setNome(txNome.getText());
                empregado.setCargo(funcao);
                empregado.setSalario(Float.parseFloat(txSalario.getText()));
            }
            else if(funcao.equals("Funcionario")){
                empregado = new Funcionario();
                empregado.setId(Integer.parseInt(txId.getText()));
                empregado.setNome(txNome.getText());
                empregado.setCargo(funcao);
                empregado.setSalario(Float.parseFloat(txSalario.getText()));
            }
 
            EmpregadoDao dao = new EmpregadoDao();
            dao.atualizar(empregado);
            modelo.removeRow(linhaSelecionada);
            modelo.addRow(new Object[]{empregado.getId(),
            empregado.getNome(), empregado.getCargo(), empregado.getSalario()});
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