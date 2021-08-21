package vn.codegym.model;

public class Product {
    private int id;
    private String tenSP;
    private int maSp;
    private String hinhAnh;
    private int gia;
    private int soLuong;
    private String moTa;

    public Product() {
    }

    public Product(String tenSP, int maSp, String hinhAnh, int gia, int soLuong, String moTa) {
        this.tenSP = tenSP;
        this.maSp = maSp;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public Product(int id, String tenSP, int maSp, String hinhAnh, int gia, String moTa) {
        this.id = id;
        this.tenSP = tenSP;
        this.maSp = maSp;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.moTa = moTa;
    }

    public Product(int id, String tenSP, int maSp, String hinhAnh, int gia, int soLuong, String moTa) {
        this.id = id;
        this.tenSP = tenSP;
        this.maSp = maSp;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public Product(Product product) {
        this.id = product.getId();
        this.tenSP = product.getTenSP();
        this.maSp = product.getMaSp();
        this.hinhAnh = product.getHinhAnh();
        this.gia = product.getGia();
        this.soLuong = product.getSoLuong();
        this.moTa = product.getMoTa();
    }

    public Product(Product product, int soLuong) {
        this.id = product.getId();
        this.tenSP = product.getTenSP();
        this.maSp = product.getMaSp();
        this.hinhAnh = product.getHinhAnh();
        this.gia = product.getGia();
        this.soLuong = soLuong;
        this.moTa = product.getMoTa();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
