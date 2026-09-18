package tuan05;

import java.util.Scanner;

// ==================== LỚP CD ====================
class CD {
    // --------- Thuộc tính (private) ---------
    private int maCD;
    private String tuaCD;
    private int soBaiHat;
    private double giaThanh;

    // --------- Hằng số mặc định ---------
    public static final int MA_CD_MAC_DINH = 999999;
    public static final String TUA_CD_MAC_DINH = "chua xac dinh";

    // --------- Constructor mặc định ---------
    public CD() {
        this.maCD = MA_CD_MAC_DINH;
        this.tuaCD = TUA_CD_MAC_DINH;
        this.soBaiHat = 1;
        this.giaThanh = 1.0;
    }

    // --------- Constructor có tham số ---------
    public CD(int maCD, String tuaCD, int soBaiHat, double giaThanh) {
        setMaCD(maCD);
        setTuaCD(tuaCD);
        setSoBaiHat(soBaiHat);
        setGiaThanh(giaThanh);
    }

    // --------- Getter ---------
    public int getMaCD() { return maCD; }
    public String getTuaCD() { return tuaCD; }
    public int getSoBaiHat() { return soBaiHat; }
    public double getGiaThanh() { return giaThanh; }

    // --------- Setter có kiểm tra ràng buộc ---------
    public void setMaCD(int maCD) {
        if (maCD <= 0)
            throw new IllegalArgumentException("Lỗi: Mã CD phải > 0!");
        this.maCD = maCD;
    }

    public void setTuaCD(String tuaCD) {
        if (tuaCD == null || tuaCD.trim().isEmpty())
            throw new IllegalArgumentException("Lỗi: Tựa CD không được rỗng!");
        this.tuaCD = tuaCD;
    }

    public void setSoBaiHat(int soBaiHat) {
        if (soBaiHat <= 0)
            throw new IllegalArgumentException("Lỗi: Số bài hát phải > 0!");
        this.soBaiHat = soBaiHat;
    }

    public void setGiaThanh(double giaThanh) {
        if (giaThanh <= 0)
            throw new IllegalArgumentException("Lỗi: Giá thành phải > 0!");
        this.giaThanh = giaThanh;
    }

    // --------- toString định dạng bảng ---------
    @Override
    public String toString() {
        return String.format(
            "| %-10d | %-25s | %-12d | %-15.2f |",
            maCD, tuaCD, soBaiHat, giaThanh);
    }
}

// ==================== LỚP CDLIST ====================
class CDList {
    // --------- Thuộc tính ---------
    private CD[] danhSachCD;
    private int soLuong;
    private int khaNang;

    private static final int KICH_THUOC_BAN_DAU = 5;

    // --------- Constructor ---------
    public CDList() {
        khaNang = KICH_THUOC_BAN_DAU;
        danhSachCD = new CD[khaNang];
        soLuong = 0;
    }

    // --------- Getter cơ bản ---------
    public int laySoLuong() { return soLuong; }
    public CD layCDTaiViTri(int i) { return danhSachCD[i]; }

    // --------- Tự động mở rộng mảng ---------
    private void moRongMang() {
        int kichThuocMoi = khaNang * 2;
        CD[] mangMoi = new CD[kichThuocMoi];
        for (int i = 0; i < soLuong; i++)
            mangMoi[i] = danhSachCD[i];
        danhSachCD = mangMoi;
        khaNang = kichThuocMoi;
        System.out.println("📈 Đã mở rộng mảng lên " + khaNang + " phần tử.");
    }

    // --------- Tìm vị trí theo mã ---------
    public int timViTriTheoMa(int maCD) {
        for (int i = 0; i < soLuong; i++)
            if (danhSachCD[i].getMaCD() == maCD) return i;
        return -1;
    }

    // ========== THÊM ==========
    public boolean themCD(CD cd) {
        if (timViTriTheoMa(cd.getMaCD()) != -1) {
            System.out.println("❌ Lỗi: Mã CD " + cd.getMaCD() + " đã tồn tại!");
            return false;
        }
        if (soLuong == khaNang) moRongMang();
        danhSachCD[soLuong] = cd;
        soLuong++;
        return true;
    }

