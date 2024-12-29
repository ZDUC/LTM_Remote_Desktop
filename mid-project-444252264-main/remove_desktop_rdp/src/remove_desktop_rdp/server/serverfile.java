package remove_desktop_rdp.server;

import remove_desktop_rdp.server.FileEvent;
import java.net.*;
import java.io.*;

public class serverfile {

    /* Định nghĩa socket và các tham số */
    private Socket socket = null;
    private File dstFile = null;
    private static ObjectOutputStream outputStream = null;
    private static ObjectInputStream inputStream = null;
    private FileEvent fileEvent = null;
    private static String fname = null;
    private FileOutputStream fileOutputStream = null;
    private String destinationPath1 = "";

    public void fileServer() throws IOException {
        ServerSocket sersock = new ServerSocket(1234);
        System.out.println("Server đã sẵn sàng ");
        Socket sock = sersock.accept();
        System.out.println("Kết nối thành công ");

        serverfile o1 = new serverfile();
    
        /* Định nghĩa luồng đầu vào và đọc dòng từ client */
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        fname = fileRead.readLine();

        /* fname là tên tệp được cung cấp bởi client */
        if (fname.contains("download")) {

            System.out.println(removeWord(fname, "download"));

            /* Loại bỏ từ "download" khỏi chuỗi */
            fname = removeWord(fname, "download");
            outputStream = new ObjectOutputStream(sock.getOutputStream());
            
            /* Gọi hàm gửi tệp */
            o1.sendFile();
            istream.close();
            fileRead.close();
        } else {
            
            /* Nếu không phải download thì là tùy chọn tải lên tệp */
            inputStream = new ObjectInputStream(sock.getInputStream());
            
            /* Client tải lên tệp nên server sẽ tải về */
            o1.downloadFile();
        }

        sock.close();
        sersock.close();

    }

    public static void main(String args[]) throws Exception {                           // thiết lập kết nối với server

    }

    /* Hàm để loại bỏ từ khỏi chuỗi */
    public static String removeWord(String string, String word) {
        if (string.contains(word)) {
            String tempWord = word + " ";
            string = string.replaceAll(tempWord, "");
            tempWord = " " + word;
            string = string.replaceAll(tempWord, "");
        }
        return string;
    }

    public void sendFile() throws IOException {

        /* Quản lý sự kiện tệp */
        fileEvent = new FileEvent();
        String fileName = fname.substring(fname.lastIndexOf("/") + 1, fname.length());
        String path = fname.substring(0, fname.lastIndexOf("/") + 1);
        fileEvent.setDestinationDirectory(destinationPath1);
        fileEvent.setFilename(fileName);
        fileEvent.setSourceDirectory(fname);
        File file = new File(fname);
        if (file.isFile()) {
            try {
                /* Đọc tên và dữ liệu tệp từ client */
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                /* Gửi tệp với tất cả các thuộc tính */
                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {

            /* Lỗi đường dẫn không hợp lệ */
            System.out.println("Đường dẫn không trỏ đến tệp");
            fileEvent.setStatus("Error");
        }

        try {
            outputStream.writeObject(fileEvent);
            System.out.println("Hoàn tất...Thoát chương trình");
            Thread.sleep(3000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /* Hàm tải xuống tệp */
    public void downloadFile() {
        try {

            /* Lỗi nếu không tìm thấy đường dẫn hoặc không xử lý sự kiện tệp */
            fileEvent = (FileEvent) inputStream.readObject();
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {
                System.out.println("Xảy ra lỗi ..Thoát chương trình");
                System.exit(0);
            }

            /* Đã tải xuống thành công */
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            if (!new File(fileEvent.getDestinationDirectory()).exists()) {
                new File(fileEvent.getDestinationDirectory()).mkdirs();
            }

            /* Đích của tệp đầu ra */
            dstFile = new File(outputFile);
            fileOutputStream = new FileOutputStream(dstFile);
            fileOutputStream.write(fileEvent.getFileData());
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("Tệp đầu ra : " + outputFile + " đã được lưu thành công ");
            Thread.sleep(3000);
            System.exit(0);

            /* Bắt lỗi */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}