/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.Frames.br;

import package_frames_Desativados.FrameDashBoard_Inutilizado;
import br.com.crm.businessobject.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author wellweb
 */
public class FrameLogin extends javax.swing.JFrame {

    /**
     * Creates new form Frame_Login
     */
    public FrameLogin() {
        initComponents();
        lblErro.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnCancelar = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        lblTrocarSenha = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblErro = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_back_blue = new javax.swing.JLabel();
        logo_pulse = new javax.swing.JLabel();
        back_moldura = new javax.swing.JLabel();
        back_efeito1 = new javax.swing.JLabel();
        back_efeito2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/unlock.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/by_Eramus7-watermark.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/user_LOG.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 240, 40));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 110, 40));

        btnEntrar.setBackground(new java.awt.Color(0, 204, 0));
        btnEntrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 110, 40));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 240, 40));

        lblTrocarSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTrocarSenha.setForeground(new java.awt.Color(255, 255, 255));
        lblTrocarSenha.setText("TROCAR SENHA");
        lblTrocarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrocarSenhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblTrocarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("NOME USUARIO");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        lblErro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblErro.setForeground(new java.awt.Color(255, 0, 51));
        lblErro.setText("USUÁRIO OU SENHA INVALIDO");
        getContentPane().add(lblErro, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SENHA ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/login_chave.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel_back_blue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/login-back_1.png"))); // NOI18N
        getContentPane().add(jLabel_back_blue, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 450, 330));

        logo_pulse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/LOGO-PULSE.png"))); // NOI18N
        getContentPane().add(logo_pulse, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, -1, 110));

        back_moldura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/fundo-03.png"))); // NOI18N
        getContentPane().add(back_moldura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 400));

        back_efeito1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/back01.gif"))); // NOI18N
        back_efeito1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        getContentPane().add(back_efeito1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 220, 360));

        back_efeito2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/back01.gif"))); // NOI18N
        back_efeito2.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        getContentPane().add(back_efeito2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 20, 620, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Usuario usuario = new Usuario();
        FramesDashBoard frame = new FramesDashBoard();
        
        if (txtUsuario.getText().isEmpty() == false && txtSenha.getText().isEmpty() == false){                                         
            try{                                               
                int id = Integer.parseInt(txtUsuario.getText());
                String senha = new String(txtSenha.getPassword());
                
                
                
               if(usuario.validarLogin(id,senha)){
                    
                    frame.setVisible(true);
                    this.dispose();
                }
                else{
                    lblErro.setText("USUÁRIO OU SENHA INVALIDO");
                    lblErro.setVisible(true);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this, "FALHA AO FAZER LOGIN");
            }
                
        }
        
        
        
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void lblTrocarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrocarSenhaMouseClicked
        FrameAlterarSenha frame = new FrameAlterarSenha();
        
        frame.setVisible(true);
    }//GEN-LAST:event_lblTrocarSenhaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back_efeito1;
    private javax.swing.JLabel back_efeito2;
    private javax.swing.JLabel back_moldura;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_back_blue;
    private javax.swing.JLabel lblErro;
    private javax.swing.JLabel lblTrocarSenha;
    private javax.swing.JLabel logo_pulse;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
