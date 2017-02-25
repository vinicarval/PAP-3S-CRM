/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crm.Frames.br;


import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author wellweb
 */
public class Frame_Atualizacoes extends javax.swing.JFrame {

    public Frame_Atualizacoes() {
        initComponents();
        this.setLocationRelativeTo(null);
        painel_resposta.setVisible(false);
        carregando();

    }

    public void carregando() {
        int x = 1;

        if (x > 0) {
            new Thread() {
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            sleep(50);                            
                            jProgressBar_atualiza.setValue(i);
                            lbl_progresso.setText(i+"%");

                            if (i == 99) {
                                buscando_gif.setVisible(false);
                                buscando_gif2.setVisible(false);
                                buscando_texto.setVisible(false);
                                lbl_progresso.setVisible(false);
                                jProgressBar_atualiza.setVisible(false);
                                painel_resposta.setVisible(true);
                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Frame_Atualizacoes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

            }.start();

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_progresso = new javax.swing.JLabel();
        painel_resposta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jProgressBar_atualiza = new javax.swing.JProgressBar();
        buscando_gif2 = new javax.swing.JLabel();
        buscando_texto = new javax.swing.JLabel();
        buscando_gif = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        back_page = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_progresso.setText("0");
        getContentPane().add(lbl_progresso, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 30, 30));

        painel_resposta.setBackground(new java.awt.Color(102, 102, 102));
        painel_resposta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NENHUMA ATUALIZAÇÃO ENCONTRADA ");

        jButton1.setBackground(new java.awt.Color(255, 102, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("VOLTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painel_respostaLayout = new javax.swing.GroupLayout(painel_resposta);
        painel_resposta.setLayout(painel_respostaLayout);
        painel_respostaLayout.setHorizontalGroup(
            painel_respostaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel_respostaLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(38, 38, 38))
            .addGroup(painel_respostaLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painel_respostaLayout.setVerticalGroup(
            painel_respostaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_respostaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        getContentPane().add(painel_resposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 370, 110));

        jProgressBar_atualiza.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(jProgressBar_atualiza, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 254, 230, 20));

        buscando_gif2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/ajax-loader.gif"))); // NOI18N
        getContentPane().add(buscando_gif2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 71, 30));

        buscando_texto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscando_texto.setForeground(new java.awt.Color(255, 255, 255));
        buscando_texto.setText("BUSCANDO POR ATUALIZAÇÕES");
        getContentPane().add(buscando_texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        buscando_gif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/ajax-loader.gif"))); // NOI18N
        getContentPane().add(buscando_gif, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 71, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/LOGO-PULSE.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 102, -1, 120));

        back_page.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/crm/Frames/br/imagens/fundo_pages_crm.jpg"))); // NOI18N
        getContentPane().add(back_page, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                new Frame_Atualizacoes().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back_page;
    private javax.swing.JLabel buscando_gif;
    private javax.swing.JLabel buscando_gif2;
    private javax.swing.JLabel buscando_texto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar_atualiza;
    private javax.swing.JLabel lbl_progresso;
    private javax.swing.JPanel painel_resposta;
    // End of variables declaration//GEN-END:variables
}
