/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.sistema.screens;

import java.sql.*;
import br.com.sistema.dataacess.ConnectionModule;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Firmino
 */
public class UserScreen extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pstatement = null;
    ResultSet resultset = null;

    /**
     * Creates new form UserScreen
     */
    public UserScreen() {
        initComponents();
        connection = ConnectionModule.Conector();
    }
//checks if the string is a integer

    private static boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    private void search() {
        String sql = "select * from users where iduser=? ";
        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, user_id.getText());
            resultset = pstatement.executeQuery();
            if (resultset.next()) {
                user_name.setText(resultset.getString(2));
                user_phonenumber.setText(resultset.getString(3));
                user_login.setText(resultset.getString(4));
                user_password.setText(resultset.getString(5));
                user_profile.setSelectedItem(resultset.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "user not registered with this id.");
                //sets the textfields to null to make them empty 
                user_name.setText(null);
                user_phonenumber.setText(null);
                user_login.setText(null);
                user_password.setText(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void add() {
        String sql = "insert into users(iduser,username,phonenumber,login,password,profile) values(?,?,?,?,?,?)";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, user_id.getText());
            pstatement.setString(2, user_name.getText());
            pstatement.setString(3, user_phonenumber.getText());
            pstatement.setString(4, user_login.getText());
            pstatement.setString(5, user_password.getText());
            pstatement.setString(6, (String) user_profile.getSelectedItem());
            // checks if the textfields are empty 
            if (user_id.getText().isEmpty() || !isInteger(user_id.getText())) {
                JOptionPane.showMessageDialog(null, "the id cannot be empty and has to be a number");
            } else if (user_name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the username for the user");
            } else if (user_login.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the login for the user");

            } else if (user_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the password for the user");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "User registered successfully");
                    user_id.setText(null);
                    user_name.setText(null);
                    user_phonenumber.setText(null);
                    user_login.setText(null);
                    user_password.setText(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Failled to register the user");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void update() {
        String sql = "Update users set username =? ,phonenumber=? ,login=? ,password=?,profile=? where iduser =?";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, user_name.getText());
            pstatement.setString(2, user_phonenumber.getText());
            pstatement.setString(3, user_login.getText());
            pstatement.setString(4, user_password.getText());
            pstatement.setString(5, user_profile.getSelectedItem().toString());
            pstatement.setString(6, user_id.getText());
            //checks if the textfields are empty
            if (user_id.getText().isEmpty() || !isInteger(user_id.getText())) {
                JOptionPane.showMessageDialog(null, "the id cannot be empty and has to be a number");
            } else if (user_name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the username for the user");
            } else if (user_login.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the login for the user");

            } else if (user_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Type the password for the user");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "User updated successfully");
                    user_id.setText(null);
                    user_name.setText(null);
                    user_phonenumber.setText(null);
                    user_login.setText(null);
                    user_password.setText(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Failled to update the user");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void delete() {
        int confirm_delete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this user", "Warning", JOptionPane.YES_NO_OPTION);
        if (confirm_delete == JOptionPane.YES_OPTION) {
            String sql = "delete from users where iduser=?";
            try {
                pstatement = connection.prepareStatement(sql);
                pstatement.setString(1, user_id.getText());
                int deleted = pstatement.executeUpdate();
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(null, "user deleted");
                    user_id.setText(null);
                    user_name.setText(null);
                    user_phonenumber.setText(null);
                    user_login.setText(null);
                    user_password.setText(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Phonenumber = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        user_id = new javax.swing.JTextField();
        user_name = new javax.swing.JTextField();
        user_phonenumber = new javax.swing.JTextField();
        user_login = new javax.swing.JTextField();
        user_password = new javax.swing.JTextField();
        user_profile = new javax.swing.JComboBox<>();
        button_add = new javax.swing.JButton();
        button_search = new javax.swing.JButton();
        button_update = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Users control panel");

        jLabel3.setText("*Name");

        Phonenumber.setText("Phonenumber");

        jLabel5.setText("*Login");

        jLabel6.setText("*Password");

        jLabel4.setText("*Id");

        jLabel7.setText("*Profile");

        user_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_idActionPerformed(evt);
            }
        });

        user_profile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "employee" }));

        button_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_add.png"))); // NOI18N
        button_add.setToolTipText("add");
        button_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addActionPerformed(evt);
            }
        });

        button_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_search.png"))); // NOI18N
        button_search.setToolTipText("Search");
        button_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_searchActionPerformed(evt);
            }
        });

        button_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_update.png"))); // NOI18N
        button_update.setToolTipText("Update");
        button_update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_updateActionPerformed(evt);
            }
        });

        button_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_delete.png"))); // NOI18N
        button_delete.setToolTipText("Delete");
        button_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });

        jLabel8.setText("* required fields");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(user_login, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(user_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button_add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(button_search)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Phonenumber)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(user_phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(button_update)
                                .addGap(34, 34, 34)
                                .addComponent(button_delete))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(user_id, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(user_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(user_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(user_profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Phonenumber)
                    .addComponent(user_phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_add, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_search, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_update)
                    .addComponent(button_delete, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(64, 64, 64))
        );

        setBounds(0, 0, 558, 517);
    }// </editor-fold>//GEN-END:initComponents

    private void user_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_idActionPerformed

    private void button_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_button_addActionPerformed

    private void button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_searchActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_button_searchActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_button_updateActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_button_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Phonenumber;
    private javax.swing.JButton button_add;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_search;
    private javax.swing.JButton button_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField user_id;
    private javax.swing.JTextField user_login;
    private javax.swing.JTextField user_name;
    private javax.swing.JTextField user_password;
    private javax.swing.JTextField user_phonenumber;
    private javax.swing.JComboBox<String> user_profile;
    // End of variables declaration//GEN-END:variables
}
