package quanlysanpham;

public abstract class SANPHAM {
	private String msp;
	private String tensp;
	private float soluong;
	private float dongia;
	abstract float tinhtien();
	public SANPHAM(String msp, String tensp, float soluong, float dongia) {
		setDongia(dongia);
		setMsp(msp);
		setSoluong(soluong);
		setTensp(tensp);
	}
	
	
	String getMsp() {
		return msp;
	}
	void setMsp(String msp) {
		this.msp = msp;
	}
	String getTensp() {
		return tensp;
	}
	void setTensp(String tensp) {
		this.tensp = tensp;
	}
	float getSoluong() {
		return soluong;
	}
	void setSoluong(float soluong) {
		this.soluong = soluong;
	}
	float getDongia() {
		return dongia;
	}
	void setDongia(float dongia) {
		this.dongia = dongia;
	}
}
