package quanlysanpham;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gdquanlysanpham extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtmasp;
	private JTextField txttensp;
	private JTextField txtsl;
	private JTextField txtdg;

	JLabel lbltt = new JLabel("0");
	JComboBox<String> comboBox = new JComboBox<>();
	private JTable table;

	Vector<String> Vtieude = new Vector<>();
	Vector<Vector<Object>> Vndung = new Vector<>();
	float tt = 0;

	DefaultTableModel dtm = new DefaultTableModel();

	void Tieude() {
		Vtieude.add("Mã sp");
		Vtieude.add("Tên sp");
		Vtieude.add("Loại sp");
		Vtieude.add("Khuyến mãi");
		Vtieude.add("Số lượng");
		Vtieude.add("Đơn giá");
		Vtieude.add("Thành tiền");
	}

	void Nhap(SANPHAM SP) {
		Vector<Object> Vdong = new Vector<>();
		Vdong.add(SP.getMsp());
		Vdong.add(SP.getTensp());
		if (comboBox.getSelectedIndex() == 0) {
			Vdong.add("Điện thoại");
			Vdong.add("20%");
		} else {
			Vdong.add("Điện máy");
			Vdong.add("10%");
		}
		Vdong.add(SP.getSoluong());
		Vdong.add(SP.getDongia());
		Vdong.add(SP.tinhtien());
		Vndung.add(Vdong);
		tt += SP.tinhtien();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				gdquanlysanpham frame = new gdquanlysanpham();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public gdquanlysanpham() {
		Tieude();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("SẢN PHẨM");
		lblNewLabel.setBounds(10, 10, 85, 30);
		contentPane.add(lblNewLabel);

		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Điện thoại", "Điện máy"}));
		comboBox.setBounds(50, 50, 85, 21);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Mã sp");
		lblNewLabel_1.setBounds(145, 51, 48, 16);
		contentPane.add(lblNewLabel_1);

		txtmasp = new JTextField();
		txtmasp.setBounds(213, 50, 96, 19);
		contentPane.add(txtmasp);
		txtmasp.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tên sp");
		lblNewLabel_1_1.setBounds(145, 80, 48, 16);
		contentPane.add(lblNewLabel_1_1);

		txttensp = new JTextField();
		txttensp.setColumns(10);
		txttensp.setBounds(213, 79, 96, 19);
		contentPane.add(txttensp);

		txtsl = new JTextField();
		txtsl.setColumns(10);
		txtsl.setBounds(213, 108, 96, 19);
		contentPane.add(txtsl);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1_1.setBounds(145, 109, 58, 16);
		contentPane.add(lblNewLabel_1_1_1);

		txtdg = new JTextField();
		txtdg.setColumns(10);
		txtdg.setBounds(213, 133, 96, 19);
		contentPane.add(txtdg);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Đơn giá");
		lblNewLabel_1_1_1_1.setBounds(145, 134, 48, 16);
		contentPane.add(lblNewLabel_1_1_1_1);

		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SANPHAM s = null;
				String masp = txtmasp.getText();
				String tensp = txttensp.getText();
				float sl = Float.parseFloat(txtsl.getText());
				float dg = Float.parseFloat(txtdg.getText());

				if (comboBox.getSelectedIndex() == 0) {
					s = new DIENTHOAI(masp, tensp, sl, dg);
				} else if (comboBox.getSelectedIndex() == 1) {
					s = new DIENMAY(masp, tensp, sl, dg);
				}

				Nhap(s);
				insertProduct(s);  // Gọi phương thức insertProduct ở đây

				// Gọi phương thức kiểm tra sau khi thêm
				try (Connection conn = MySQLConnection.getConnection(); Statement stmt = conn.createStatement()) {
					ResultSet rs = stmt.executeQuery("SELECT * FROM sanpham");
					while (rs.next()) {
						System.out.println("Mã sp: " + rs.getString("masp") + ", Tên sp: " + rs.getString("tensp"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				lbltt.setText("" + tt);
				dtm.setDataVector(Vndung, Vtieude);
				table.setModel(dtm);

				txtmasp.setText("");
				txttensp.setText("");
				txtsl.setText("");
				txtdg.setText("");
				txtmasp.requestFocus();
			}
		});
		btnNewButton.setBounds(50, 162, 85, 21);
		contentPane.add(btnNewButton);

		JButton btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					Vector<Object> selectedRowData = Vndung.get(selectedRow);
					txtmasp.setText((String) selectedRowData.get(0));
					txttensp.setText((String) selectedRowData.get(1));
					comboBox.setSelectedIndex(selectedRowData.get(2).equals("Điện thoại") ? 0 : 1);
					txtsl.setText(selectedRowData.get(4).toString());
					txtdg.setText(selectedRowData.get(5).toString());
					btnEdit.setText("Cập nhật");
				} else {
					System.out.println("Vui lòng chọn sản phẩm cần chỉnh sửa!");
				}
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnEdit.getText().equals("Cập nhật")) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						Vector<Object> selectedRowData = Vndung.get(selectedRow);

						selectedRowData.set(0, txtmasp.getText());
						selectedRowData.set(1, txttensp.getText());
						selectedRowData.set(2, comboBox.getSelectedIndex() == 0 ? "Điện thoại" : "Điện máy");
						selectedRowData.set(3, comboBox.getSelectedIndex() == 0 ? "20%" : "10%");
						float soluong = Float.parseFloat(txtsl.getText());
						float dongia = Float.parseFloat(txtdg.getText());
						selectedRowData.set(4, soluong);
						selectedRowData.set(5, dongia);

						float thanhtienMoi = soluong * dongia * (comboBox.getSelectedIndex() == 0 ? 0.8f : 0.9f);
						selectedRowData.set(6, thanhtienMoi);

						float thanhtienCu = (float) selectedRowData.get(6);
						tt = tt - thanhtienCu + thanhtienMoi;
						lbltt.setText("" + tt);

						dtm.setDataVector(Vndung, Vtieude);
						table.setModel(dtm);

						btnEdit.setText("Sửa");

						txtmasp.setText("");
						txttensp.setText("");
						txtsl.setText("");
						txtdg.setText("");
						txtmasp.requestFocus();
					} else {
						System.out.println("Vui lòng chọn sản phẩm cần cập nhật!");
					}
				}
			}
		});

		btnEdit.setBounds(145, 162, 85, 21);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					Vector<Object> selectedRowData = Vndung.get(selectedRow);
					float thanhtien = (float) selectedRowData.get(6);
					tt -= thanhtien;
					lbltt.setText("" + tt);
					Vndung.remove(selectedRow);
					dtm.setDataVector(Vndung, Vtieude);
					table.setModel(dtm);
				} else {
					System.out.println("Vui lòng chọn sản phẩm cần xóa!");
				}
			}
		});
		btnDelete.setBounds(240, 162, 85, 21);
		contentPane.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 500, 120);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lbltt.setForeground(Color.RED);
		lbltt.setBounds(400, 320, 100, 30);
		contentPane.add(lbltt);
		
		JButton btnView = new JButton("Xem danh sách");
		btnView.setBounds(335, 162, 85, 21);
		contentPane.add(btnView);
		
		JLabel lblTongThanhTien = new JLabel("tổng thành tiền: ");
		lblTongThanhTien.setBounds(145, 325, 130, 16);
		contentPane.add(lblTongThanhTien);
		
		
		
		btnView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        // Clear existing data in the table
		        Vndung.clear();
		        
		        // Retrieve products from database
		        try (Connection conn = MySQLConnection.getConnection(); 
		             Statement stmt = conn.createStatement()) {
		            ResultSet rs = stmt.executeQuery("SELECT * FROM quanlysanpham.sanpham");
		            while (rs.next()) {
		                Vector<Object> Vdong = new Vector<>();
		                Vdong.add(rs.getString("masp"));
		                Vdong.add(rs.getString("tensp"));
		                Vdong.add(rs.getString("loaisp"));
		                Vdong.add(rs.getString("khuyenmai"));
		                Vdong.add(rs.getFloat("soluong"));
		                Vdong.add(rs.getFloat("dongia"));
		                Vdong.add(rs.getFloat("thanhtien"));
		                Vndung.add(Vdong);
		            }
		            // Refresh the table model with the new data
		            dtm.setDataVector(Vndung, Vtieude);
		            table.setModel(dtm);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		});
	}
	// Thêm sản phẩm vào CSDL
	private void insertProduct(SANPHAM product) {
		String query = "INSERT INTO sanpham (masp, tensp, loaisp, soluong, dongia, khuyenmai, thanhtien) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = MySQLConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, product.getMsp());
			pstmt.setString(2, product.getTensp());
			pstmt.setString(3, (comboBox.getSelectedIndex() == 0) ? "Điện thoại" : "Điện máy");
			pstmt.setFloat(4, product.getSoluong());
			pstmt.setFloat(5, product.getDongia());
			pstmt.setString(6, (comboBox.getSelectedIndex() == 0) ? "20%" : "10%");
			pstmt.setFloat(7, product.tinhtien());

			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Số bản ghi đã thêm: " + rowsAffected);
			if (rowsAffected > 0) {
				System.out.println("Thêm sản phẩm thành công!");
			} else {
				System.out.println("Không có sản phẩm nào được thêm!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