    // ========== XÓA ==========
    public boolean xoaCD(int maCD) {
        int viTri = timViTriTheoMa(maCD);
        if (viTri == -1) {
            System.out.println("❌ Không tìm thấy CD mã " + maCD);
            return false;
        }
        for (int i = viTri; i < soLuong - 1; i++)
            danhSachCD[i] = danhSachCD[i + 1];
        danhSachCD[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    // ========== SỬA / CẬP NHẬT ==========
    public boolean capNhatCD(int maCD, String tuaMoi, int soBaiMoi, double giaMoi) {
        int viTri = timViTriTheoMa(maCD);
        if (viTri == -1) {
            System.out.println("❌ Không tìm thấy CD mã " + maCD);
            return false;
        }
        CD cd = danhSachCD[viTri];
        cd.setTuaCD(tuaMoi);
        cd.setSoBaiHat(soBaiMoi);
        cd.setGiaThanh(giaMoi);
        return true;
    }

    // ========== TÌM THEO MÃ ==========
    public CD timTheoMa(int maCD) {
        int viTri = timViTriTheoMa(maCD);
        return (viTri == -1) ? null : danhSachCD[viTri];
    }

    // ========== TÌM THEO TỰA CD ==========
    public CDList timTheoTienTo(String tuKhoa) {
        CDList ketQua = new CDList();
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().startsWith(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }

    public CDList timTheoHauTo(String tuKhoa) {
        CDList ketQua = new CDList();
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().endsWith(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }

    public CDList timGanGiong(String tuKhoa) {
        CDList ketQua = new CDList();
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().contains(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }

    // ========== THỐNG KÊ ==========
    public int tinhSoLuongCD() { return soLuong; }

    public double tinhTongGiaThanh() {
        double tong = 0;
        for (int i = 0; i < soLuong; i++)
            tong += danhSachCD[i].getGiaThanh();
        return tong;
    }

    public double tinhGiaTrungBinh() {
        return (soLuong == 0) ? 0 : tinhTongGiaThanh() / soLuong;
    }

    public CD timCDDatNhat() {
        if (soLuong == 0) return null;
        CD max = danhSachCD[0];
        for (int i = 1; i < soLuong; i++)
            if (danhSachCD[i].getGiaThanh() > max.getGiaThanh())
                max = danhSachCD[i];
        return max;
    }

    public CD timCDReNhat() {
        if (soLuong == 0) return null;
        CD min = danhSachCD[0];
        for (int i = 1; i < soLuong; i++)
            if (danhSachCD[i].getGiaThanh() < min.getGiaThanh())
                min = danhSachCD[i];
        return min;
    }

    // ========== HIỂN THỊ ==========
    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("📭 Danh sách CD trống!");
            return;
        }
        System.out.println("\n+------------+---------------------------+--------------+------------------+");
        System.out.println("|   Mã CD    |          Tựa CD           |  Số bài hát  |    Giá thành     |");
        System.out.println("+------------+---------------------------+--------------+------------------+");
        for (int i = 0; i < soLuong; i++) {
            System.out.println(danhSachCD[i]);
        }
        System.out.println("+------------+---------------------------+--------------+------------------+");
    }

    // ========== SẮP XẾP ==========
    public void sapXepGiamDanTheoGia() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                if (danhSachCD[i].getGiaThanh() < danhSachCD[j].getGiaThanh()) {
                    CD temp = danhSachCD[i];
                    danhSachCD[i] = danhSachCD[j];
                    danhSachCD[j] = temp;
                }
            }
        }
        System.out.println("✅ Đã sắp xếp giảm dần theo giá thành.");
    }

    public void sapXepTangDanTheoTua() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                if (danhSachCD[i].getTuaCD().compareToIgnoreCase(danhSachCD[j].getTuaCD()) > 0) {
                    CD temp = danhSachCD[i];
                    danhSachCD[i] = danhSachCD[j];
                    danhSachCD[j] = temp;
                }
            }
        }
        System.out.println("✅ Đã sắp xếp tăng dần theo tựa CD.");
    }
}

