package quanlysanpham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String DATABASE_NAME = "quanlysanpham"; // Tên cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false";
    private static final String USER = "root";  // Tên người dùng
    private static final String PASSWORD = "1234"; // Mật khẩu

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy Driver JDBC!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Kết nối cơ sở dữ liệu thất bại!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection(); // Gọi phương thức getConnection để kiểm tra kết nối
    }
}
