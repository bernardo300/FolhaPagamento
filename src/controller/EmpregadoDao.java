package controller;

import model.*;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EmpregadoDao {

    private final String INSERT = "INSERT INTO funcionarios (nome, funcao, salario) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE funcionarios SET nome=?, funcao=?, salario=? WHERE id=?";
    private final String DELETE = "DELETE FROM funcionarios WHERE id =?";
    private final String LIST = "SELECT * FROM funcionarios";
    private final String LISTBYID = "SELECT * FROM funcionarios WHERE id=?";
    private final String LISTFOLHA ="SELECT f.id AS codF, f.nome, f.funcao, f.salario, COUNT(g.id) as quantGratificacao, SUM(g.valor) As gratificacao\n" +
                                    "FROM gratificacoes as g\n" +
                                    "RIGHT JOIN funcionarios as f\n" +
                                    "ON f.id = g.id_funcionario\n" +
                                    "GROUP BY f.id\n" +
                                    "ORDER BY f.nome";

    public void inserir(Empregado empregado) {
        if (empregado != null) {
            Connection conn = null;
            try {
                conn = Conexao.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
 
                pstm.setString(1, empregado.getNome());
                pstm.setString(2, empregado.getCargo());
                pstm.setFloat(3, empregado.getSalario());
 
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso");
                Conexao.fechaConexao(conn, pstm);
 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir contato no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O contato enviado por parâmetro está vazio");
        }
    }
 
    public void atualizar(Empregado empregado) {
        if (empregado != null) {
            Connection conn = null;
            try {
                conn = Conexao.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, empregado.getNome());
                pstm.setString(2, empregado.getCargo());
                pstm.setFloat(3, empregado.getSalario());
                pstm.setInt(4, empregado.getId());
 
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Contato alterado com sucesso");
                Conexao.fechaConexao(conn);
 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar contato no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O contato enviado por parâmetro está vazio");
        }
 
 
    }
 
    public void remover(int id) {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);
 
            pstm.setInt(1, id);
 
            pstm.execute();
            Conexao.fechaConexao(conn, pstm);
 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato do banco de"
                    + "dados " + e.getMessage());
        }
    }
 
    public List<Empregado> getFuncionarios() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Empregado> empregados = new ArrayList<Empregado>();
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Empregado contato;
                if (rs.getString("funcao").equals("Gerente")){
                    contato = new Gerente();

                    contato.setId(rs.getInt("id"));
                    contato.setNome(rs.getString("nome"));
                    contato.setCargo(rs.getString("funcao"));
                    contato.setSalario(rs.getFloat("salario"));
                    empregados.add(contato);
                    
                }
                else if (rs.getString("funcao").equals("Funcionario")){
                    contato = new Funcionario();
                    contato.setId(rs.getInt("id"));
                    contato.setNome(rs.getString("nome"));
                    contato.setCargo(rs.getString("funcao"));
                    contato.setSalario(rs.getFloat("salario"));
                    empregados.add(contato);

                }
                
            }
            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
        }
        return empregados;
    }

    public void addGratificacao(Empregado empregado){
        GratificacaoDao dao = new GratificacaoDao();
        dao.inserir(empregado.getGratificacao(), empregado.getId());
    }

    public List<Folha> getFolhaPagamento() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<Folha> folhas = new ArrayList<Folha>();
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LISTFOLHA);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Folha folha = new Folha();

                folha.setIdFuncionario(rs.getInt("codF"));
                folha.setNomeEmpregado(rs.getString("nome"));
                folha.setFuncaoEmpregado(rs.getString("funcao"));
                folha.setSalarioBase(rs.getFloat("salario"));
                folha.setTotalGratificacoes(rs.getInt("quantGratificacao"));
                folha.setValorGratificacoes(rs.getFloat("gratificacao"));
                folha.setSalarioMensal(folha.getSalarioBase() + folha.getValorGratificacoes());
                folhas.add(folha);

            }
            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
        }
        return folhas;
    }
 
    public Empregado getFuncionarioById(int id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Empregado empregado = null;
        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if (rs.getString("funcao").equals("Gerente")){
                    empregado = new Gerente();
                    empregado.setId(rs.getInt("id"));
                    empregado.setNome(rs.getString("nome"));
                    empregado.setCargo(rs.getString("funcao"));
                    empregado.setSalario(rs.getFloat("salario"));
                    
                }
                if (rs.getString("funcao").equals("Funcionario")){
                    empregado = new Funcionario();
                    empregado.setId(rs.getInt("id"));
                    empregado.setNome(rs.getString("nome"));
                    empregado.setCargo(rs.getString("funcao"));
                    empregado.setSalario(rs.getFloat("salario"));
                }
            }
            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
        }
        return empregado;
    }

}