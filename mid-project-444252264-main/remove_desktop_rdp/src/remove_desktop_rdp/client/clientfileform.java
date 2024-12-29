/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package remove_desktop_rdp.client;

/**
 *
 * @author Administrator
 */

public class clientfileform extends javax.swing.JFrame {
    /* Địa chỉ IP */
    static String ip=""; // Khai báo biến tĩnh để lưu địa chỉ IP

    // Constructor của lớp, nhận địa chỉ IP như một tham số
    public clientfileform(String ip) {
        this.ip = ip; // Gán giá trị địa chỉ IP cho biến tĩnh
        initComponents(); // Gọi hàm khởi tạo các thành phần giao diện
    }

    @SuppressWarnings("unchecked")
                         
    private void initComponents() {

        // Các thành phần giao diện
        jLabel1 = new javax.swing.JLabel(); // Nhãn tiêu đề
        jLabel2 = new javax.swing.JLabel(); // Nhãn hướng dẫn
        jLabel3 = new javax.swing.JLabel(); // Nhãn bổ sung
        jButton1 = new javax.swing.JButton(); // Nút tải lên
        jButton2 = new javax.swing.JButton(); // Nút tải xuống
        jLabel4 = new javax.swing.JLabel(); // Nhãn để hiển thị hình ảnh

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // Đặt hành động khi đóng cửa sổ
        getContentPane().setLayout(null); // Sử dụng bố cục null để định vị thành phần

        /* Giao diện người dùng cho truyền tệp của khách hàng */
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 28)); // Thiết lập phông chữ cho tiêu đề
        jLabel1.setForeground(new java.awt.Color(0, 0, 0)); // Thiết lập màu chữ cho tiêu đề
        jLabel1.setText("Client File Transfer"); // Đặt văn bản cho tiêu đề
        getContentPane().add(jLabel1); // Thêm nhãn tiêu đề vào khung chứa
        jLabel1.setBounds(270, 76, 256, 37); // Đặt vị trí và kích thước cho nhãn

        /* Tùy chọn tải lên hoặc tải xuống */
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập phông chữ cho nhãn hướng dẫn
        jLabel2.setForeground(new java.awt.Color(0, 0, 0)); // Thiết lập màu chữ cho nhãn
        jLabel2.setText("Select if you want to upload or download a file "); // Đặt văn bản cho nhãn
        getContentPane().add(jLabel2); // Thêm nhãn vào khung chứa
        jLabel2.setBounds(122, 176, 542, 32); // Đặt vị trí và kích thước cho nhãn

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập phông chữ cho nhãn bổ sung
        jLabel3.setForeground(new java.awt.Color(0, 0, 0)); // Thiết lập màu chữ cho nhãn
        jLabel3.setText("from server's system"); // Đặt văn bản cho nhãn bổ sung
        getContentPane().add(jLabel3); // Thêm nhãn vào khung chứa
        jLabel3.setBounds(272, 214, 237, 32); // Đặt vị trí và kích thước cho nhãn

        // Nút tải lên
        jButton1.setBackground(new java.awt.Color(0, 0, 0)); // Thiết lập màu nền cho nút
        jButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập phông chữ cho nút
        jButton1.setForeground(new java.awt.Color(255, 255, 255)); // Thiết lập màu chữ cho nút
        jButton1.setText("Upload"); // Đặt văn bản cho nút
        // Thêm hành động cho nút khi nhấn
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt); // Gọi hàm xử lý sự kiện nhấn nút
            }
        });
        getContentPane().add(jButton1); // Thêm nút vào khung chứa
        jButton1.setBounds(272, 290, 237, 48); // Đặt vị trí và kích thước cho nút

        // Nút tải xuống
        jButton2.setBackground(new java.awt.Color(0, 0, 0)); // Thiết lập màu nền cho nút
        jButton2.setFont(new java.awt.Font("Dialog", 1, 24)); // Thiết lập phông chữ cho nút
        jButton2.setForeground(new java.awt.Color(255, 255, 255)); // Thiết lập màu chữ cho nút
        jButton2.setText("Download"); // Đặt văn bản cho nút
        // Thêm hành động cho nút khi nhấn
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt); // Gọi hàm xử lý sự kiện nhấn nút
            }
        });
        getContentPane().add(jButton2); // Thêm nút vào khung chứa
        jButton2.setBounds(272, 356, 237, 48); // Đặt vị trí và kích thước cho nút

        getContentPane().add(jLabel4); // Thêm nhãn hình ảnh vào khung chứa
        jLabel4.setBounds(0, 0, 800, 700); // Đặt vị trí và kích thước cho nhãn hình ảnh

        pack(); // Đóng gói các thành phần giao diện
    } // </editor-fold>                        

    // Phương thức xử lý khi nhấn nút tải lên
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.setVisible(false); // Ẩn cửa sổ hiện tại

        /* Gọi uploadfileform khi nhấn nút tải lên */
        uploadfileform u = new uploadfileform(ip); // Tạo đối tượng uploadfileform với địa chỉ IP
        u.setBounds(550, 150, 700, 300); // Đặt vị trí và kích thước cho uploadfileform
        u.setVisible(true); // Hiện cửa sổ uploadfileform
    }                                        

    // Phương thức xử lý khi nhấn nút tải xuống
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                          

        /* Gọi downloadfileform khi nhấn nút tải xuống */
        this.setVisible(false); // Ẩn cửa sổ hiện tại
        downloadfileform d = new downloadfileform(ip); // Tạo đối tượng downloadfileform với địa chỉ IP
        d.setBounds(550, 150, 700, 300); // Đặt vị trí và kích thước cho downloadfileform
        d.setVisible(true); // Hiện cửa sổ downloadfileform
    }                                        

    // Phương thức chính để khởi động ứng dụng
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { // Kiểm tra nếu giao diện Nimbus có sẵn
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); // Đặt giao diện thành Nimbus
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(clientfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Tạo và hiển thị cửa sổ */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clientfileform(ip).setVisible(true); // Tạo và hiển thị cửa sổ với địa chỉ IP
            }
        });
    }

    // Các biến thành phần giao diện
    private javax.swing.JButton jButton1; // Nút tải lên
    private javax.swing.JButton jButton2; // Nút tải xuống
    private javax.swing.JLabel jLabel1; // Nhãn tiêu đề
    private javax.swing.JLabel jLabel2; // Nhãn hướng dẫn
    private javax.swing.JLabel jLabel3; // Nhãn bổ sung
    private javax.swing.JLabel jLabel4; // Nhãn hình ảnh
}
