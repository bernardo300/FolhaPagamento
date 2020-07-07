package controller;

import model.*;
import util.Conexao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GratificacaoDao {

    private final String INSERT = "INSERT INTO gratificacoes (id_funcionario, tipo, valor) VALUES (?, ?, ?)";
    private final String DELETE = "DELETE FROM gratificacoes WHERE id =?";
    private final String UPDATE = "UPDATE gratificacoes SET tipo=? , valor=? WHERE id=?";
    private final String LIST = "SELECT * FROM gratificacoes";
    private final String LISTBYID = "SELECT * FROM gratificacoes WHERE id=?";
    private final String LISTBYIDFUNCIONARIO = "SELECT * FROM gratificacoes WHERE id_funcionario=?";

    public void inserir(Gratificacao gratificacao,int idFuncionario) {
        if (gratificacao != null) {
            Connection conn = null;
            try {
                conn = Conexao.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);

                pstm.setInt(1, idFuncionario);
                pstm.setString(2,gratificacao.getTipo());
                pstm.setFloat(3, gratificacao.getValor());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "Gratificao cadastrada com sucesso");
                Conexao.fechaConexao(conn, pstm);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir contato no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O contato enviado por parâmetro está vazio");
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
            JOptionPane.showMessageDialog(null, "Erro ao excluir Gratificacao do banco de"
                    + "dados " + e.getMessage());
        }
    }

    public void atualizar(Gratificacao gratificacao) {
        if (gratificacao != null) {
            System.out.println(gratificacao.getTipo());
            Connection conn = null;
            try {
                conn = Conexao.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, gratificacao.getTipo());
                pstm.setFloat(2,gratificacao.getValor());
                pstm.setInt(3, gratificacao.getId());

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

    public List<Gratificacao> getGratificacao() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Gratificacao>gratificacaos = new ArrayList<>();

        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Gratificacao gratificacao;
                String tipo = rs.getString("tipo");
                if (tipo.equals("Desempenho")){
                    gratificacao = new GratificacaoDesempenho();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));
                    gratificacaos.add(gratificacao);

                }else if(tipo.equals("Hora Extra")){
                    gratificacao = new GratificacaoHoraExtra();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));
                    gratificacaos.add(gratificacao);
                }
            }
            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gratificao " + e.getMessage());
        }
        System.out.println(gratificacaos);
        return gratificacaos;
    }

    public Gratificacao getGratificacaoById(int idGratificacao) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Gratificacao gratificacao = null;

        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LISTBYID);
            pstm.setInt(1, idGratificacao);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                if (tipo.equals("Desempenho")){
                    gratificacao = new GratificacaoDesempenho();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));

                }else if(tipo.equals("Hora Extra")){
                    gratificacao = new GratificacaoHoraExtra();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));
                }
            }
            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gratificaoa" + e.getMessage());
        }
        return gratificacao;
    }

    /**
     * @param empregado com id setado
     * @return O contracheque do empregado
     * @exception NullPointerException caso o id do usuario seja null
     */
    public ContraCheque getContraChequeByFuncionario(Empregado empregado) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Gratificacao> gratificacaos = new ArrayList<>();
        ContraCheque contraCheque = new ContraCheque();

        try {
            conn = Conexao.getConexao();
            pstm = conn.prepareStatement(LISTBYIDFUNCIONARIO);
            pstm.setInt(1, empregado.getId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Gratificacao gratificacao = null;
                String tipo = rs.getString("tipo");
                if (tipo.equals("Desempenho")){
                    gratificacao = new GratificacaoDesempenho();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));
                    gratificacaos.add(gratificacao);

                }else if(tipo.equals("Hora Extra")){
                    gratificacao = new GratificacaoHoraExtra();
                    gratificacao.setId(rs.getInt("id"));
                    gratificacao.setTipo(rs.getString("tipo"));
                    gratificacao.setIdFuncionario(rs.getInt("id_funcionario"));
                    gratificacao.setValor(rs.getFloat("valor"));
                    gratificacaos.add(gratificacao);
                }
            }

            contraCheque = montarContracheque(empregado,gratificacaos);

            Conexao.fechaConexao(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gratificaoa" + e.getMessage());
        }
        return contraCheque;
    }

    /**
     * @param empregado Recebe um empregado
     * @param listGratificaoec Recebe uma lisat de gratificaoes
     * @return Contra Cheque do Mempregado
     */
    private ContraCheque montarContracheque(Empregado empregado,List<Gratificacao>listGratificaoec){
        ContraCheque c = new ContraCheque();
        c.setEmpregado(empregado);
        c.setSalarioBase(empregado.getSalario());
        c.setGratificacaos(listGratificaoec);

        float total = 0;
        for (Gratificacao g:listGratificaoec) {
            total += g.getValor();
        }
        c.setValorGratificacoes(total);
        c.setSalarioMensal(empregado.getSalario() + total);
        return  c;
    }
}
