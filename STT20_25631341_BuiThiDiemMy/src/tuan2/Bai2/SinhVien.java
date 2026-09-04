/**
 * 
 */
package tuan2.Bai2;

import java.util.Scanner;

/**
 * 
 */
public class SinhVien {

	/**
	 * Khai bao thuoc tinh
	 */
	private int maSV;
	private String hoTen;
	private float diemLT, diemTH;
	/**
	 * Dong goi
	 */
	public int getMaSV() {
		return maSV;
	}

	public void setMaSV(int ma) throws Exception {
		if (ma > 0) {
			this.maSV = ma;
		} else {
			throw new Exception("Loi ma > 0");
		}
		
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public float getDiemLT() {
		return diemLT;
	}

	public void setDiemLT(float diemLT) {
		this.diemLT = diemLT;
	}

	public float getDiemTH() {
		return diemTH;
	}

	public void setDiemTH(float diemTH) {
		this.diemTH = diemTH;
	}
	/**
	 * @param Ham tao dung khoi tao doi tuong
	 */
	/**
	 * @param maSV
	 * @param hoTen
	 * @param diemLT
	 * @param diemTH
	 */
	public SinhVien() {
		this.maSV = 1;
		this.hoTen = "chua co";
		this.diemLT = 0.0f;
		this.diemTH = 0.0f;
	}
	/**
	 * @param maSV
	 * @param hoTen
	 * @param diemLT
	 * @param diemTH
	 * @throws Exception 
	 */
	public SinhVien(int ma, String hoTen, float diemLT, float diemTH) throws Exception {
		if (ma > 0) {
			this.maSV = ma;
		} else {
			throw new Exception("Loi ma > 0");
		}
		this.hoTen = hoTen;
		this.diemLT = diemLT;
		this.diemTH = diemTH;
	}
	/**
	 * Xay dung phuong thuc tinh diem trung binh
	 */
	public double getDiemTB() {
		return (getDiemLT() + getDiemTH())/2;
	}

	/**
	 * Phuong thuc toString in cac dong doi tuong
	 */
	/**
	 * In tieu de
	 */
	public static final void tieuDe() {
		for (int i = 0; i < 60; i++) {
			System.out.printf("-");
		}
		System.out.println();
		String s= "";
		s =s+s.format("|%10s|%-10s|%10s|%10s|%10s|", "Ma sinh vien", "Ho ten", "Diem ly thuyet", "Diem TH", "Diem TB");
		System.out.println(s);
		for (int i = 0; i < 60; i++) {
			System.out.printf("-");
		}
		System.out.println();
	}
	/**
	 * toString mau in cua doi tuong
	 */
	@Override
	public String toString() {
		String s= "";
		s =s+s.format("|%10s|%-10s|%10s|%10s|%10s|", getMaSV(), getHoTen(), getDiemLT(), getDiemTH(), getDiemTB());
		return s;
	}
	
	public static SinhVien nhap() throws Exception {
		Scanner sc = new Scanner(System.in);
		int maSV;
		String hoTen;
		float diemLT, diemTH;
		System.out.println("Nhap ma sinh vien: ");
		maSV = sc.nextInt();
		sc.nextLine(); // xoa bo dem
		System.out.println("Nhap ho ten: ");
		hoTen = sc.nextLine();
		System.out.println("Nhap diem LT: ");
		diemLT = sc.nextFloat();
		System.out.println("Nhap diem TH: ");
		diemTH = sc.nextFloat();
		SinhVien s = new SinhVien(maSV, hoTen, diemLT, diemTH);
		return s;
	}
	public static void xuat(SinhVien s) {
		System.out.println(s.toString());
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		tieuDe();
		SinhVien s1 = new SinhVien(2, "Nguyen Van A", 8, 9);
		System.out.println(s1.toString());
		System.out.println("Nhap tu ban phim");
		tieuDe();
		xuat(nhap());
	}

	
	

	

}
