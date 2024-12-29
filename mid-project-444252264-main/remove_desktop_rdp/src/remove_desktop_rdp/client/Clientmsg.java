/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package remove_desktop_rdp.client;

import java.awt.Dimension;
import java.awt.Frame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class clientmsg extends javax.swing.JFrame {

    /* Định nghĩa Socket */
    static Socket sock;

    /* Định nghĩa luồng nhập và luồng xuất */
    static DataInputStream dtinpt;
    static DataOutputStream dtotpt;
    static String ip = "";
   
    public clientmsg(String ip) {
        this.ip = ip;
        initComponents();
    }

    /* Hàm để gửi tin nhắn */
    public void sendmess() {
        try {
            System.out.println("Phương thức gửi tin nhắn đã được gọi ở phía Client");
          
            String serverName = ip;             
            sock = new Socket(serverName, 1201);

            /* Nhận tin nhắn từ luồng nhập */
            dtinpt = new DataInputStream(sock.getInputStream());

            /* Gửi tin nhắn từ luồng xuất */
            dtotpt = new DataOutputStream(sock.getOutputStream());

            /* Cuộc hội thoại sẽ tiếp diễn cho đến khi nhận được tin nhắn "bye" */
            String msg = "";
            while (!msg.equals("bye")) {

                /* Nhận chuỗi và gửi đi */
                msg = dtinpt.readUTF();
                System.out.println("Tin nhắn nhận được: " + msg);

                /* Hiển thị tin nhắn trong hộp văn bản */
                jTextArea1.setText(jTextArea1.getText().trim() + "\n Server:" + msg);
              
            }
        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Client side");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(331, 29, 122, 32);

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(67, 79, 655, 235);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nhập tin nhắn mà bạn muốn gửi");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(67, 370, 468, 32);

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 600, 180, 48);

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jTextField1);
        jTextField1.setBounds(67, 436, 655, 94);

        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 800, 700);

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
        try {
            String msgout = "";
            msgout = jTextField1.getText().trim();
            dtotpt.writeUTF(msgout);
            System.out.println("Tin nhắn đã gửi:" + msgout);
            jTextField1.setText(null);
        } catch (Exception ex) {
        }
    }                                        

    static void display() {
        clientmsg c = new clientmsg(ip);
        c.getContentPane().setSize(800, 700);
       
        c.setLocationRelativeTo(null);
        c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                display();
                new clientmsg(ip).setVisible(true);

                
            }
        });

    }

    // Khai báo các biến - không thay đổi
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // Kết thúc khai báo biến                   
}
