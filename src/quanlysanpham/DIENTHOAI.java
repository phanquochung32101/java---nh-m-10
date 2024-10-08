package quanlysanpham;

public class DIENTHOAI extends SANPHAM {

	public DIENTHOAI(String msp, String tensp, float soluong, float dongia) {
		super(msp, tensp, soluong, dongia);
		// TODO Auto-generated constructor stub
	}

	@Override
	float tinhtien() {
		// TODO Auto-generated method stub
		float tt= (float) (getDongia() * getSoluong()*(1-0.2));
		return tt;
	}

}
