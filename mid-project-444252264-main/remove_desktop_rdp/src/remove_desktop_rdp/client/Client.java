package remove_desktop_rdp.client;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Client extends Thread {

    // Lớp `Control` để đồng bộ chiều cao và chiều rộng
    class Control {
        public AtomicInteger height = new AtomicInteger(0);               
        public AtomicInteger width = new AtomicInteger(0);
        public volatile JFrame frame;
        public volatile JPanel panel;
    }

    final Control c = new Control();
    clientfirstpage c1 = new clientfirstpage();               
    private static long nextTime = 0;
    private static Client clientApp = null;
    private String serverName = c1.ipAddress.getText(); // Địa chỉ IP mặc định
    private static int portNo = 8087; // Cổng mặc định
    static String ip = "";
    
    // Luồng nhận màn hình từ máy chủ
    class T1 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // Lấy kích thước màn hình
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Dimension dimensions = toolkit.getScreenSize();               
                    
                    // Tạo socket mới để nhận màn hình từ máy chủ
                    Socket serverSocket = new Socket(serverName, portNo);         
                  
                    // Lấy tên host của máy chủ
                    String fileName = serverSocket.getInetAddress().getHostName().replace(".", "-"); 
                    System.out.println(fileName);
                    
                    // Đọc hình ảnh từ socket
                    BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(serverSocket.getInputStream()));  
                   
                    // Lấy chiều cao và chiều rộng của ảnh
                    c.height.set(img.getHeight());                                   
                    c.width.set(img.getWidth()); 
 
                    // Thiết lập kích thước của `panel`
                    c.panel.setSize(dimensions.width, dimensions.height);  
                     
                    JLabel lab;
                    
                    // Đưa hình ảnh vào một nhãn để hiển thị
                    lab = new JLabel(new ImageIcon((new ImageIcon(img).getImage().getScaledInstance(c.panel.getWidth(), c.panel.getHeight(), java.awt.Image.SCALE_SMOOTH))));
                    
                    // Thêm nhãn vào `panel`
                    c.panel.add(lab);      
                    c.frame.repaint();       
                    c.frame.pack();

                    // Tạm dừng trong một khoảng thời gian ngắn
                    sleep(1000);             
                    
                    // Xóa nhãn để chuẩn bị cho ảnh tiếp theo
                    c.panel.remove(lab);   

                    System.out.println("Đã nhận ảnh");

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Luồng gửi sự kiện từ client đến máy chủ
    class T2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    // Tạo socket để gửi sự kiện
                    Socket eve = new Socket(serverName, 8888);   

                    // Tạo một thể hiện của lớp SendEvents để xử lý sự kiện
                    new SendEvents(c.panel, eve);               
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Luồng cho chức năng chat
    class T3 implements Runnable {
        @Override
        public void run() {
            clientmsg c = new clientmsg(ip); // Tạo giao diện chat với IP của máy chủ
            c.sendmess(); // Gửi tin nhắn
            c.repaint();
            c.pack();
        }
    }
    
    // Hàm tạo `Client` với IP của máy chủ
    Client(String ip) {
        // Khởi tạo frame và panel
        c.frame = new JFrame();
        c.panel = new JPanel();
        
        // Đặt kích thước cho frame và panel
        c.frame.setSize(1930, 1050);
        c.panel.setSize(1930, 1050);
        c.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        // Thêm `panel` vào `frame`
        c.frame.add(c.panel);
        c.frame.pack();
        c.frame.setVisible(true);
        
        // Thiết lập địa chỉ IP của máy chủ
        this.ip = ip;
        serverName = ip;
        
        // Tạo ba luồng T1, T2 và T3
        T1 t1 = new T1();
        T2 t2 = new T2();
        T3 t3 = new T3();
        
        // Khởi động các luồng
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}
