package quanlysanpham;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterUI extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public RegisterUI() {
        setTitle("Đăng Kí");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        // Label và TextField cho Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtUsername, gbc);

        // Label và TextField cho Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtPassword, gbc);

        // Nút Đăng Kí
        JButton btnRegister = new JButton("Đăng Kí");
        btnRegister.addActionListener(e -> handleRegister());

        // Tạo panel cho nút Đăng Kí
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRegister);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        add(panel);
        setVisible(true);
    }

    private void handleRegister() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên người dùng và mật khẩu không được để trống!");
            return;
        }

        try (Connection conn = MySQLConnection.getConnection()) {
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Username đã tồn tại hoặc lỗi cơ sở dữ liệu!");
        }
    }
}
