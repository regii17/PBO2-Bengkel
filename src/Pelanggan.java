class Pelanggan {
    private String nama;
    private String noHp;
    private String alamat;
    private char jenisKelamin;

    public Pelanggan(String nama, String noHp, String alamat, char jenisKelamin) {
        this.nama = nama;
        this.noHp = noHp;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public char getJenisKelamin() {
        return jenisKelamin;
    }
}