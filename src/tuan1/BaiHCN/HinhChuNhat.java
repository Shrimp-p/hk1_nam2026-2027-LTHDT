package tuan1.BaiHCN;

public class HinhChuNhat {
	// Khai bao thuoc tinh private (gioi han truy cap)
	private double chieuDai;
	private double chieuRong;
	
	

	public double getChieuDai() {
		return chieuDai;
	}



	public void setChieuDai(double cD) throws Exception {
		if(cD > 0) {
			this.chieuDai = cD;
		}
		else {
			throw new Exception("Loi");
		}
	}



	public double getChieuRong() {
		return chieuRong;
	}



	public void setChieuRong(double chieuRong) {
		this.chieuRong = chieuRong;
	}
// tao ham tao cung duoc khoi tao bang tu khoa new

	/**
	 Ham tao co so mac dinh java da co 
	 */
	public HinhChuNhat() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Ham co thuoc tinh dung khoi tao doi tuong
 * @param chieuDai
 * @param chieuRong
 */
	public HinhChuNhat(double chieuDai, double chieuRong) {
		this.chieuDai = chieuDai;
		this.chieuRong = chieuRong;
}
// Viet phuong trinh tim dien tich va chu vi
	public double getDT() {
		return this.chieuDai * this.chieuRong;
	}
	
	public double getCV() {
		return (this.chieuDai + this.chieuRong) * 2;
	}

	public static void main(String[] args) {
		// Test chuong trinh theo tinh dien tich va chu vi
		// Dung ham tao
		HinhChuNhat h1 = new HinhChuNhat (7, 5);
		//Xem gia tri canh
		System.out.println(h1.getChieuDai());
		System.out.println(h1.getChieuRong());
		// Tinh dien tich va chu vi theo phuong thuc
		System.out.println("Chu vi hinh chu nhat");
		System.out.println(h1.getCV());
		System.out.println ("Dien tich hinh chu nhat");
		System.out.println(h1.getDT());
	}



	
}
