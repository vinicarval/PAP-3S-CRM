/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Funcionario;
import br.com.crm.businessobject.SAC;
import br.com.crm.businessobject.Usuario;
import br.com.crm.businessobject.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author icaro
 */
public class VendaDao extends DaoBase<Object>{
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

    public void save(Object venda, Object funcionario) {
        try {
            if (venda instanceof Venda && funcionario instanceof Funcionario) {
                Venda vend = (Venda) venda;
                Funcionario func = (Funcionario) funcionario;
                Connection conn = this.conectar();
                String inserir = "INSERT INTO VENDA(CODVENDA,CODCLIENTE,CODPRODUTO,DATACOMPRA,ORIGEMVENDA) VALUES (?,?,?,?,?)";

                PreparedStatement sta;

                sta = conn.prepareStatement(inserir);
               // sta.setString(1, func.getCPF());
             // /  sta.setString(2, usu.getSenha());
                //sta.setString(3, usu.getNivelAcesso());
               // sta.setDate(4, vend.getDataCompra());
               // sta.setString(5, usu.getNivelAcesso());

                sta.execute();

                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }

    }
    
    @Override
    public void update(Object venda) {
        try {
            if (venda instanceof Venda ) {
                Venda vend = (Venda) venda;

                Connection conn = this.conectar();
              String atualizar = "UPDATE VENDA SET ";

                PreparedStatement sta;

                sta = conn.prepareStatement(atualizar);

               // sta.setString(1, usu.getSenha());
              //  sta.setInt(2, usu.getIdFunc());

                sta.executeUpdate();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    @Override
    public void delete(Object venda) {
        try {
           if (venda instanceof Venda ) {
                Venda vend = (Venda) venda;

                Connection conn = this.conectar();
                String remover = "DELETE FROM VENDA WHERE CODVENDA=?";

                PreparedStatement sta;
                sta = conn.prepareStatement(remover);
                //sta.setInt(1,);

                sta.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }
    
    public Venda find(String codVenda){
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT  CODVENDA, MODOPAGAMENTO, ORIGEMVENDA, DATACOMPRA FROM VENDA WHERE CODVENDA = "+ codVenda;
            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda(rs.getString("CODVENDA".toString()),
                        rs.getString("MODOPAGAMENTO"), rs.getString("ORIGEMVENDA"),
                        rs.getDate("DATACOMPRA"));

                this.desconectar(conn);
                return venda;
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return null;
    } 

    public List<Venda> List() {
        List<Venda> vendas = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT V.MODOPAGAMENTO,V.ORIGEMVENDA,V.DATACOMPRA,P.NOME, V.CODVENDA FROM VENDA V INNER JOIN PRODUTO P ON V.CODPRODUTO=P.CODPRODUTO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda(rs.getString("V.MODOPAGAMENTO"),
                        rs.getString("V.ORIGEMVENDA"),
                     (Date)   rs.getDate("V.DATACOMPRA"), rs.getString("P.NOME"),rs.getString("CODVENDA".toString()));
                vendas.add(venda);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return vendas;
    }
   
    public List<Venda> ListVendaPorCliente(Cliente cli) {
        List<Venda> vendas = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT V.MODOPAGAMENTO AS MODOPAGAMENTO,V.ORIGEMVENDA AS ORIGEMVENDA,V.DATACOMPRA AS DATACOMPRA,P.NOME AS NOMEPRODUTO, V.CODVENDA AS CODVENDA FROM VENDA V INNER JOIN PRODUTO P ON V.CODPRODUTO=P.CODPRODUTO INNER JOIN CLIENTE C ON V.CODCLIENTE = C.CODCLIENTE WHERE C.CODCLIENTE = "+cli.getCodCliente();

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda(rs.getString("MODOPAGAMENTO"),
                        rs.getString("ORIGEMVENDA"),
                     (Date)   rs.getDate("DATACOMPRA"), rs.getString("NOMEPRODUTO"), rs.getString("CODVENDA".toString()));
                vendas.add(venda);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return vendas;
    }
   
    public int qtdVendas(Cliente cli){
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT COUNT(*) AS COUNT FROM VENDA WHERE CODCLIENTE ="+cli.getCodCliente();

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();
            
            int resultado = 0;
            while (rs.next()) {
                return rs.getInt("COUNT");
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
        return 0;
    }
}
