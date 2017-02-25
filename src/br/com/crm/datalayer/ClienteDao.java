/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Endereco;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author icaro
 */
public class ClienteDao extends DaoBase implements IClienteDao {

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
     * @param clie
     * @param ende Se clie for uma instancia de CLIENTE e ende uma instancia de
     * ENDERECO ira inserir um cliente com endereco,telefone e redesocial no
     * banco Caso contrario não fara nada
     */
    @Override
    public void save(Object clie, Object ende) {
        try {
            if (clie instanceof Cliente && ende instanceof Endereco) {
                Cliente cli = (Cliente) clie;
                Endereco end = (Endereco) ende;
                Connection conn = this.conectar();
                String inserir = "INSERT INTO CLIENTE(CODCLIENTE,CLASSIFICACAO,DATANASCIMENTO,SEXO,BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP,EMAIL,CPF,NOME) VALUES ((select (nvl(max(CODCLIENTE),0)+1) FROM CLIENTE),?,?,?,?,?,?,?,?,?,?,?)";
                String inserirTel = "INSERT INTO TELEFONECLI(TELEFONE,CODCLIENTE) VALUES (?,(select (nvl(max(CODCLIENTE),0)+1) FROM TELEFONECLI))";
                String inserirRede = "INSERT INTO REDESOCIAL VALUES (?,(select (nvl(max(CODCLIENTE),0)+1) FROM REDESOCIAL))";
                PreparedStatement sta;
                PreparedStatement staTel;
                PreparedStatement staRede;

                staTel = conn.prepareStatement(inserirTel);
                staTel.setString(1, cli.getTelefone());
                staRede = conn.prepareStatement(inserirRede);
                staRede.setString(1, cli.getRedeSocial());

                sta = conn.prepareStatement(inserir);
                sta.setString(1, cli.getClassificacao());
                sta.setDate(2, (Date) cli.getdataNascimento());
                sta.setString(3, cli.getSexo() + "");
                sta.setString(4, end.getBairro());
                sta.setString(5, end.getCidade());
                sta.setString(6, end.getEstado());
                sta.setString(7, end.getRua());
                sta.setString(8, end.getNumero());
                sta.setString(9, end.getCep());
                sta.setString(10, cli.getEmail());
                sta.setString(11, cli.getCPF());
                sta.setString(12, cli.getNome());

                sta.execute();
                staTel.execute();
                staRede.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }

    }

    /**
     *
     * @param clie
     * @param ende Se clie for uma instancia de CLIENTE e ende uma instancia de
     * ENDERECO ira Atualizar um cliente com endereco,telefone e redesocial no
     * banco Caso contrario não fara nada
     */
    @Override
    public void update(Object clie, Object ende) {
        try {
            if (clie instanceof Cliente && ende instanceof Endereco) {
                Cliente cli = (Cliente) clie;
                Endereco end = (Endereco) ende;
                Connection conn = this.conectar();
                String atualizar = "UPDATE CLIENTE SET CLASSIFICACAO=?,DATANASCIMENTO=?,SEXO=?,BAIRRO=?,CIDADE=?,ESTADO=?,RUA=?,NUMERO=?,CEP=?,EMAIL=?,CPF=?,NOME=? WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                String atualizarTel = "UPDATE TELEFONECLI SET TELEFONE=? WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                String atualizarRede = "UPDATE REDESOCIAL SET REDESOCIAL=? WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                PreparedStatement sta;
                PreparedStatement staTel;
                PreparedStatement staRede;

                sta = conn.prepareStatement(atualizar);
                staTel = conn.prepareStatement(atualizarTel);
                staRede = conn.prepareStatement(atualizarRede);

                sta.setString(1, cli.getClassificacao());
                sta.setDate(2, (Date) cli.getdataNascimento());
                sta.setString(3, cli.getSexo() + "");
                sta.setString(4, end.getBairro());
                sta.setString(5, end.getCidade());
                sta.setString(6, end.getEstado());
                sta.setString(7, end.getRua());
                sta.setString(8, end.getNumero());
                sta.setString(9, end.getCep());
                sta.setString(10, cli.getEmail());
                sta.setString(11, cli.getCPF());
                sta.setString(12, cli.getNome());

                sta.setString(13, cli.getCPF());//WHERE

                staTel.setString(1, cli.getTelefone());
                staTel.setString(2, cli.getCPF());

                staRede.setString(1, cli.getRedeSocial());
                sta.executeUpdate();
                staTel.executeUpdate();
                staRede.executeUpdate();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    @Override
    public void updateApenasCliente(Object clie) {
        try {
            if (clie instanceof Cliente) {
                Cliente cli = (Cliente) clie;

                Connection conn = this.conectar();
                String atualizar = "UPDATE CLIENTE SET CLASSIFICACAO=?,DATANASCIMENTO=?,SEXO=?,EMAIL=?,CPF=?,NOME=? WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='" + cli.getCPF() + "')";
                PreparedStatement sta;

                sta = conn.prepareStatement(atualizar);

                sta.setString(1, cli.getClassificacao());
                sta.setDate(2, (Date) cli.getdataNascimento());
                sta.setString(3, cli.getSexo() + "");
                sta.setString(4, cli.getEmail());
                sta.setString(5, cli.getCPF());
                sta.setString(6, cli.getNome());

                sta.executeUpdate();

                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }

    /**
     *
     * @param clie Se clie for uma instancia de cliente ira deletar um cliente
     * com seu endereco, telefone e redesocial do banco Caso contrario não fara
     * nada
     */
    @Override
    public void delete(Object clie) {
        try {
            if (clie instanceof Cliente) {
                Cliente cli = (Cliente) clie;

                Connection conn = this.conectar();
                String remover = "DELETE FROM CLIENTE WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                PreparedStatement sta;
                String removerTel = "DELETE FROM TELEFONECLI WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                String removerRede = "DELETE FROM REDESOCIAL WHERE CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='?')";
                PreparedStatement sta2;
                PreparedStatement staRede;
                sta2 = conn.prepareStatement(removerTel);
                sta = conn.prepareStatement(remover);
                staRede = conn.prepareStatement(removerRede);
                sta.setString(1, cli.getCPF());
                sta2.setString(1, cli.getCPF());
                staRede.setString(1, cli.getCPF());
                sta.execute();
                sta2.execute();
                staRede.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    /**
     *
     * @return uma lista com TODOS os registros de clientes EXCETO ENDERECO
     */
    @Override
    public List listOrdenadoClassificacao() {
        List<Cliente> cli = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT C.CLASSIFICACAO AS CLASSIFICACAO ,C.DATANASCIMENTO AS DATANASCIMENTO ,C.SEXO AS SEXO,C.EMAIL AS EMAIL ,C.CPF AS CPF ,C.NOME AS NOME , T.TELEFONE AS TELEFONE , R.REDESOCIAL AS REDESOCIAL, C.CODCLIENTE AS CODCLIENTE  FROM CLIENTE C  INNER JOIN TELEFONECLI T ON C.CODCLIENTE = T.CODCLIENTE INNER JOIN REDESOCIAL R ON C.CODCLIENTE = R.CODCLIENTE ORDER BY CLASSIFICACAO DESC";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("CLASSIFICACAO"),
                        (Date) rs.getDate("DATANASCIMENTO"), rs.getString("SEXO").charAt(0),
                        rs.getString("EMAIL"), rs.getString("CPF"), rs.getString("NOME"), rs.getString("TELEFONE"), rs.getString("REDESOCIAL"), rs.getString("CODCLIENTE".toString()));
                cli.add(cliente);

            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return cli;
    }

    @Override
    public List listComQtdCompras() {
        List<Cliente> cli = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT C.CLASSIFICACAO AS CLASSIFICACAO ,C.DATANASCIMENTO AS DATANASCIMENTO ,C.SEXO AS SEXO,C.EMAIL AS EMAIL ,C.CPF AS CPF ,C.NOME AS NOME , T.TELEFONE AS TELEFONE , R.REDESOCIAL AS REDESOCIAL,C.CODCLIENTE AS CODCLIENTE  FROM CLIENTE C  INNER JOIN TELEFONECLI T ON C.CODCLIENTE = T.CODCLIENTE INNER JOIN REDESOCIAL R ON C.CODCLIENTE = R.CODCLIENTE ORDER BY CLASSIFICACAO DESC";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("CLASSIFICACAO"),
                        (Date) rs.getDate("DATANASCIMENTO"), rs.getString("SEXO").charAt(0),
                        rs.getString("EMAIL"), rs.getString("CPF"), rs.getString("NOME"), rs.getString("TELEFONE"), rs.getString("REDESOCIAL"));
                cli.add(cliente);

            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return cli;
    }

    /**
     *
     * @return a lista de endereços dos Clientes
     */
    @Override
    public List<Endereco> listEnd() {
        List<Endereco> end = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP FROM CLIENTE";
            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco(rs.getString("BAIRRO"),
                        rs.getString("CIDADE"), rs.getString("ESTADO"),
                        rs.getString("RUA"), rs.getString("NUMERO"), rs.getString("CEP"));
                end.add(endereco);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return end;
    }

    /**
     *
     * @param Cpf
     * @return um registro do cliente sem endereco usando o CPF como parametro
     */
    @Override
    public Cliente find(String Cpf) {
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT C.CLASSIFICACAO AS CLASSIFICACAO,C.DATANASCIMENTO AS DATANASCIMENTO,C.SEXO AS SEXO,C.EMAIL AS EMAIL,C.CPF AS CPF,C.NOME AS NOME,T.TELEFONE AS TELEFONE,R.REDESOCIAL AS REDESOCIAL, C.CODCLIENTE FROM CLIENTE C  INNER JOIN TELEFONECLI T ON C.CODCLIENTE = T.CODCLIENTE INNER JOIN REDESOCIAL R ON C.CODCLIENTE = R.CODCLIENTE WHERE C.CODCLIENTE=(SELECT CODCLIENTE FROM CLIENTE WHERE CPF='" + Cpf + "')";
            PreparedStatement stm = conn.prepareStatement(consulta);
            //stm.setString(1, Cpf);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("CLASSIFICACAO"),
                        rs.getDate("DATANASCIMENTO"), rs.getString("SEXO").charAt(0),
                        rs.getString("EMAIL"), rs.getString("CPF"), rs.getString("NOME"), rs.getString("TELEFONE"), rs.getString("REDESOCIAL"), rs.getString("CODCLIENTE".toString()));

                this.desconectar(conn);
                return cliente;
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return null;
    }

    /**
     *
     * @param Cpf
     * @return o endereco do cliente usando o CPF como parametro
     */
    @Override
    public Endereco findEndereco(String Cpf) {
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP FROM CLIENTE WHERE CPF=?";
            PreparedStatement stm = conn.prepareStatement(consulta);
            stm.setString(1, Cpf);
            ResultSet rs = stm.executeQuery();
            Endereco endereco = new Endereco(rs.getString("BAIRRO"),
                    rs.getString("CIDADE"), rs.getString("ESTADO"),
                    rs.getString("RUA"), rs.getString("NUMERO"), rs.getString("CEP"));
            return endereco;
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return null;
    }

}
