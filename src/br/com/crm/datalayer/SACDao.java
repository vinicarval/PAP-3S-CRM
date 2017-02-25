/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Funcionario;
import br.com.crm.businessobject.SAC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author icaro
 */
public class SACDao extends DaoBase<Object> implements ISACDao {

    public Connection conectar() {
        /*Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn
                    = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=10.20.250.13)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=DBFACET)))", "AL239677", "AL239677");

        } catch (SQLException erro) {
            System.out.println("ERRO de conexão");

        } catch (ClassNotFoundException erro) {
            System.out.println("ERRO de driver");

        }
        return conn;
                */
        Conexao con = new Conexao();
        
        try{
        return con.conectar();
        }
        catch(Exception e ){
            System.out.println("Falha ao conectar");
        }
        return con.conectar();
    }

    public void desconectar(Connection conn) {
        /*try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectou");
            }

        } catch (SQLException erro) {
            System.out.println("Não desconectou");
        }
                */
        
        Conexao con = new Conexao();
        try{
         con.desconectar( conn);
        }
        catch(Exception e ){
            System.out.println("Falha ao desconectar");
        }

    }

    /**
     *
     * @param sac
     * @param funcionario
     * @param cliente
     * Insere se sac for instancia de SAC,funcionario instancia de Funcionario e cliente instancia Cliente
     * Caso contrario não faz nada
     */
    @Override
    public void save(Object sac, Object funcionario, Object cliente) {
        try {
            if (sac instanceof SAC && funcionario instanceof Funcionario && cliente instanceof Cliente) {
                SAC atend = (SAC) sac;
                Funcionario func = (Funcionario) funcionario;
                Cliente cli = (Cliente) cliente;
                Connection conn = this.conectar();
                String inserir = "INSERT INTO SAC(PROTOCOLO,CODFUNCIONARIO,CODCLIENTE,TIPOLIGACAO) VALUES (?,(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?'),(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?'),?)";
                String inserirAvaliacao = "INSERT INTO AVALIACAOCHAMADA(PROTOCOLO,CODCLIENTE,AVALIACAO) VALUES (?, (SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?'),?)";
                String inserirEstado = "INSERT INTO ESTADOCHAMADA(PROTOCOLO,CODFUNCIONARIO,ESTADOCHAMADA) VALUES (?,(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?'),?)";
                PreparedStatement sta;
                PreparedStatement staAvaliacao;
                PreparedStatement staEstado;

                staAvaliacao = conn.prepareStatement(inserirAvaliacao);
                staAvaliacao.setInt(1, atend.getProtocolo());
                staAvaliacao.setString(2, cli.getCPF());
                staAvaliacao.setInt(3, atend.getAvaliacao());

                staEstado = conn.prepareStatement(inserirEstado);
                staEstado.setInt(1, atend.getProtocolo());
                staEstado.setString(2, func.getCPF());
                staEstado.setString(3, atend.getEstadoChamada());

                sta = conn.prepareStatement(inserir);
                sta.setInt(1, atend.getProtocolo());
                sta.setString(2, func.getCPF());
                sta.setString(3, cli.getCPF());
                sta.setString(4, atend.getTipoLigacao());

                sta.execute();
                staAvaliacao.execute();
                staEstado.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }

    }
    
    /**
     *
     * @param sac
     * Atualiza se sac for uma instancia de SAC
     * Caso contrario não faz nada
     */
    @Override
    public void update(Object sac) {
        try {
            if (sac instanceof SAC) {
                SAC atend = (SAC) sac;

                Connection conn = this.conectar();
                String atualizar = "UPDATE ESTADOCHAMADA SET ESTADOCHAMADA=? WHERE PROTOCOLO=?";

                PreparedStatement sta;

                sta = conn.prepareStatement(atualizar);

                sta.setString(1, atend.getEstadoChamada());
                sta.setInt(2, atend.getProtocolo());

                sta.executeUpdate();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    /**
     *
     * @param sac
     * Deleta se sac for uma instancia de SAC
     * Caso contrario não faz nada
     */
    @Override
    public void delete(Object sac) {
        try {
            if (sac instanceof SAC) {
                SAC atend = (SAC) sac;

                Connection conn = this.conectar();
                String remover = "DELETE FROM SAC WHERE PROTOCOLO=?";
                String removerAvaliacao = "DELETE FROM AVALIACAOCHAMADA WHERE PROTOCOLO='?')";
                String removerEstado = "DELETE FROM ESTADOCHAMADA WHERE PROTOCOLO='?')";
                PreparedStatement sta;
                PreparedStatement staAvaliacao;
                PreparedStatement staEstado;
                staAvaliacao = conn.prepareStatement(removerAvaliacao);
                sta = conn.prepareStatement(remover);
                staEstado = conn.prepareStatement(removerEstado);
                sta.setInt(1, atend.getProtocolo());
                staAvaliacao.setInt(1, atend.getProtocolo());
                staEstado.setInt(1, atend.getProtocolo());

                staAvaliacao.execute();
                staEstado.execute();
                sta.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    /**
     *
     * @return uma lista com TODOS os registros de SAC
     */
    @Override
    public List list() {
        List<SAC> sacs = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT S.PROTOCOLO,S.TIPOLIGACAO,A.AVALIACAO,E.ESTADOCHAMADA FROM SAC S INNER JOIN AVALIACAOCHAMADA A ON S.PROTOCOLO=A.PROTOCOLO INNER JOIN ESTADOCHAMADA E ON A.PROTOCOLO=E.PROTOCOLO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SAC sac = new SAC(rs.getInt("S.PROTOCOLO"),
                        rs.getString("S.TIPOLIGACAO"),
                        rs.getInt("A.AVALIACAO"), rs.getString("E.ESTADOCHAMADA"));
                sacs.add(sac);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return sacs;
    }

    /**
     *
     * @param protocolo
     * @return um registro de sac
     */
    @Override
    public SAC find(int protocolo) {
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT S.PROTOCOLO,S.TIPOLIGACAO,A.AVALIACAO,E.ESTADOCHAMADA FROM SAC S INNER JOIN AVALIACAOCHAMADA A ON S.PROTOCOLO=A.PROTOCOLO INNER JOIN ESTADOCHAMADA E ON A.PROTOCOLO=E.PROTOCOLO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();
            SAC sac = new SAC(rs.getInt("S.PROTOCOLO"),
                    rs.getString("S.TIPOLIGACAO"),
                    rs.getInt("A.AVALIACAO"), rs.getString("E.ESTADOCHAMADA"));

            this.desconectar(conn);
            return sac;
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return null;
    }

}
