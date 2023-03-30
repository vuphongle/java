package GK;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BandCombineOp;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class GUI extends JFrame implements ActionListener, MouseListener{
	private JButton btnTim, btnThem, btnXoa, btnSua, btnXoaTrang, btnLuu;
	private JTextField txtTim, txtMaNV, txtTen, txtTuoi, txtLuong;
	private String[] cols = { "Ma", "Tên", "Tuoi", "Gioi tinh", "Tien Luong", "Phong Ban" };
	private DefaultTableModel model;
	private JTable table;
	private JRadioButton rdoNam = new JRadioButton(), rdoNu = new JRadioButton();
	private ButtonGroup group = new ButtonGroup();
	private JComboBox<String> cbo = new JComboBox<String>();
	private DanhSachNV listnv = new DanhSachNV();
	private StoredData std = new StoredData();

	public GUI() {
		setTitle("Thông tin nhân viên");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		JPanel pnNorth, pnCenter, pnSouth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		add(pnCenter = new JPanel(), BorderLayout.CENTER);
		add(pnSouth = new JPanel(), BorderLayout.SOUTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		pnNorth.add(lblTieuDe);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);

		Box box = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		box.add(b1);
		box.add(Box.createVerticalStrut(5));
		box.add(b2);
		box.add(Box.createVerticalStrut(5));
		box.add(b3);
		box.add(Box.createVerticalStrut(5));
		box.add(b4);
		box.add(Box.createVerticalStrut(5));
		box.add(b5);
		box.add(Box.createVerticalStrut(5));

		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		txtMaNV = new JTextField(50);
		b1.add(lblMaNV);
		b1.add(txtMaNV);

		JLabel lblTen = new JLabel("Tên nhân viên: ");
		txtTen = new JTextField(50);
		b2.add(lblTen);
		b2.add(txtTen);

		JLabel lblTuoi = new JLabel("Tuổi:");
		txtTuoi = new JTextField(50);
		b3.add(lblTuoi);
		b3.add(txtTuoi);

		JLabel lblSex = new JLabel("Giới tính: ");
		JLabel lblNam = new JLabel("Nam");
		JLabel lblNu = new JLabel("Nu");
		group.add(rdoNam = new JRadioButton());
		group.add(rdoNu = new JRadioButton());
		rdoNam.setSelected(true);

		b3.add(lblSex);
		b3.add(lblNam);
		b3.add(rdoNam);
		b3.add(lblNu);
		b3.add(rdoNu);

		JLabel lblLuong = new JLabel("Tiền lương: ");
		txtLuong = new JTextField(20);
		b4.add(lblLuong);
		b4.add(txtLuong);

		JLabel lblPhongban = new JLabel("Phòng ban: ");
		cbo.addItem("Phòng kỹ thuật");
		cbo.addItem("Phòng nhân sự");
		b5.add(lblPhongban);
		b5.add(cbo);
		pnCenter.add(box);

		JComboBox cboset = new JComboBox();
		cboset.addItem("Nam");
		cboset.addItem("Nữ");

		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		DefaultCellEditor de = new DefaultCellEditor(cboset);
		table.getColumnModel().getColumn(3).setCellEditor(de);
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(getPreferredSize());
		pnCenter.add(pane);

		try {
			listnv = (DanhSachNV) std.loadFile("Data//data.txt");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData(listnv.getDsNhanVien(), model);

		pnSouth.add(txtTim = new JTextField(10));
		pnSouth.add(btnTim = new JButton("Tìm"));
		pnSouth.add(btnThem = new JButton("Thêm"));
		pnSouth.add(btnXoaTrang = new JButton("Xóa trắng"));
		pnSouth.add(btnXoa = new JButton("Xóa"));
		pnSouth.add(btnSua = new JButton("Sửa"));
		pnSouth.add(btnLuu = new JButton("Lưu"));

		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		table.addMouseListener(this);
	}

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	private NhanVien creatNhanVien() {
		NhanVien item;
		String maNv, ten, phai, phongban;
		int tuoi;
		double luong;
		maNv = txtMaNV.getText();
		ten = txtTen.getText();
		if (rdoNam.isSelected())
			phai = "Nam";
		else
			phai = "Nu";
		tuoi = Integer.parseInt(txtTuoi.getText());
		luong = Double.parseDouble(txtLuong.getText());
		phongban = (String) cbo.getSelectedItem();
		item = new NhanVien(maNv, ten, tuoi, luong, phai, phongban);
		return item;
	}

	private void loadData(ArrayList<NhanVien> lstnv, DefaultTableModel model) {
		for (NhanVien nv : lstnv) {
			Object[] obj = { nv.getMaNV(), nv.getTenNV(), nv.getTuoi(), nv.getPhai(), nv.getTienLuong(),
					nv.getPhongBan() };
			model.addRow(obj);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtMaNV.setText("");
			txtTen.setText("");
			txtTuoi.setText("");
			txtLuong.setText("");
		} else if (o.equals(btnThem)) {
			if (txtMaNV.getText().trim().equals("") || txtTen.getText().trim().equals("")
					|| txtTuoi.getText().trim().equals("") || txtLuong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được rỗng!!");
			} else {
				NhanVien nhanvien = creatNhanVien();
				if (nhanvien != null) {
					if (listnv.themNhanVien(nhanvien)) {
						model.setRowCount(0);
						loadData(listnv.getDsNhanVien(), model);
					} else {
						JOptionPane.showMessageDialog(this, "Trung ma!");
					}
				}

			}
		} else if (o.equals(btnLuu)) {
			try {
				StoredData std = new StoredData();
				if (std.saveFile(listnv, "Data//data.txt"))
					JOptionPane.showConfirmDialog(this, "Luu thanh cong");
				else
					JOptionPane.showMessageDialog(this, "Luu that bai");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			if (txtTim.getText().equals("")) {
				JOptionPane.showConfirmDialog(this, "Hãy nhập!");
			} else {
				String ID = txtTim.getText();
				NhanVien nv = listnv.timNhanVien(ID);
				if (nv != null) {
					Object[] temp = { nv.getMaNV(), nv.getTenNV(), nv.getTuoi(), nv.getPhai(), nv.getTienLuong(),
							nv.getPhongBan() };
					table.setModel(model = new DefaultTableModel(cols, 0));
					model.addRow(temp);
				} else {
					JOptionPane.showConfirmDialog(this, "Không tim thấy");
				}
			}
		} else if (o.equals(btnSua)) {
			sua();
		} else if (o.equals(btnXoa)) {
			int selectRow = table.getSelectedRow();
			if (selectRow < 0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần xóa");
			} else {
				NhanVien nv = listnv.getDsNhanVien().get(selectRow);
				String id = nv.getMaNV();
				if ((JOptionPane.showConfirmDialog(this,
						"Bạn có muốn xóa nhân viên?" + "\nMã: " + nv.getMaNV() + "Tên" + nv.getTenNV(), "Lựa chọn",
						JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
					listnv.xoaNhanVien(nv);
					table.setModel(model = new DefaultTableModel(cols, 0));
					for (NhanVien nv1 : listnv.getDsNhanVien()) {
						Object[] temp = { nv1.getMaNV(), nv1.getTenNV(), nv1.getTuoi(), nv1.getPhai(), nv1.getTienLuong(),
								nv1.getPhongBan() };
						model.addRow(temp);
					}
				}
			}
		}
	}

	private void sua() {
		try {
			int selectedRow = table.getSelectedRow();
			if(selectedRow < 0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần sửa");
				return;
			}
			NhanVien nv = creatNhanVien();
			if(nv == null)
				return;
			listnv.replaceID(nv);
			model.removeRow(selectedRow);
			model.insertRow(selectedRow, nv.getObjectNv());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Không thể sửa mã!");
		}
	}

	private void loadDataToText(NhanVien item) {
		txtMaNV.setText(item.getMaNV());
		txtTen.setText(item.getTenNV());
		txtTuoi.setText(item.getTuoi() + "");
		txtLuong.setText(item.getTienLuong() + "");
		if(item.getPhai().trim().equals("Nam"))
			rdoNam.setSelected(true);
		else
			rdoNu.setSelected(false);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row != -1) {
			NhanVien ee = listnv.getDsNhanVien().get(row);
			loadDataToText(ee);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
