class Pegawai {
    String noTelp;
    int id;
    String lokasi;
    String nama;
    char jenisKelamin;
    int kerja;

    public Pegawai(int id, String noTelp, String lokasi, String nama, char jenisKelamin, char kerja) {
        this.noTelp = noTelp;
        this.id = id;
        this.lokasi = lokasi;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.kerja = kerja == 'M' ? 0 : 1;
    }
}