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
		lblNewLabel.setBounds(10, 10, 61, 13);
		contentPane.add(lblNewLabel);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Điện thoại", "Điện máy"}));
		comboBox.setBounds(10, 50, 77, 19);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Mã sp");
		lblNewLabel_1.setBounds(97, 51, 48, 16);
		contentPane.add(lblNewLabel_1);
		
		txtmasp = new JTextField();
		txtmasp.setBounds(155, 50, 96, 19);
		contentPane.add(txtmasp);
		txtmasp.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên sp");
		lblNewLabel_1_1.setBounds(97, 78, 48, 16);
		contentPane.add(lblNewLabel_1_1);
		
		txttensp = new JTextField();
		txttensp.setColumns(10);
		txttensp.setBounds(155, 77, 96, 19);
		contentPane.add(txttensp);
		
		txtsl = new JTextField();
		txtsl.setColumns(10);
		txtsl.setBounds(155, 104, 96, 19);
		contentPane.add(txtsl);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1_1.setBounds(97, 105, 48, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtdg = new JTextField();
		txtdg.setColumns(10);
		txtdg.setBounds(155, 133, 96, 19);
		contentPane.add(txtdg);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Đơn giá");
		lblNewLabel_1_1_1_1.setBounds(97, 134, 48, 16);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SANPHAM s=null;
				String masp=txtmasp.getText();
				String tensp=txttensp.getText();
				float sl=Float.parseFloat(txtsl.getText());
				float dg=Float.parseFloat(txtdg.getText());
				if(comboBox.getSelectedIndex()==0) {
					s=new DIENTHOAI(masp,tensp,sl,dg);
				}
				if(comboBox.getSelectedIndex()==1) {
					s=new DIENMAY(masp,tensp,sl,dg);
				}
				lbltt.setText(""+tt);
				Nhap(s);
				dtm.setDataVector(Vndung, Vtieude);
				table.setModel(dtm);
			}
		});
		btnNewButton.setBounds(10, 162, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdg.setText("");
				txtmasp.setText("");
				txtsl.setText("");
				txttensp.setText("");
				Vdong.clear();
				dtm.setDataVector(Vdong, Vtieude);
				table.setModel(dtm);
				
			}
		});
		btnNewButton_1.setBounds(127, 162, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Thoát");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1.setBounds(241, 160, 85, 21);
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
		lblNewLabel_1_2.setBounds(170, 310, 85, 16);
		contentPane.add(lblNewLabel_1_2);
		
		
		lbltt.setForeground(Color.RED);
		lbltt.setBounds(290, 310, 48, 16);
		contentPane.add(lbltt);
	}
}