// ==================== LỚP MAIN (CHÍNH) ====================
class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CDList ql = new CDList();
        int luaChon = -1;

        do {
            System.out.println("\n╔══════════════ MENU QUẢN LÝ CD ══════════════╗");
            System.out.println("║ 1. Thêm CD (tự mở rộng mảng khi đầy)        ║");
            System.out.println("║ 2. Xóa CD theo mã                           ║");
            System.out.println("║ 3. Sửa / Cập nhật CD theo mã                ║");
            System.out.println("║ 4. Tìm kiếm theo mã                         ║");
            System.out.println("║ 5. Tìm theo tựa - TIỀN TỐ (bên trái)        ║");
            System.out.println("║ 6. Tìm theo tựa - HẬU TỐ (bên phải)         ║");
            System.out.println("║ 7. Tìm theo tựa - GẦN GIỐNG (chứa)          ║");
            System.out.println("║ 8. Hiển thị danh sách                       ║");
            System.out.println("║ 9. Thống kê                                 ║");
            System.out.println("║ 10. Sắp xếp giảm dần theo giá thành         ║");
            System.out.println("║ 11. Sắp xếp tăng dần theo tựa CD            ║");
            System.out.println("║ 0. Thoát                                    ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("👉 Chọn chức năng: ");

            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Vui lòng nhập số!");
                continue;
            }

            switch (luaChon) {
                case 1: themCD(ql); break;
                case 2: xoaCD(ql); break;
                case 3: capNhatCD(ql); break;
                case 4: timTheoMa(ql); break;
                case 5: timTheoTienTo(ql); break;
                case 6: timTheoHauTo(ql); break;
                case 7: timGanGiong(ql); break;
                case 8: ql.hienThiDanhSach(); break;
                case 9: thongKe(ql); break;
                case 10: ql.sapXepGiamDanTheoGia(); ql.hienThiDanhSach(); break;
                case 11: ql.sapXepTangDanTheoTua(); ql.hienThiDanhSach(); break;
                case 0: System.out.println("👋 Tạm biệt!"); break;
                default: System.out.println("⚠️ Lựa chọn không hợp lệ!");
            }
        } while (luaChon != 0);

        sc.close();
    }

    // ========== Các hàm hỗ trợ menu ==========

    static void themCD(CDList ql) {
        try {
            System.out.print("Nhập mã CD (>0): ");
            int ma = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập tựa CD: ");
            String tua = sc.nextLine();
            System.out.print("Nhập số bài hát (>0): ");
            int sbh = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập giá thành (>0): ");
            double gia = Double.parseDouble(sc.nextLine());
            if (ql.themCD(new CD(ma, tua, sbh, gia)))
                System.out.println("✅ Thêm CD thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void xoaCD(CDList ql) {
        try {
            System.out.print("Nhập mã CD cần xóa: ");
            int ma = Integer.parseInt(sc.nextLine());
            if (ql.xoaCD(ma)) System.out.println("🗑 Đã xóa CD mã " + ma);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Mã phải là số nguyên!");
        }
    }

    static void capNhatCD(CDList ql) {
        try {
            System.out.print("Nhập mã CD cần sửa: ");
            int ma = Integer.parseInt(sc.nextLine());
            System.out.print("Tựa CD mới: ");
            String tua = sc.nextLine();
            System.out.print("Số bài hát mới: ");
            int sbh = Integer.parseInt(sc.nextLine());
            System.out.print("Giá thành mới: ");
            double gia = Double.parseDouble(sc.nextLine());
            if (ql.capNhatCD(ma, tua, sbh, gia))
                System.out.println("✅ Đã cập nhật CD mã " + ma);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void timTheoMa(CDList ql) {
        try {
            System.out.print("Nhập mã CD cần tìm: ");
            int ma = Integer.parseInt(sc.nextLine());
            CD kq = ql.timTheoMa(ma);
            if (kq != null) {
                System.out.println("🔍 Tìm thấy:");
                System.out.println(kq);
            } else {
                System.out.println("❌ Không có CD mã " + ma);
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Mã phải là số!");
        }
    }

    static void timTheoTienTo(CDList ql) {
        System.out.print("Nhập tiền tố tựa CD (so khớp BÊN TRÁI): ");
        String tk = sc.nextLine();
        CDList kq = ql.timTheoTienTo(tk);
        System.out.println("🔍 Tìm thấy " + kq.laySoLuong() + " kết quả (bắt đầu bằng '" + tk + "'):");
        kq.hienThiDanhSach();
    }

    static void timTheoHauTo(CDList ql) {
        System.out.print("Nhập hậu tố tựa CD (so khớp BÊN PHẢI): ");
        String tk = sc.nextLine();
        CDList kq = ql.timTheoHauTo(tk);
        System.out.println("🔍 Tìm thấy " + kq.laySoLuong() + " kết quả (kết thúc bằng '" + tk + "'):");
        kq.hienThiDanhSach();
    }

    static void timGanGiong(CDList ql) {
        System.out.print("Nhập từ khóa (tìm GẦN GIỐNG, chứa ở bất kỳ vị trí): ");
        String tk = sc.nextLine();
        CDList kq = ql.timGanGiong(tk);
        System.out.println("🔍 Tìm thấy " + kq.laySoLuong() + " kết quả (chứa '" + tk + "'):");
        kq.hienThiDanhSach();
    }

    static void thongKe(CDList ql) {
        System.out.println("\n===== THỐNG KÊ =====");
        System.out.println("📊 Số lượng CD: " + ql.tinhSoLuongCD());
        System.out.printf("💰 Tổng giá thành: %,.2f VNĐ\n", ql.tinhTongGiaThanh());
        System.out.printf("📈 Giá trung bình: %,.2f VNĐ\n", ql.tinhGiaTrungBinh());
        CD max = ql.timCDDatNhat();
        CD min = ql.timCDReNhat();
        if (max != null) {
            System.out.println("💎 CD đắt nhất:");
            System.out.println("   " + max);
            System.out.println("💵 CD rẻ nhất:");
            System.out.println("   " + min);
        }
    }
}