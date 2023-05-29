/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.sistema.screens;

import java.sql.*;
import br.com.sistema.dataacess.ConnectionModule;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Felipe Firmino
 */
public class ClientScreen extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pstatement = null;
    ResultSet resultset = null;

    /**
     * Creates new form ClientScreen
     */
    public ClientScreen() {
        initComponents();
        connection = ConnectionModule.Conector();
    }

    private void add() {
        String sql = "insert into clients(clientname,clientadress,clientphonenumber,clientemail) values(?,?,?,?)";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, textfield_clientname.getText());
            pstatement.setString(2, textfield_clientadress.getText());
            pstatement.setString(3, textfield_clientphonenumber.getText());
            pstatement.setString(4, textfield_clientemail.getText());

            // checks if the textfields are empty 
            if (textfield_clientname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The client name cannot be empty");
            } else if (textfield_clientadress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The client adress cannot be empty");
            } else if (textfield_clientphonenumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The client phonenumber cannot be empty");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "client registered successfully");
                    cleanpage();

                } else {
                    JOptionPane.showMessageDialog(null, "Failled to register the client");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void search() {
        String sql = "select * from clients where clientname like ?";
        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, textfield_searchclient.getText() + "%");
            resultset = pstatement.executeQuery();
            //this function comes from the library rs2xml
            Clients_table.setModel(DbUtils.resultSetToTableModel(resultset));
        } catch (Exception e) {

        }
    }

    private void setSearchedClient() {
        int set = Clients_table.getSelectedRow();
        textfield_idclient.setText(Clients_table.getModel().getValueAt(set, 0).toString());
        textfield_clientname.setText(Clients_table.getModel().getValueAt(set, 1).toString());
        textfield_clientadress.setText(Clients_table.getModel().getValueAt(set, 2).toString());
        textfield_clientphonenumber.setText(Clients_table.getModel().getValueAt(set, 3).toString());
        textfield_clientemail.setText(Clients_table.getModel().getValueAt(set, 4).toString());
        //unables the add button so you dont add the same client thats already registered
        button_addclient.setEnabled(false);

    }

    private void update() {
        String sql = "Update clients set clientname =? ,clientadress=? ,clientphonenumber=? ,clientemail=? where idclient =?";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, textfield_clientname.getText());
            pstatement.setString(2, textfield_clientadress.getText());
            pstatement.setString(3, textfield_clientphonenumber.getText());
            pstatement.setString(4, textfield_clientemail.getText());
            pstatement.setString(5, textfield_idclient.getText());
            //checks if the textfields are empty
            if (textfield_clientname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "the client name cannot be empty and has to be a number");
            } else if (textfield_clientadress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The adress cannot be empty");
            } else if (textfield_clientphonenumber.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The phonenumber cannot be empty");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "User updated successfully");
                    cleanpage();
                    button_addclient.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Failled to update the client");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void delete() {
        int confirm_delete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this client", "Warning", JOptionPane.YES_NO_OPTION);
        if (confirm_delete == JOptionPane.YES_OPTION) {
            String sql = "delete from clients where idclient=?";
            try {
                pstatement = connection.prepareStatement(sql);
                pstatement.setString(1, textfield_idclient.getText());
                int deleted = pstatement.executeUpdate();
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(null, "user deleted");
                    cleanpage();
                    button_addclient.setEnabled(true);
                }
            }catch(SQLIntegrityConstraintViolationException errorsql){
                JOptionPane.showMessageDialog(null, "It is not possible to delete a client with active service orders");
          
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
               
            }

        }
    }

    private void cleanpage() {
        textfield_idclient.setText(null);
        textfield_clientname.setText(null);
        textfield_clientadress.setText(null);
        textfield_clientphonenumber.setText(null);
        textfield_clientemail.setText(null);
        ((DefaultTableModel) Clients_table.getModel()).setRowCount(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textfield_searchclient = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Clients_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textfield_clientname = new javax.swing.JTextField();
        textfield_clientadress = new javax.swing.JTextField();
        textfield_clientphonenumber = new javax.swing.JTextField();
        textfield_clientemail = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        button_addclient = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        textfield_idclient = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clients control panel");

        textfield_searchclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_searchclientActionPerformed(evt);
            }
        });
        textfield_searchclient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_searchclientKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_search_32x32.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("*required fields ");

        Clients_table = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        Clients_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Adress", "Phonenumber", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Clients_table.setFocusable(false);
        Clients_table.getTableHeader().setReorderingAllowed(false);
        Clients_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Clients_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Clients_table);

        jLabel2.setText("*Name");

        jLabel3.setText("*Adress");

        jLabel4.setText("*Phonenumber");

        jLabel5.setText("Email");

        textfield_clientemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_clientemailActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_delete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_update.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        button_addclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_add.png"))); // NOI18N
        button_addclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addclientActionPerformed(evt);
            }
        });

        jLabel6.setText("ID");

        textfield_idclient.setEnabled(false);
        textfield_idclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_idclientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textfield_clientemail, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfield_clientphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textfield_clientadress, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfield_clientname, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfield_idclient, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_addclient)
                .addGap(59, 59, 59)
                .addComponent(jButton3)
                .addGap(60, 60, 60)
                .addComponent(jButton2)
                .addGap(103, 103, 103))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfield_searchclient, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textfield_searchclient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfield_idclient, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textfield_clientname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textfield_clientadress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textfield_clientphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textfield_clientemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_addclient)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textfield_clientemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_clientemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_clientemailActionPerformed

    private void button_addclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addclientActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_button_addclientActionPerformed

    private void textfield_searchclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_searchclientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_searchclientActionPerformed

    private void textfield_searchclientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_searchclientKeyReleased
        // TODO add your handling code here:
        //while is typing it keeps running the method (key released)
        search();
    }//GEN-LAST:event_textfield_searchclientKeyReleased

    private void Clients_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Clients_tableMouseClicked
        // TODO add your handling code here:
        //when its clicked the function happens
        setSearchedClient();
    }//GEN-LAST:event_Clients_tableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void textfield_idclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_idclientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_idclientActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Clients_table;
    private javax.swing.JButton button_addclient;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textfield_clientadress;
    private javax.swing.JTextField textfield_clientemail;
    private javax.swing.JTextField textfield_clientname;
    private javax.swing.JTextField textfield_clientphonenumber;
    private javax.swing.JTextField textfield_idclient;
    private javax.swing.JTextField textfield_searchclient;
    // End of variables declaration//GEN-END:variables
}
