/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGR
 */
public class StudentUI extends javax.swing.JFrame {
    private String studentID;
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form StudentUI
     */
    public StudentUI(String studentID) {
        initComponents();
        this.studentID = studentID;
        connectDB();
    }
    
    final String url = "jdbc:mysql://localhost:3306/schooldb";
    final String sqlDriver = "com.mysql.cj.jdbc.Driver";
    final String sqlUser = "root";
    final String sqlPass = "@mySQL36";
    
    Connection connect;
    PreparedStatement prestate;
    Statement statement;
    ResultSet result;
    

    public void connectDB() {
        try {
            Class.forName(sqlDriver);
            connect = DriverManager.getConnection(url, sqlUser, sqlPass);
            statement = connect.createStatement();
            String queryStudentInfo = "SELECT * FROM `student_info` WHERE student_id = ?";
            prestate = connect.prepareStatement(queryStudentInfo);
            prestate.setString(1, studentID);
            result = prestate.executeQuery(); 
            
            if (connect != null) {
                System.out.println("Database Connected");
            }     
            
            StringBuilder labelText = new StringBuilder();
            String section = "";
            String student_id = "";
            
            while(result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                section = result.getString("section");
                student_id = result.getString("student_id");
                labelText.append(firstName).append(", ").append(lastName);
            }        
            
            label1.setText(labelText.toString());
            label2.setText(section);
            label3.setText(student_id);
            
            String queryGrades = "SELECT DISTINCT g.subject_id, t.first_name, t.last_name, s.subject_name, g.grade " +
                    "FROM `grades` g " +
                    "JOIN `subjects` s ON g.subject_id = s.subject_id " +
                    "JOIN `teacher_info` t ON g.teacher_id = t.teacher_id " +
                    "WHERE g.student_id = ?";

            tableModel = new DefaultTableModel(new Object[][][]{}, new String[]{"SUBJECTS", "TEACHERS", "GRADES"});

            prestate = connect.prepareStatement(queryGrades);
            prestate.setString(1, studentID);
            result = prestate.executeQuery();

            while (result.next()) {
                String subjectName = result.getString("subject_name");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String teacherName = firstName + ", " + lastName;
                String grade = result.getString("grade");
                tableModel.addRow(new Object[]{subjectName, teacherName, grade});
            }

            label4.setModel(tableModel);

            // Make the table non-editable
            label4.setEnabled(false);
            label4.setFocusable(false);
            label4.setRowSelectionAllowed(false);
            label4.setColumnSelectionAllowed(false);
            // Adjust column width
            label4.getColumnModel().getColumn(0).setPreferredWidth(800); // Adjust width for subjects
            label4.getColumnModel().getColumn(1).setPreferredWidth(200); // Adjust width for teachers
            label4.getColumnModel().getColumn(2).setPreferredWidth(50); // Adjust width for grades

            
        } catch (SQLException ex) { // Exception for SQL
            Logger.getLogger(LoginUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database Offline!");
        } catch (ClassNotFoundException ex) { //Exception for Class.forName()
            Logger.getLogger(TeacherInfo.class.getName()).log(Level.SEVERE, null, ex);
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

        logo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        jLabel5 = new javax.swing.JLabel();
        label2 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        label4 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        label3 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 800));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/School Icon/StoreLogo.scale-300.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Student Name:");

        label1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Section:");

        label2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        label4.setBackground(new java.awt.Color(242, 242, 242));
        label4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        label4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "SUBJECTS", "TEACHERS", "GRADES"
            }
        ));
        label4.setGridColor(new java.awt.Color(0, 0, 0));
        label4.setRowHeight(40);
        label4.setSelectionBackground(new java.awt.Color(242, 242, 242));
        label4.setShowGrid(true);
        label4.getTableHeader().setResizingAllowed(false);
        label4.getTableHeader().setReorderingAllowed(false);
        label4.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(label4);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Student ID:");

        label3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1904, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // Log out action
        dispose(); // Close the current frame
        try {
            new LoginUI().setVisible(true); // Open the login menu
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentUI("yourUserID").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JTable label4;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
