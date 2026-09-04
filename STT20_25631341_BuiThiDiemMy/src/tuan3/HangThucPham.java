/**
 * 
 */
package tuan3;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 */
public class HangThucPham {

	/**
	 * Khai bao thuoc tinh
	 */
	private int id;
	private final String maHang;
	private String tenHang;
	private LocalDate ngaySanXuat;
	private LocalDate ngayHetHan;
	private float gia;
	
	private static int demSoLuong = 0;
	
	// Tao constructer mac dinh

	/**
	 * @param id
	 * @param maHang
	 * @param tenHang
	 * @param ngaySanXuat
	 * @param ngayHetHan
	 * @param gia
	 */
	public HangThucPham() {
		this.id = ++demSoLuong;
		this.maHang = "MAC_DINH";
		this.tenHang = "Hang mac dinh";
		this.ngaySanXuat = LocalDate.now();
		this.ngayHetHan = LocalDate.now().plusDays(7);
		this.gia = 1.0f;
	}
	// Tao constructor co tham so
	/**
	 * @param id
	 * @param maHang
	 * @param tenHang
	 * @param ngaySanXuat
	 * @param ngayHetHan
	 * @param gia
	 */
	public HangThucPham(String maHang, String tenHang, LocalDate ngaySanXuat, LocalDate ngayHetHan, float gia) {
		this.id = ++demSoLuong;
		if(maHang == null || maHang.trim().isEmpty())
			throw new IllegalArgumentException("Mã hàng không được để trống!");
		if(tenHang == null || tenHang.trim().isEmpty())
			throw new IllegalArgumentException("Ten hang không được để trống!");
		if (gia <= 0)
			throw new IllegalArgumentException("Gia phai lon hon 0!");
		if (!kiemTraNgaySanXuat(ngaySanXuat))
			throw new IllegalArgumentException("Ngay san xuat khong duoc la ngay trong tuong lai!");
		if (!kiemTraNgayHetHan(ngaySanXuat, ngayHetHan))
			throw new IllegalArgumentException("Ngay het han phai sau ngay san xuat!");
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.ngaySanXuat = ngaySanXuat;
		this.ngayHetHan = ngayHetHan;
		this.gia = gia;
	}
	//Phuong thuc kiem tra ngay
	private boolean kiemTraNgaySanXuat(LocalDate ngaySX) {
		return ngaySX != null && !ngaySX.isAfter(LocalDate.now());
	}
	private boolean kiemTraNgayHetHan(LocalDate ngaySX, LocalDate ngayHH) {
		return ngaySX != null && ngayHH != null && ngayHH.isAfter(ngaySX);
	}
	//Tao cac phuong thuc get/set
	public int getId() { return id; }
    public String getMaHang() { return maHang; }
    public String getTenHang() { return tenHang; }
    public LocalDate getNgaySanXuat() { return ngaySanXuat; }
    public LocalDate getNgayHetHan() { return ngayHetHan; }
    public float getGia() { return gia; }
    
    public void setTenHang(String tenHang) {
        if (tenHang == null || tenHang.trim().isEmpty())
            throw new IllegalArgumentException("Tên hàng không được để trống!");
        this.tenHang = tenHang;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        if (!kiemTraNgaySanXuat(ngaySanXuat))
            throw new IllegalArgumentException("Ngày sản xuất không được là tương lai!");
        if (this.ngayHetHan != null && !kiemTraNgayHetHan(ngaySanXuat, this.ngayHetHan))
            throw new IllegalArgumentException("Ngày sản xuất phải trước ngày hết hạn!");
        this.ngaySanXuat = ngaySanXuat;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        if (this.ngaySanXuat == null)
            throw new IllegalArgumentException("Chưa có ngày sản xuất để so sánh!");
        if (!kiemTraNgayHetHan(this.ngaySanXuat, ngayHetHan))
            throw new IllegalArgumentException("Ngày hết hạn phải sau ngày sản xuất!");
        this.ngayHetHan = ngayHetHan;
    }

    public void setGia(float gia) {
        if (gia <= 0)
            throw new IllegalArgumentException("Giá phải lớn hơn 0!");
        this.gia = gia;
    }
    //Kiem tra het han
    public boolean kiemTraHetHan() {
    	return LocalDate.now().isAfter(this.ngayHetHan);
    }
    
    public String trangThaiChiTiet() {
    	LocalDate homNay = LocalDate.now();
    	if(homNay.isBefore(ngaySanXuat))
    		return "Chua san xuat";
    	if(homNay.isAfter(ngayHetHan))
    		return "Het Han";
    	if(homNay.isEqual(ngayHetHan))
    		return "Het han hom nay";
    	long con = ngayHetHan.toEpochDay() - homNay.toEpochDay();
    	return "Con" + con + "ngay";
    }
    
    @Override
	public String toString() {
		return "HangThucPham{" +
                "id=" + id +
                ", maHang='" + maHang + '\'' +
                ", tenHang='" + tenHang + '\'' +
                ", ngaySX=" + ngaySanXuat +
                ", ngayHH=" + ngayHetHan +
                ", gia=" + gia +
                '}';
				
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HangThucPham htp1 = new HangThucPham();
		Scanner sc = new Scanner(System.in);
		
		HangThucPham htp2 = new HangThucPham("HP123456", "May Tinh", LocalDate.of(2015, 12, 12), LocalDate.now(), 12000000);
		System.out.println(htp1.toString());
		System.out.println(htp2.toString());
	}
	

}
