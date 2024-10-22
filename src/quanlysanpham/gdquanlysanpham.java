package quanlysanpham;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
	
	JLabel lbltt = new JLabel("");
	JComboBox comboBox = new JComboBox();
	private JTable table;
	
	Vector Vtieude=new Vector();
	Vector Vndung=new Vector();
	Vector Vdong;
	float tt=0;
	
	DefaultTableModel dtm=new DefaultTableModel();
	
	void Tieude() {
		Vtieude.add("Mã sp");
		Vtieude.add("Tên sp");
		Vtieude.add("Loại sp");
		Vtieude.add("Khuyến mãi");
		Vtieude.add("Số lượng");
		Vtieude.add("Đơn giá");
		Vtieude.add("Thành tiền");
	}
	
	void Nhap(SANPHAM SP){
		Vdong =new Vector();
		Vdong.add(SP.getMsp());
		Vdong.add(SP.getTensp());
		if(comboBox.getSelectedIndex()==0) {
			Vdong.add("Điện thoại");
			Vdong.add("20%");
		}
		if(comboBox.getSelectedIndex()==1) {
			Vdong.add("Điện máy");
			Vdong.add("10%");
		}
		Vdong.add(SP.getSoluong());
		Vdong.add(SP.getDongia());
		Vdong.add(SP.tinhtien());
		Vndung.add(Vdong);
		tt+=SP.tinhtien();
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gdquanlysanpham frame = new gdquanlysanpham();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		comboBox.setMaximumRowCount(10);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Điện thoại", "Điện máy"}));
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
		            Vector selectedRowData = (Vector) Vndung.get(selectedRow);

		            txtmasp.setText((String) selectedRowData.get(0)); 
		            txttensp.setText((String) selectedRowData.get(1));  
		            if (selectedRowData.get(2).equals("Điện thoại")) {
		                comboBox.setSelectedIndex(0); 
		            } else if (selectedRowData.get(2).equals("Điện máy")) {
		                comboBox.setSelectedIndex(1);
		            }
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
		                Vector selectedRowData = (Vector) Vndung.get(selectedRow);
		                
		                selectedRowData.set(0, txtmasp.getText());  
		                selectedRowData.set(1, txttensp.getText());  
		                if (comboBox.getSelectedIndex() == 0) {
		                    selectedRowData.set(2, "Điện thoại");  
		                    selectedRowData.set(3, "20%");  
		                } else {
		                    selectedRowData.set(2, "Điện máy");
		                    selectedRowData.set(3, "10%");
		                }
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
		btnEdit.setBounds(155, 162, 85, 21);  
		contentPane.add(btnEdit);

		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        int selectedRow = table.getSelectedRow();

		        if (selectedRow != -1) {
		            Vector selectedRowData = (Vector) Vndung.get(selectedRow);
		            float thanhtien = (float) selectedRowData.get(6);  
		            tt -= thanhtien; 
		            lbltt.setText("" + tt);  

		            Vndung.remove(selectedRow);

		            dtm.setDataVector(Vndung, Vtieude);
		            table.setModel(dtm);
		        } else {
		        	 Vndung.clear();  
		             dtm.setDataVector(Vndung, Vtieude); 
		             table.setModel(dtm); 
		             
		             tt = 0;
		             lbltt.setText("0");
		        }
		    }
		});
		btnNewButton_1.setBounds(258, 162, 85, 21);
		contentPane.add(btnNewButton_1);
		
		
		
		JButton btnNewButton_1_1 = new JButton("Thoát");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1.setBounds(361, 162, 85, 21);
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 194, 504, 106);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 sp", "T\u00EAn sp", "Lo\u1EA1i sp", "S\u1ED1 l\u01B0\u1EE3ng", "Khuy\u1EBFn M\u00E3i", "\u0110\u01A1n Gi\u00E1", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tổng thành tiền");
		lblNewLabel_1_2.setBounds(199, 310, 110, 16);
		contentPane.add(lblNewLabel_1_2);
		
		
		lbltt.setForeground(Color.RED);
		lbltt.setBounds(290, 310, 48, 16);
		contentPane.add(lbltt);
	}
}
