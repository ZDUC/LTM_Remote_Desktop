package remove_desktop_rdp.server;

import java.io.Serializable;

/* Sự kiện tệp dùng cho quản lý tệp */
public class FileEvent implements Serializable {

    public FileEvent() {
    }

    private static final long serialVersionUID = 1L;
    
	/* Các tham số cho hệ thống tệp */
    private String destinationDirectory;
    private String sourceDirectory;
    private String filename;
    private long fileSize;
    private byte[] fileData;
    private String status;

        /* Thư mục đích */
    public String getDestinationDirectory() {
        return destinationDirectory;
    }
	
	/* Đặt thư mục đích */
    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

	/* Lấy thư mục nguồn */
    public String getSourceDirectory() {
        return sourceDirectory;
    }
   
	/* Đặt thư mục nguồn */
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

	/* Lấy tên tệp */
    public String getFilename() {
        return filename;
    }

	/* Đặt tên tệp */
    public void setFilename(String filename) {
        this.filename = filename;
    }

	/* Lấy kích thước tệp */
    public long getFileSize() {
        return fileSize;
    }

	/* Đặt kích thước tệp */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

	/* Lấy trạng thái */
    public String getStatus() {
        return status;
    }

	/* Đặt trạng thái */
    public void setStatus(String status) {
        this.status = status;
    }

	/* Lấy dữ liệu tệp */
    public byte[] getFileData() {
        return fileData;
    }

	/* Đặt dữ liệu tệp */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
