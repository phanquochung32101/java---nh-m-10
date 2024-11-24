package quanlysanpham;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    public LoginUI() {
        setTitle("Đăng Nhập");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        btnRegister = new JButton("Đăng Kí");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        btnLogin.addActionListener(e -> handleLogin());
        btnRegister.addActionListener(e -> openRegisterForm());

        add(panel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        User user = new User(username, password);

        if (checkCredentials(user)) {
            dispose(); // Đóng giao diện LoginUI
            gdquanlysanpham frame = new gdquanlysanpham();
            frame.setVisible(true); // Hiển thị giao diện quản lý sản phẩm
        } else {
            JOptionPane.showMessageDialog(this, "Tên người dùng hoặc mật khẩu sai!");
        }
    }

    private boolean checkCredentials(User user) {
        boolean isValid = false;
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!");
        }

        return isValid;
    }

    private void openRegisterForm() {
        new RegisterUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::new);
    }
}
