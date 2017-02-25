/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.datalayer;


import br.com.crm.businessobject.Cliente;
import br.com.crm.businessobject.Funcionario;
import br.com.crm.businessobject.Endereco;
import br.com.crm.businessobject.SAC;
import java.sql.Connection;
import java.sql.Date;
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
public class FuncionarioDao extends DaoBase implements IFuncionarioDao{
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
     * @param funcionario
     * @param endereco
     * Insere se funcionario for instancia de Funcionario e endereco instancia de Endereco
     * Caso contrario não faz nada
     */
     @Override
    public void save(Object funcionario, Object endereco) {
        try {
             if ( funcionario instanceof Funcionario && endereco instanceof Endereco ) {
                Funcionario func = (Funcionario) funcionario;
                Endereco end=(Endereco) endereco;
            Connection conn = this.conectar();
            String inserir = "INSERT INTO FUNCIONARIO(CODFUNCIONARIO,BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP,RG,CODDEPARTAMENTO,SEXO,DATANASCIMENTO,EMAIL,CPF,NOME) VALUES ((select (nvl(max(CODFUNCIONARIO),0)+1) FROM FUNCIONARIO),?,?,?,?,?,?,?,(SELECT CODDEPARTAMENTO FROM DEPARTAMENTO WHERE NOME=?),?,?,?,?,?)";
            String inserirTel = "INSERT INTO TELEFONEFUNC(TELEFONE,CODFUNCIONARIO) VALUES (?,(select (nvl(max(CODFUNCIONARIO),0)+1) FROM TELEFONEFUNC))";
            PreparedStatement sta;
            PreparedStatement sta2;
           
            sta2 = conn.prepareStatement(inserirTel);
            sta2.setString(1,func.getTelefone());
           
            sta = conn.prepareStatement(inserir);
         
            sta.setString(1, end.getBairro());
            sta.setString(2, end.getCidade());
            sta.setString(3, end.getEstado());
            sta.setString(4, end.getRua());
            sta.setString(5, end.getNumero());
            sta.setString(6, end.getCep());
            sta.setString(7, func.getRg());
            sta.setString(8, func.getSexo() + "");
            sta.setString(9, func.getNomeDept());
            sta.setDate(10, (Date) func.getDataNascimento());
            
            sta.setString(11, func.getEmail());
            sta.setString(12, func.getCPF());
            sta.setString(13, func.getNome());

            sta.execute();
            sta2.execute();
            this.desconectar(conn);
             }
        } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }

    }

    /**
     *
     * @param funcionario
     * @param endereco
     * Atualiza se funcionario for instancia de Funcionario e endereco instancia de Endereco
     * Caso contrario não faz nada
     */
     @Override
    public void update(Object funcionario, Object endereco) {
        try {
            if ( funcionario instanceof Funcionario && endereco instanceof Endereco ) {
                Funcionario func = (Funcionario) funcionario;
                Endereco end=(Endereco) endereco;
            Connection conn = this.conectar();
            String atualizar = "UPDATE FUNCIONARIO SET BAIRRO=?,CIDADE=?,ESTADO=?,RUA=?,NUMERO=?,CEP=?,RG=?,SEXO=?,DATANASCIMENTO=?,EMAIL=?,CPF=?,NOME=? WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            String atualizarTel = "UPDATE TELEFONEFUNC SET TELEFONE=? WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            PreparedStatement sta;
            PreparedStatement sta2;

            sta = conn.prepareStatement(atualizar);
            sta2 = conn.prepareStatement(atualizarTel);

            sta2.setString(1, func.getTelefone());
            sta2.setString(2, func.getCPF());//WHERE
            sta.setString(1, end.getBairro());
            sta.setString(2, end.getCidade());
            sta.setString(3, end.getEstado());
            sta.setString(4, end.getRua());
            sta.setString(5, end.getNumero());
            sta.setString(6, end.getCep());
            sta.setString(7, func.getRg());
            sta.setString(8, func.getSexo()+"");
            sta.setDate(9, (Date) func.getDataNascimento());
            sta.setString(10, func.getEmail());
            sta.setString(11, func.getCPF());
            sta.setString(12, func.getNome());
            sta.setString(13, func.getCPF());//WHERE
            sta.executeUpdate();
            sta2.executeUpdate();
            this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
    }

    /**
     *
     * @param funcionario
     * Deleta se funcionario for uma instancia de Funcionario 
     * Caso contrario não faz nada
     */
     @Override
    public void delete(Object funcionario) {
        try {
            if ( funcionario instanceof Funcionario ) {
                Funcionario func = (Funcionario) funcionario;
            Connection conn = this.conectar();
            String remover = " DELETE FROM FUNCIONARIO WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            PreparedStatement sta;
            sta = conn.prepareStatement(remover);
            String removerTel = "DELETE FROM TELEFONEFUNC WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            PreparedStatement sta2;
            sta2 = conn.prepareStatement(removerTel);
            
            sta.setString(1, func.getCPF());
            sta2.setString(1, func.getCPF());
            sta.execute();
            sta2.execute();
            this.desconectar(conn);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
    }

    /**
     *
     * @return uma lista com TODOS os registros de FUNCIONARIOS EXCETO ENDERECO
     */
     @Override
    public List list() {
        List<Funcionario> func=new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta="SELECT CLASSIFICACAO,DATANASCIMENTO,SEXO,EMAIL,CPF,NOME FROM CLIENTE";
            String consulta2="SELECT TELEFONE FROM TELEFONEFUNC";
            PreparedStatement stm=conn.prepareStatement(consulta);
            ResultSet rs=stm.executeQuery();
            PreparedStatement stmTel=conn.prepareStatement(consulta2);
            ResultSet rsTel=stmTel.executeQuery();
         
            while(rs.next())
            {
            Funcionario funcionario=new Funcionario(rs.getString("RG"),
                    rs.getString("SEXO").charAt(0),rs.getDate("DATANASCIMENTO"),
                    rs.getString("EMAIL"),rs.getString("CPF"),rs.getString("NOME"),rsTel.getString("TELEFONE"));
           func.add(funcionario);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
        return func;
    }
     
    /**
     *
     * @return a lista de endereços dos FUNCIONARIOS
     */
     @Override
    public List<Endereco> ListEnd() {
        List<Endereco> end=new ArrayList<>();
        try {
            Connection conn = this.conectar();
            String consulta="SELECT BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP FROM FUNCIONARIO";
            PreparedStatement stm=conn.prepareStatement(consulta);
            ResultSet rs=stm.executeQuery();
            
            while(rs.next())
            {
            Endereco endereco=new Endereco(rs.getString("BAIRRO"),
                    rs.getString("CIDADE"),rs.getString("ESTADO"),
                    rs.getString("RUA"),rs.getString("NUMERO"),rs.getString("CEP"));
            end.add(endereco);
            }
        } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
        return end;
    }

    /**
     *
     * @param Cpf
     * @return um funcionario atraves do Cpf
     */
    @Override
    public Funcionario find(String Cpf) 
    {
             try {
            Connection conn = this.conectar();
            String consulta="SELECT CLASSIFICACAO,DATANASCIMENTO,SEXO,EMAIL,CPF,NOME FROM FUNCIONARIO WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            String consulta2="SELECT TELEFONE FROM TELEFONEFUNC WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
           
            PreparedStatement stm=conn.prepareStatement(consulta);
            PreparedStatement stmTel=conn.prepareStatement(consulta2);
            
            stm.setString(1, Cpf);
            stmTel.setString(1, Cpf);
            ResultSet rs=stm.executeQuery();
            ResultSet rsTel=stmTel.executeQuery();
            Funcionario funcionario=new Funcionario(rs.getString("RG"),
                    rs.getString("SEXO").charAt(0),rs.getDate("DATANASCIMENTO"),
                    rs.getString("EMAIL"),rs.getString("CPF"),rs.getString("NOME"),rsTel.getString("TELEFONE"));
            return funcionario;
             } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
           return null;  
    }

    /**
     *
     * @param Cpf
     * @return um endereco atraves do Cpf
     */
    @Override
    public Endereco findEndereco(String Cpf) 
    {
             try {
            Connection conn = this.conectar();
            String consulta="SELECT BAIRRO,CIDADE,ESTADO,RUA,NUMERO,CEP FROM FUNCIONARIO WHERE CODFUNCIONARIO=(SELECT CODFUNCIONARIO FROM FUNCIONARIO WHERE CPF='?')";
            PreparedStatement stm=conn.prepareStatement(consulta);
            stm.setString(1, Cpf);
            ResultSet rs=stm.executeQuery();
             Endereco endereco=new Endereco(rs.getString("BAIRRO"),
                    rs.getString("CIDADE"),rs.getString("ESTADO"),
                    rs.getString("RUA"),rs.getString("NUMERO"),rs.getString("CEP"));
            return endereco;
             } catch (SQLException erro) {
            System.out.println("ERRO...." );
        }
           return null;  
    }

}