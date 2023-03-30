package GK;

import java.io.Serializable;
import java.util.ArrayList;


public class DanhSachNV implements Serializable{
	private ArrayList<NhanVien> dsNhanVien;

	public DanhSachNV(ArrayList<NhanVien> dsNhanVien) {
		super();
		dsNhanVien = new ArrayList<NhanVien>();
	}

	public DanhSachNV() {
		super();
		dsNhanVien = new ArrayList<NhanVien>();
	}

	public ArrayList<NhanVien> getDsNhanVien() {
		return dsNhanVien;
	}

	public void setDsNhanVien(ArrayList<NhanVien> dsNhanVien) {
		this.dsNhanVien = dsNhanVien;
	}

	public void replaceID(NhanVien e)
	{
		dsNhanVien.set(dsNhanVien.indexOf(e),e);
	}
	
	public boolean themNhanVien(NhanVien x) {
		if (dsNhanVien.contains(x))
			return false;
		dsNhanVien.add(x);
		return true;
	}

	public boolean xoaNhanVien(NhanVien x) {
		if (!dsNhanVien.contains(x))
			return false;
		dsNhanVien.remove(x);
		return true;
	}

	public NhanVien timNhanVien(String ma) {
		for (NhanVien x : dsNhanVien) {
			if (x.getMaNV().equals(ma))
				return x;
		}
		return null;
	}
	
	public void sua(NhanVien nv) {
		for (NhanVien x : dsNhanVien) {
			if(x.getMaNV().equals(nv.getMaNV())) {
				x.setTenNV(nv.getTenNV());
				x.setTuoi(nv.getTuoi());
				x.setPhai(nv.getPhai());
				x.setTienLuong(nv.getTienLuong());
				x.setPhongBan(nv.getPhongBan());
			}
		}
	}
}
