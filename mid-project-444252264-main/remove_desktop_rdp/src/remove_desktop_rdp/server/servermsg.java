
package remove_desktop_rdp.server;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class servermsg extends javax.swing.JFrame {

    static ServerSocket ssckt; // Khởi tạo ServerSocket
    static Socket sckt; // Khởi tạo Socket để kết nối với client
    static DataInputStream dtinpt; // Đối tượng để nhận dữ liệu từ client
    static DataOutputStream dtotpt; // Đối tượng để gửi dữ liệu tới client

    public void sendmsg() {
        System.out.println("Phương thức gửi tin nhắn được gọi từ phía Server");

        String msg = "";
        try {
            ssckt = new ServerSocket(1201); // Tạo socket server tại cổng 1201
            sckt = ssckt.accept(); // Chấp nhận kết nối từ client
            dtinpt = new DataInputStream(sckt.getInputStream()); // Nhận dữ liệu từ client
            dtotpt = new DataOutputStream(sckt.getOutputStream()); // Gửi dữ liệu tới client

            while (!msg.equals("bye")) { // Khi tin nhắn không phải là "bye"
                msg = dtinpt.readUTF(); // Đọc tin nhắn từ client
                System.out.println("Tin nhắn nhận được: " + msg);
                jTextArea2.setText(jTextArea2.getText().trim() + "\n Client:" + msg); // Hiển thị tin nhắn từ client
            }

        } catch (Exception ex) {
            // Xử lý ngoại lệ nếu có lỗi
        }
    }

    public servermsg() {
        initComponents(); // Khởi tạo các thành phần giao diện
    }

    @SuppressWarnings("unchecked")                     
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập font chữ cho nhãn
        jLabel1.setText("Server side");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(331, 29, 121, 32);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập font chữ cho nhãn
        jLabel2.setText("Nhập tin nhắn muốn gửi");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(67, 370, 466, 32);

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập font chữ cho nút
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt); // Gọi phương thức khi nhấn nút Gửi
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(300, 590, 180, 39);

        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // Thiết lập font chữ cho ô nhập liệu
        getContentPane().add(jTextField1);
        jTextField1.setBounds(67, 432, 655, 95);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dialog", 0, 18)); // Thiết lập font chữ cho vùng hiển thị tin nhắn
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(67, 85, 655, 233);

        getContentPane().add(jLabel3);
        jLabel3.setBounds(-300, 0, 1100, 700);

        pack();
    }                       

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            String msgout = "";
            msgout = jTextField1.getText().trim(); // Lấy nội dung tin nhắn từ ô nhập liệu
            dtotpt.writeUTF(msgout); // Gửi tin nhắn tới client
            System.out.println("Tin nhắn gửi: " + msgout);
            jTextField1.setText(null); // Xóa nội dung ô nhập liệu sau khi gửi

        } catch (Exception ex) {
            // Xử lý ngoại lệ nếu có lỗi
        }
    }                                        

    static void display() {
        servermsg c = new servermsg();

        c.setPreferredSize(new Dimension(800, 700)); // Đặt kích thước cửa sổ
        c.setResizable(false); // Không cho phép thay đổi kích thước cửa sổ
        c.setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở giữa màn hình
        c.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Đóng cửa sổ khi nhấn nút Đóng
    }

    public static void main(String args[]) {
        /* Thiết lập giao diện Nimbus */
        //<editor-fold defaultstate="collapsed" desc=" Thiết lập giao diện (tùy chọn) ">
        /* Nếu Nimbus không có sẵn, sẽ giữ giao diện mặc định */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(servermsg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(servermsg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(servermsg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(servermsg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Tạo và hiển thị giao diện */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                display();
                new servermsg().setVisible(true);
            }
        });
    }
                   
    private javax.swing.JButton jButton1; // Nút gửi
    private javax.swing.JLabel jLabel1; // Nhãn tiêu đề
    private javax.swing.JLabel jLabel2; // Nhãn hướng dẫn nhập tin nhắn
    private javax.swing.JLabel jLabel3; // Nhãn trống
    private javax.swing.JScrollPane jScrollPane2; // Vùng cuộn cho jTextArea2
    private static javax.swing.JTextArea jTextArea2; // Vùng hiển thị tin nhắn
    private javax.swing.JTextField jTextField1; // Ô nhập liệu tin nhắn    
}
