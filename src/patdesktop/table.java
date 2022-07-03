package patdesktop;

public class table {


    private String NOMOR_TRANSAKSI;
    private String BERAS;
    private String MINYAK;
    private String GULA;
    public table(String NOMOR_TRANSAKSI, String BERAS, String MINYAK, String GULA, String GARAM, String TELUR, String TOTAL_PEMBELIAN, String ALAMAT, String ALAMAT_LANJUT, String DATE) {
        this.NOMOR_TRANSAKSI = NOMOR_TRANSAKSI;
        this.BERAS = BERAS;
        this.MINYAK = MINYAK;
        this.GULA = GULA;
        this.GARAM = GARAM;
        this.TELUR = TELUR;
        this.TOTAL_PEMBELIAN = TOTAL_PEMBELIAN;
        this.ALAMAT = ALAMAT;
        this.ALAMAT_LANJUT = ALAMAT_LANJUT;
        this.DATE = DATE;
    }
    public String getBERAS() {
        return BERAS;
    }

    public void setBERAS(String BERAS) {
        this.BERAS = BERAS;
    }

    public String getMINYAK() {
        return MINYAK;
    }

    public void setMINYAK(String MINYAK) {
        this.MINYAK = MINYAK;
    }

    public String getGULA() {
        return GULA;
    }

    public void setGULA(String GULA) {
        this.GULA = GULA;
    }

    public String getGARAM() {
        return GARAM;
    }

    public void setGARAM(String GARAM) {
        this.GARAM = GARAM;
    }

    public String getTELUR() {
        return TELUR;
    }

    public void setTELUR(String TELUR) {
        this.TELUR = TELUR;
    }

    public String getTOTAL_PEMBELIAN() {
        return TOTAL_PEMBELIAN;
    }

    public void setTOTAL_PEMBELIAN(String TOTAL_PEMBELIAN) {
        this.TOTAL_PEMBELIAN = TOTAL_PEMBELIAN;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getALAMATLANJUT() {
        return ALAMAT_LANJUT;
    }

    public void setALAMATLANJUT(String ALAMATLANJUT) {
        this.ALAMAT_LANJUT = ALAMATLANJUT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    private String GARAM;
    private String TELUR;
    private String TOTAL_PEMBELIAN;
    private String ALAMAT;



    private String ALAMAT_LANJUT;
    private String DATE;



    public table() {
    }

    public String getId_transaksi() {
        return NOMOR_TRANSAKSI;
    }

    public void setId_transaksi(int NOMOR_TRANSAKSI) {
        this.NOMOR_TRANSAKSI = String.valueOf(NOMOR_TRANSAKSI);
    }
}