package remove_desktop_rdp.server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveEvents {

    //Constructor
    ReceiveEvents(Socket socket, Robot robot) throws IOException, AWTException {
    
        // nhận thao tác được gửi từ Client
        Scanner scanner = new Scanner(socket.getInputStream());
        
        // kich thước màn hình từ server
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimensions = toolkit.getScreenSize();
        
        // tạo khung màn hình 
        Robot re = new Robot();
        BufferedImage screenCap = re.createScreenCapture(new Rectangle(dimensions));
        
        int height = screenCap.getHeight();
        int width = screenCap.getWidth();
        
        // nhận id của event
        while (true) {
            try {
                
                // id của event nhận từ stream
                int event = scanner.nextInt();
                System.out.println(event);
                
                // thực hiện hành động theo id
                switch (event) {
                    case 1:
                        int m = InputEvent.getMaskForButton(event);
                        robot.mousePress(m);
                        System.out.println("Nhấp chuột");
                        break;
                    case 2:
                        int m1 = InputEvent.getMaskForButton(event);
                        robot.mouseRelease(m1);
                        System.out.println("Nhả chuột");
                        break;
                    case 3:
                        robot.mouseMove((int) (scanner.nextDouble() * width), (int) (scanner.nextDouble() * height));
                        System.out.println("Di chuyển chuột");
                        break;
                    case 4:
                        robot.keyPress(scanner.nextInt());
                        System.out.println("Bấm phím");
                        break;
                    case 5:
                        robot.keyRelease(scanner.nextInt());
                        System.out.println("Nhả phím");
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {

                System.out.println("Exception in receive events:" + ex);
            }
        }

    }
}