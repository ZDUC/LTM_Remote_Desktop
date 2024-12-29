package remove_desktop_rdp.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

class SendEvents implements MouseListener, MouseMotionListener, KeyListener {

    PrintWriter writer; // Đối tượng để ghi dữ liệu gửi đi qua socket
    int width, height; // Chiều rộng và chiều cao của frame
    private JPanel frame = null;

    // Constructor với các tham số là panel và socket
    SendEvents(JPanel frame, Socket s) {
       
        // Khởi tạo frame và thiết lập chiều rộng, chiều cao
        this.frame = frame;
        this.height = height;
        this.width = width;
        
        // Thêm các bộ lắng nghe sự kiện vào frame
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        
        System.out.println("Send Events called");
        
        // Lấy luồng đầu ra của socket để gửi sự kiện
        try {
            writer = new PrintWriter(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Sự kiện khi nhấn chuột
    @Override
    public void mousePressed(MouseEvent e) {
        writer.println("1"); // Gửi mã sự kiện "1" cho nhấn chuột
        System.out.println("Mouse Pressed");
        
        int click = e.getButton(); // Lấy nút chuột được nhấn
        writer.println(click); // Gửi nút chuột đã nhấn
        writer.flush(); // Đẩy dữ liệu ra ngoài
    }

    // Sự kiện khi thả chuột
    @Override
    public void mouseReleased(MouseEvent e) {
        writer.println("2"); // Gửi mã sự kiện "2" cho thả chuột
        System.out.println("Mouse released");
        
        int click = e.getButton(); // Lấy nút chuột được thả
        writer.println(click); // Gửi nút chuột đã thả
        writer.flush(); // Đẩy dữ liệu ra ngoài
    }
    
    // Sự kiện khi di chuyển chuột
    @Override
    public void mouseMoved(MouseEvent e) {
        writer.println("3"); // Gửi mã sự kiện "3" cho di chuyển chuột
        System.out.println("Mouse moved");
     
        double x = ((double) e.getX() / frame.getWidth()); // Tính tỷ lệ x trên frame
        double y = ((double) e.getY() / frame.getHeight()); // Tính tỷ lệ y trên frame
        writer.println(x); // Gửi vị trí x dưới dạng tỷ lệ
        writer.println(y); // Gửi vị trí y dưới dạng tỷ lệ
        writer.flush(); // Đẩy dữ liệu ra ngoài
    }

    // Sự kiện khi nhấn phím
    @Override
    public void keyPressed(KeyEvent e) {
        writer.println("4"); // Gửi mã sự kiện "4" cho nhấn phím
        int code = e.getKeyCode(); // Lấy mã phím đã nhấn
        writer.println(code); // Gửi mã phím đã nhấn
        System.out.println("Key Pressed");
        writer.flush(); // Đẩy dữ liệu ra ngoài
    }

    // Sự kiện khi thả phím
    @Override
    public void keyReleased(KeyEvent e) {
        writer.println("5"); // Gửi mã sự kiện "5" cho thả phím
        int code = e.getKeyCode(); // Lấy mã phím đã thả
        writer.println(code); // Gửi mã phím đã thả
        System.out.println("Key Released");
        writer.flush(); // Đẩy dữ liệu ra ngoài
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Không xử lý sự kiện này
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Không xử lý sự kiện này
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Không xử lý sự kiện này
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Không xử lý sự kiện này
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không xử lý sự kiện này
    }
}
