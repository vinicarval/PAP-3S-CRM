/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;

import br.com.crm.businessobject.Funcionario;
import br.com.crm.businessobject.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author icaro
 */
public class UsuarioDao extends DaoBase<Object> implements IDaoUsuario{

    public Connection conectar() {
        Connection conn = null;
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
                
        
        /*
        Conexao con = new Conexao();
        
        try{
        return con.conectar();
        }
        catch(Exception e ){
            System.out.println("Falha ao conectar");
        }
        return con.conectar();
        
        */
    }

    public void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectou");
            }

        } catch (SQLException erro) {
            System.out.println("Não desconectou");
        }
                
        /*
        Conexao con = new Conexao();
        try{
         con.desconectar( conn);
        }
        catch(Exception e ){
            System.out.println("Falha ao desconectar");
        }
        */
    }

    /**
     *
     * @param usuario
     * @param funcionario
     * Insere se usuario for uma instancia de Usuario e funcionario uma instancia de Funcionario
     * Caso contrario não faz nada
     */
    @Override
    public void save(Object usuario, Object funcionario) {
        try {
            if (usuario instanceof Usuario && funcionario instanceof Funcionario) {
                Usuario usu = (Usuario) usuario;
                Funcionario func = (Funcionario) funcionario;
                Connection conn = this.conectar();
                String inserir = "INSERT INTO USUARIO(CODFUNCIONARIO,SENHA,NIVELACESSO) VALUES ((SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?'),?,?)";

                PreparedStatement sta;

                sta = conn.prepareStatement(inserir);
                sta.setString(1, func.getCPF());
                sta.setString(2, usu.getSenha());
                sta.setString(3, usu.getNivelAcesso());

                sta.execute();

                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }

    }

    /**
     *
     * @param usuario
     * Atualiza a SENHA se usuario for uma instancia de Usuario
     * Caso contrario não faz nada
     */
    @Override
    public void update(Object usuario) {
        try {
            if (usuario instanceof Usuario) {
                Usuario usu = (Usuario) usuario;

                Connection conn = this.conectar();
                String atualizar = "UPDATE USUARIO SET SENHA='"+usu.getSenha()+"' WHERE CODFUNCIONARIO= "+usu.getIdFunc();

                PreparedStatement sta;

                sta = conn.prepareStatement(atualizar);
                /*
                sta.setString(1, usu.getSenha());
                sta.setInt(2, usu.getIdFunc());
                */                              
                sta.executeUpdate();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println(erro);
        }
    }

    /**
     *
     * @param usuario
     * Deleta se usuario for uma instancia de Usuario
     * Caso contrario não faz nada
     */
    @Override
    public void delete(Object usuario) {
        try {
            if (usuario instanceof Usuario) {
                Usuario usu = (Usuario) usuario;

                Connection conn = this.conectar();
                String remover = "DELETE FROM USUARIO WHERE CODFUNCIONARIO=?";

                PreparedStatement sta;
                sta = conn.prepareStatement(remover);
                sta.setInt(1, usu.getIdFunc());

                sta.execute();
                this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO....");
        }
    }

    /**
     *
     * @param idFunc
     * @return um objeto usuario com a senha pedida
     */
    @Override
    public String findSenha(int idFunc) {
        try {
            Connection conn = this.conectar();
            String consulta = "SELECT SENHA FROM USUARIO WHERE CODFUNCIONARIO= "+idFunc ;

            PreparedStatement stm = conn.prepareStatement(consulta);
            ResultSet rs = stm.executeQuery();
            String resultado="";
            while(rs.next()){
            resultado = rs.getString("SENHA");
            }           
            this.desconectar(conn);
            return resultado;
            
        } catch (SQLException erro) {
            System.out.println(erro);
        }
        return null;
    }
}
