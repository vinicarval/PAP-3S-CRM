/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Funcionario;
import br.com.crm.businessobject.Produto;
import br.com.crm.businessobject.ProdutoSAC;
import br.com.crm.businessobject.ProdutoVenda;
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
public class ProdutoDAO extends DaoBase<Object>{
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

    public void save(Object produto) {
        try {
            if (produto instanceof Produto ) {
                Produto prod = (Produto) produto;
                
                Connection conn = this.conectar();
                String inserir = "INSERT INTO PRODUTO(CODPRODUTO,NOME,DESCRICAO) VALUES (?,?,?)";

                PreparedStatement sta;

                sta = conn.prepareStatement(inserir);
                sta.setInt(1, prod.getIdProd());
                sta.setString(2, prod.getNome());
                sta.setString(3, prod.getDescricao());


                sta.execute();

                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }

    }
    
    @Override
    public void update(Object produto) {
        try {
            if (produto instanceof Produto ) {
                Produto prod = (Produto) produto;

                Connection conn = this.conectar();
              String atualizar = "UPDATE PRODUTO SET NOME = "+prod.getNome()+",DESCRICAO = "+prod.getDescricao();

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
    public void delete(Object produto) {
        try {
            if (produto instanceof Produto ) {
                Produto prod = (Produto) produto;

                Connection conn = this.conectar();
                String remover = "DELETE FROM PRODUTO WHERE CODPRODUTO = "+String.valueOf(prod.getIdProd());

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
    
    public Produto find(String codProd){
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT NOME,DESCRICAO FROM PRODUTO WHERE CODPRODUTO = "+ codProd;
            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(rs.getString("NOME"),
                        rs.getString("DESCRICAO"));

                this.desconectar(conn);
                return produto;
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return null;
    } 
   
    public List<Produto> ListTudo() {
        List<Produto> produto = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT CODPRODUTO, NOME, DESCRICAO FROM PRODUTO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Produto p = new Produto(rs.getInt("CODPRODUTO") ,rs.getString("NOME"), rs.getString("DESCRICAO"));
                produto.add(p);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return produto;
    }
    
    public List<ProdutoVenda> ListProdVenda() {
        List<ProdutoVenda> vendas = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT P.CODPRODUTO AS CODIGOPRODUTO, P.NOME AS NOME, P.DESCRICAO AS DESCRICAO, COUNT(V.CODPRODUTO) AS CONTADOR FROM PRODUTO P INNER JOIN VENDA V ON P.CODPRODUTO = V.CODPRODUTO GROUP BY V.CODPRODUTO, P.NOME,P.DESCRICAO, P.CODPRODUTO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ProdutoVenda Pvenda = new ProdutoVenda(rs.getInt("CODIGOPRODUTO") ,rs.getString("NOME"), rs.getString("DESCRICAO") , 
                        rs.getInt("CONTADOR"));
                vendas.add(Pvenda);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return vendas;
    }
   
    public List<ProdutoSAC> ListProdutoPorSAC() {
        List<ProdutoSAC> vendas = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT P.CODPRODUTO AS CODPRODUTO,P.NOME AS NOME,  P.DESCRICAO AS DESCRICAO,COUNT(S.CODPRODUTO) AS CONTADOR FROM PRODUTO P INNER JOIN SAC S ON P.CODPRODUTO = S.CODPRODUTO GROUP BY P.NOME, S.CODPRODUTO,P.DESCRICAO,P.CODPRODUTO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ProdutoSAC venda = new ProdutoSAC(rs.getInt("CODPRODUTO"),
                        rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getInt("CONTADOR"));
                vendas.add(venda);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return vendas;
    }
    
    public List<ProdutoSAC> ListProdutoReclamacao() {
        List<ProdutoSAC> vendas = new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT P.CODPRODUTO AS CODPRODUTO,P.NOME AS NOME,  P.DESCRICAO AS DESCRICAO,COUNT(S.CODPRODUTO) AS CONTADOR FROM PRODUTO P INNER JOIN SAC S ON P.CODPRODUTO = S.CODPRODUTO WHERE S.TIPOLIGACAO='RECLAMACAO'  GROUP BY P.NOME, S.CODPRODUTO,P.DESCRICAO,P.CODPRODUTO";

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ProdutoSAC venda = new ProdutoSAC(rs.getInt("CODPRODUTO"),
                        rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getInt("CONTADOR"));
                vendas.add(venda);
            }
            this.desconectar(conn);
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return vendas;
    }
   
    
}

