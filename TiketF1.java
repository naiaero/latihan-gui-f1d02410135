public class TiketF1 {
    private String kategori;
    private double hargaSatuan;

    public TiketF1(String kategori, double hargaSatuan) {
        this.kategori = kategori;
        this.hargaSatuan = hargaSatuan;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public double hitungTotal(int jumlah) {
        return this.hargaSatuan * jumlah;
    }
}