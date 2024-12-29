
package remove_desktop_rdp.server;

import java.awt.*; // Thư viện của Java AWT để tạo giao diện đồ họa
import java.net.*; // Thư viện java.net để sử dụng các socket
import java.sql.SQLException; // Thư viện SQLException để xử lý lỗi SQL
import java.text.*; // Sử dụng định dạng ngày tháng
import java.util.*; // Các lớp tiện ích của Java
import java.awt.image.BufferedImage; // Làm việc với hình ảnh
import java.io.*; // Xử lý các tệp và luồng I/O
import java.util.logging.*; // Ghi log
import javax.imageio.ImageIO; // Xử lý nhập/xuất hình ảnh
import javax.swing.*; // Các thành phần giao diện đồ họa Swing


public class Server extends Thread {

    // Khởi tạo Socket cho server và client
    private static ServerSocket serverSocket = null; // Nhận yêu cầu từ cl
    private static Socket server = null; // Cl giao tiếp với sv
    private static ServerSocket eveSocket = null; // Nhận các sự kiện từ cl
    private static Socket eve = null; // Nhận sự kiện từ cl

    // Khởi tạo Robot và các Thread
    static Robot robot = null; // Đối tượng Robot thao tác với màn hình
    static Thread Server_Thread_screen = null; // Thread gửi ảnh màn hình cho cl
    static Thread Server_Thread_eve = null; // Thread nhận sự kiện từ cl
    static Thread Server_Thread_msg = null; // Thread trò chuyện với cl

    // Constructor khởi tạo sv với ba luồng
    public Server(Thread ServerThread1, Thread ServerThread2, Thread ServerThread3) throws IOException, SQLException, ClassNotFoundException, Exception {
        
        // Giá trị luồng và các biến tương ứng
        this.Server_Thread_screen = ServerThread1;
        this.Server_Thread_eve = ServerThread2;
        this.Server_Thread_msg = ServerThread3;
        
        // Tạo các socket trên các cổng khác nhau
        serverSocket = new ServerSocket(8087); // Cổng 8087 để gửi màn hình
        eveSocket = new ServerSocket(8888); // Ccổng 8888 để nhận sự kiện
        serverSocket.setSoTimeout(1172003); // Đặt thời gian chờ cho socket
        
        // Tạo màn hình khởi động
        serverstartscreen s = new serverstartscreen();
        s.setVisible(true);
    }

    // Khởi động sv
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, Exception {

        // Luồng để gửi ảnh chụp màn hình
        Server_Thread_screen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Tạo đối tượng Robot mới để thao tác màn hình
                    robot = new Robot();
                } catch (AWTException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex); // Ghi log nếu có lỗi
                }
                
                // Tạo vòng lặp xử lý yêu cầu cl
                while (true) {
                    try {
                        
                        
                        server = serverSocket.accept();
                        
                        // Gửi ảnh màn hình cho cl
                        sendScreen(robot);
                       
                    } catch (SocketTimeoutException st) {
                        System.out.println("Socket timed out!"); // Thông báo nếu socket hết thời gian chờ
                        break;
                    } catch (IOException e) {
                        e.printStackTrace(); // Xử lý lỗi I/O
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex); // In ra lỗi khác
                    }
                }
            }
        });

        // Luồng để nhận sự kiện từ cl
        Thread Server_Thread_eve = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    // Tạo đối tượng Robot mới để thao tác màn hình
                    robot = new Robot();
                } catch (AWTException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex); // Ghi log nếu có lỗi
                }
                // Vòng lặp chờ yêu cầu từ cl
                while (true) {
                    try {
                        
                        eve = eveSocket.accept();
                       
                        // Nhận sự kiện từ cl và xử lý
                        new ReceiveEvents(eve, robot);
                    } catch (SocketTimeoutException st) {
                        System.out.println("Socket timed out!"); // Thông báo nếu socket hết thời gian chờ
                        break;
                    } catch (IOException e) {
                        e.printStackTrace(); // Xử lý lỗi I/O
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex); // In ra lỗi khác
                    }
                }
            }
        });

        // Luồng để trò chuyện với cl
        Thread Server_Thread_msg = new Thread(new Runnable() {
            @Override
            public void run() {
                // Vòng lặp trò chuyện với cl
                while (true) {
                    
                    // Gọi phương thức gửi tin nhắn cho cl
                    servermsg s = new servermsg();
                    s.sendmsg(); // Gửi tin nhắn
                    s.repaint(); // Vẽ lại giao diện
                    s.pack(); // Cập nhật kích thước giao diện
                }
            }
        });

        // Tạo đối tượng của sv với ba luồng
        Server serverApp = new Server(Server_Thread_screen, Server_Thread_eve, Server_Thread_msg);
        Thread thread = new Thread(serverApp); // Tạo luồng chính cho sv
        thread.start(); // Bắt đầu chạy luồng chính
        
        // Khởi động từng luồng
        Server_Thread_screen.start();
        Server_Thread_eve.start();
        Server_Thread_msg.start();
    }
    
    // Phương thức gửi ảnh chụp màn hình cho cl
    private static void sendScreen(Robot robot) throws AWTException, IOException {
        
        // Lấy kích thước màn hình hiện tại
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimensions = toolkit.getScreenSize();
        
        // Chụp ảnh màn hình bằng đối tượng robot
        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(dimensions));
        
        // Gửi hình ảnh cho cl qua luồng xuất của socket
        ImageIO.write(screenshot, "png", server.getOutputStream());
    }

}
