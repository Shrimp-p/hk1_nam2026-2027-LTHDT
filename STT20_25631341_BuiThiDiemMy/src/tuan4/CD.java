package tuan4;

public class CD {
    private String maCD;
    private String tuaCD;
    private String caSy;
    private int soBaiHat;
    private float gia;

    public String getMaCD() { return maCD; }
    public void setMaCD(String maCD) { this.maCD = maCD; }

    public String getTuaCD() { return tuaCD; }
    public void setTuaCD(String tuaCD) { this.tuaCD = tuaCD; }

    public String getCaSy() { return caSy; }
    public void setCaSy(String caSy) { this.caSy = caSy; }

    public int getSoBaiHat() { return soBaiHat; }
    public void setSoBaiHat(int soBai) throws Exception {
        if (soBai > 0) {
            this.soBaiHat = soBai;
        } else {
            throw new Exception("So bai hat phai lon hon 0");
        }
    }

    public float getGia() { return gia; }
    public void setGia(float gia) { this.gia = gia; }

    public CD() { }

    public CD(String tuaCD, String caSy, int soBai, float gia, String maCD) throws Exception {
        this.tuaCD = tuaCD;
        this.caSy  = caSy;
        this.setSoBaiHat(soBai);
        this.gia   = gia;
        this.maCD  = maCD;
    }

    public static void tieuDe() {
        System.out.println("In danh sach CD");
        for (int i = 0; i < 100; i++) System.out.print("-");
        System.out.println();
        
        String s = String.format("|%10s|%10s|%10s|%10s|%10s|%10s|",
            "Ma CD", "Tua CD", "Ca Sy", "So Bai", "Don Gia", "Thanh Tien");
        System.out.println(s);
        
        for (int i = 0; i < 100; i++) System.out.print("-");
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("|%10s|%10s|%10s|%10d|%10.2f|%10.2f|",
            maCD, tuaCD, caSy, soBaiHat, gia, gia * soBaiHat);
    }
}
