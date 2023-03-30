package GK;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable{
	private String maNV;
	private String tenNV;
	private int tuoi;
	private double tienLuong;
	private String phai;
	private String phongBan;

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public Object[] getObjectNv() {
		Object[] obj = {getMaNV(), getTenNV(), getTuoi(), getPhai(), getTienLuong(), getPhongBan()};
		return obj;
	}
	
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(String phongBan) {
		this.phongBan = phongBan;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNV, String tenNV, int tuoi, double tienLuong, String phai, String phongBan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.tuoi = tuoi;
		this.tienLuong = tienLuong;
		this.phai = phai;
		this.phongBan = phongBan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
