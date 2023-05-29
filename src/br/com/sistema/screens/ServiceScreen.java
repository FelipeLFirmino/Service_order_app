/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.sistema.screens;

import br.com.sistema.dataacess.ConnectionModule;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Felipe Firmino
 */
public class ServiceScreen extends javax.swing.JInternalFrame {

    Connection connection = null;
    PreparedStatement pstatement = null;
    ResultSet resultset = null;

    //variable that sets the text for the radio buttons 
    private String type;

    /**
     * Creates new form ServiceScreen
     */
    public ServiceScreen() {
        initComponents();
        connection = ConnectionModule.Conector();
    }

    private void search() {
        String sql = "select idclient,clientname,clientphonenumber from clients where clientname like ?";
        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, textfield_clientsearch.getText() + "%");
            resultset = pstatement.executeQuery();
            //this function comes from the library rs2xml
            table_clients.setModel(DbUtils.resultSetToTableModel(resultset));
        } catch (Exception e) {

        }
    }

    private void setSearchedClient() {
        int set = table_clients.getSelectedRow();
        textfield_clientid.setText(table_clients.getModel().getValueAt(set, 0).toString());
        //unables the add button so you dont add the same client thats already registered       
    }

    private void addService() {
        String sql = "insert into serviceorder(type,status,equipment,problem,service,technician,value,idclient) values(?,?,?,?,?,?,?,?)";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, type);
            pstatement.setString(2, combobox_status.getSelectedItem().toString());
            pstatement.setString(3, textfield_equipment.getText());
            pstatement.setString(4, textfield_problem.getText());
            pstatement.setString(5, textfield_service.getText());
            pstatement.setString(6, textfield_technician.getText());
            pstatement.setString(7, textfield_value.getText().replace(",", "."));
            pstatement.setString(8, textfield_clientid.getText());
            // checks if the textfields are empty 
            if (textfield_equipment.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The equipment cannot be empty");
            } else if (textfield_problem.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The problem cannot be empty");
            } else if (textfield_service.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The service cannot be empty");

            } else if (textfield_technician.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The technician cannot be empty");

            } else if (textfield_value.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The value cannot be empty");

            } else if (textfield_clientid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Select a client to link to the service");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Service registered successfully");
                    button_add.setEnabled(false);
                    button_print.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Failled to register the client");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void cleanpage() {
        textfield_Serviceorder.setText(null);
        textfield_data.setText(null);
        textfield_clientid.setText(null);
        textfield_equipment.setText(null);
        textfield_problem.setText(null);
        textfield_service.setText(null);
        textfield_technician.setText(null);
        textfield_value.setText(null);
        textfield_clientsearch.setText(null);
        ((DefaultTableModel) table_clients.getModel()).setRowCount(0);
        button_delete.setEnabled(false);
        button_print.setEnabled(false);
        button_update.setEnabled(false);

    }

    private void search_so() {
        String number_SO = JOptionPane.showInputDialog("Service number");
        String sql = "select serviceorder,date_format(data_so,'%d/%m/%y'),type,status,equipment,problem,service,technician,value,idclient from serviceorder where serviceorder= " + number_SO;

        try {
            pstatement = connection.prepareStatement(sql);
            resultset = pstatement.executeQuery();
            if (resultset.next()) {
                textfield_Serviceorder.setText(resultset.getString(1));
                textfield_data.setText(resultset.getString(2));
                String type_db = resultset.getString(3);
                if (type_db.equals("Budget")) {
                    RadioButton_budget.setSelected(true);
                    type = "Service order";

                } else {
                    RadioButton_serviceorder.setSelected(true);
                    type = "Budget";
                }
                combobox_status.setSelectedItem(resultset.getString(4));
                textfield_equipment.setText(resultset.getString(5));
                textfield_problem.setText(resultset.getString(6));
                textfield_service.setText(resultset.getString(7));
                textfield_technician.setText(resultset.getString(8));
                textfield_value.setText(resultset.getString(9));
                textfield_clientid.setText(resultset.getString(10));

                button_add.setEnabled(false);
                textfield_clientsearch.setEnabled(false);
                table_clients.setVisible(false);
                button_print.setEnabled(true);
                button_delete.setEnabled(true);
                button_update.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "service not found ");
            }

        } catch (SQLSyntaxErrorException errorsql) {
            JOptionPane.showMessageDialog(null, "Service invalid, type a number");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }

    private void UpdateService() {
        String sql = "update serviceorder set type = ? ,status =? ,equipment =? ,problem = ? ,service = ?,technician = ? ,value =? where serviceorder = ?";

        try {
            pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, type);
            pstatement.setString(2, combobox_status.getSelectedItem().toString());
            pstatement.setString(3, textfield_equipment.getText());
            pstatement.setString(4, textfield_problem.getText());
            pstatement.setString(5, textfield_service.getText());
            pstatement.setString(6, textfield_technician.getText());
            pstatement.setString(7, textfield_value.getText().replace(",", "."));
            pstatement.setString(8, textfield_Serviceorder.getText());
            // checks if the textfields are empty 
            if (textfield_equipment.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The equipment cannot be empty");
            } else if (textfield_problem.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The problem cannot be empty");
            } else if (textfield_service.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The service cannot be empty");

            } else if (textfield_technician.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The technician cannot be empty");

            } else if (textfield_value.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The value cannot be empty");

            } else if (textfield_clientid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Select a client to link to the service");

            } else {
                int added = pstatement.executeUpdate();

                if (added > 0) {
                    JOptionPane.showMessageDialog(null, "Service updated successfully");
                    cleanpage();
                    button_add.setEnabled(true);
                    textfield_clientsearch.setEnabled(false);
                    table_clients.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Failled to update the client");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void deleteService() {
        int confirm_delete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this service", "Warning", JOptionPane.YES_NO_OPTION);
        if (confirm_delete == JOptionPane.YES_OPTION) {
            String sql = "delete from serviceorder where serviceorder=?";
            try {
                pstatement = connection.prepareStatement(sql);
                pstatement.setString(1, textfield_Serviceorder.getText());
                int deleted = pstatement.executeUpdate();
                if (deleted > 0) {
                    JOptionPane.showMessageDialog(null, "service deleted deleted");
                    cleanpage();
                    button_add.setEnabled(true);
                    textfield_clientsearch.setEnabled(false);
                    table_clients.setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textfield_Serviceorder = new javax.swing.JTextField();
        textfield_data = new javax.swing.JTextField();
        RadioButton_budget = new javax.swing.JRadioButton();
        RadioButton_serviceorder = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        combobox_status = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        textfield_clientsearch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textfield_clientid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_clients = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textfield_equipment = new javax.swing.JTextField();
        textfield_problem = new javax.swing.JTextField();
        textfield_service = new javax.swing.JTextField();
        textfield_technician = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textfield_value = new javax.swing.JTextField();
        button_add = new javax.swing.JButton();
        button_search = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        button_update = new javax.swing.JButton();
        button_print = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Serivce Order ");
        setPreferredSize(new java.awt.Dimension(560, 500));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("SO number");

        jLabel2.setText("Data");

        textfield_Serviceorder.setEditable(false);
        textfield_Serviceorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_ServiceorderActionPerformed(evt);
            }
        });

        textfield_data.setEditable(false);
        textfield_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_dataActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioButton_budget);
        RadioButton_budget.setText("Budget");
        RadioButton_budget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_budgetActionPerformed(evt);
            }
        });

        buttonGroup1.add(RadioButton_serviceorder);
        RadioButton_serviceorder.setText("Service order");
        RadioButton_serviceorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButton_serviceorderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textfield_Serviceorder, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textfield_data)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioButton_budget)
                    .addComponent(RadioButton_serviceorder, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfield_Serviceorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioButton_budget)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioButton_serviceorder)
                .addContainerGap())
        );

        jLabel3.setText("Status");

        combobox_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Waiting approval", "Done", "Not approved ", "Waiting pieces", "Returned to client", "Abbandoned", " " }));
        combobox_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_statusActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Client"));

        textfield_clientsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textfield_clientsearchKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_search_32x32.png"))); // NOI18N

        jLabel5.setText("ID");

        textfield_clientid.setEditable(false);

        table_clients = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        table_clients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Phonenumber"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_clients.setFocusable(false);
        table_clients.getTableHeader().setReorderingAllowed(false);
        table_clients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_clientsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_clients);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textfield_clientsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(textfield_clientid, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textfield_clientid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textfield_clientsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Equipment");

        jLabel7.setText("Problem");

        jLabel8.setText("Service");

        jLabel9.setText("Technician");

        jLabel10.setText("Value");

        textfield_value.setText("0");
        textfield_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_valueActionPerformed(evt);
            }
        });

        button_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_add.png"))); // NOI18N
        button_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addActionPerformed(evt);
            }
        });

        button_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_search.png"))); // NOI18N
        button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_searchActionPerformed(evt);
            }
        });

        button_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_delete.png"))); // NOI18N
        button_delete.setEnabled(false);
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });

        button_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/icon_update.png"))); // NOI18N
        button_update.setEnabled(false);
        button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_updateActionPerformed(evt);
            }
        });

        button_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistema/icons/print_icon.png"))); // NOI18N
        button_print.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_print)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_update)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combobox_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfield_equipment, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textfield_service, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textfield_problem, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfield_technician, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textfield_value, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(combobox_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textfield_equipment, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(textfield_problem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textfield_service, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textfield_technician, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textfield_value, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_search)
                    .addComponent(button_add)
                    .addComponent(button_print)
                    .addComponent(button_delete)
                    .addComponent(button_update))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        setBounds(0, 0, 561, 490);
    }// </editor-fold>//GEN-END:initComponents

    private void button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_searchActionPerformed
        // TODO add your handling code here:
        search_so();
    }//GEN-LAST:event_button_searchActionPerformed

    private void textfield_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_valueActionPerformed

    private void combobox_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox_statusActionPerformed

    private void RadioButton_serviceorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_serviceorderActionPerformed
        // TODO add your handling code here:
        type = "Service order";
    }//GEN-LAST:event_RadioButton_serviceorderActionPerformed

    private void textfield_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_dataActionPerformed

    private void textfield_ServiceorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_ServiceorderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textfield_ServiceorderActionPerformed

    private void textfield_clientsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textfield_clientsearchKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_textfield_clientsearchKeyReleased

    private void table_clientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_clientsMouseClicked
        // TODO add your handling code here:
        setSearchedClient();
    }//GEN-LAST:event_table_clientsMouseClicked

    private void RadioButton_budgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButton_budgetActionPerformed
        // TODO add your handling code here:
        type = "Budget";
    }//GEN-LAST:event_RadioButton_budgetActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        RadioButton_budget.setSelected(true);
        type = "Budget";
    }//GEN-LAST:event_formInternalFrameOpened

    private void button_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addActionPerformed
        // TODO add your handling code here:
        addService();
    }//GEN-LAST:event_button_addActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed
        // TODO add your handling code here:
        UpdateService();
    }//GEN-LAST:event_button_updateActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        // TODO add your handling code here:
        deleteService();
    }//GEN-LAST:event_button_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioButton_budget;
    private javax.swing.JRadioButton RadioButton_serviceorder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton button_add;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_print;
    private javax.swing.JButton button_search;
    private javax.swing.JButton button_update;
    private javax.swing.JComboBox<String> combobox_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_clients;
    private javax.swing.JTextField textfield_Serviceorder;
    private javax.swing.JTextField textfield_clientid;
    private javax.swing.JTextField textfield_clientsearch;
    private javax.swing.JTextField textfield_data;
    private javax.swing.JTextField textfield_equipment;
    private javax.swing.JTextField textfield_problem;
    private javax.swing.JTextField textfield_service;
    private javax.swing.JTextField textfield_technician;
    private javax.swing.JTextField textfield_value;
    // End of variables declaration//GEN-END:variables
}
