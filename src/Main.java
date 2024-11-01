import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);
    private static Pegawai[] pegawaiList = {
            new Pegawai(1,"082222222", "Bandung", "Pegawai1", 'L', 'M'),
            new Pegawai(2,"083333333", "Yogyakarta", "Pegawai2", 'L', 'M'),
            new Pegawai(3,"084444444", "Semarang", "Pegawai3", 'L', 'M')
    };

    private static Komponen[] komponenList = {
            new Komponen("Oli Motor", 50000, '2'),
            new Komponen("Oli Mobil", 100000, '4'),
            new Komponen("Filter Motor", 30000, '2'),
            new Komponen("Filter Mobil", 60000, '4')
    };
    private static Kendaraan[] kendaraanList = new Kendaraan[100];
    private static int kendaraanCount = 0;
    public static void main(String[] args) {
        boolean next = true;

        while (next) {
            showMenu();
            int pil = in.nextInt();
            in.nextLine();

            switch (pil) {
                case 1:
                    handleService();
                    break;
                case 2:
                    showAntrian();
                    break;
                case 3:
                    completeService();
                    break;
                case 4:
                    showRiwayat();
                    break;
                case 5:
                    printNotaPembayaran();
                    break;
                case 6:
                    tambahKomponen();
                    break;
                case 7:
                    System.out.println("Keluar");
                    next = false;
                    break;
                default:
                    System.out.println("Inputan tidak valid");
                    break;
            }
        }
        in.close();
    }

    private static void showMenu() {
        System.out.println("Service Kendaraan Al Makmur");
        System.out.println("Menu : ");
        System.out.println("1. Service");
        System.out.println("2. Lihat antrian");
        System.out.println("3. Selesaikan service kendaraan");
        System.out.println("4. Lihat riwayat");
        System.out.println("5. Cetak nota pembayaran");
        System.out.println("6. Tambah komponen");
        System.out.println("7. Keluar program");
    }

    private static void handleService() {
//        int pilJasa = chooseServiceSpeed();
        Kendaraan kendaraan = inputKendaraanData();

        if (kendaraan != null) {
//            Pelanggan pelanggan = inputPelangganData();
            assignWorker();
            kendaraan.setInProgress(true);
            kendaraanList[kendaraanCount++] = kendaraan;
        }
    }

    private static int chooseServiceSpeed() {
        System.out.println("Pilih kecepatan service : ");
        System.out.println("1.Express");
        System.out.println("2.Regular");
        return in.nextInt();
    }

    private static Kendaraan inputKendaraanData() {
        System.out.println("Masukkan data kendaraan");
        System.out.println("=======================");
        System.out.print("Plat nomor : ");
        String platNo = in.nextLine();
        System.out.print("Warna  : ");
        String warna = in.nextLine();
        System.out.print("Merk : ");
        String merk = in.nextLine();
        System.out.print("Tipe : ");
        String tipe = in.nextLine();
        System.out.print("Jenis kendaraan (2 : motor, 4 : mobil) : ");
        char jenisKendaraan = in.next().charAt(0);
        in.nextLine();

        Pelanggan pelanggan = inputPelangganData();
        Kendaraan kendaraan;

        if (jenisKendaraan == '2') {
            kendaraan = new Motor(platNo, warna, merk, tipe, pelanggan);
        } else if (jenisKendaraan == '4') {
            kendaraan = new Mobil(platNo, warna, merk, tipe, pelanggan);
        } else {
            System.out.println("Jenis kendaraan tidak valid.");
            return null;
        }

        System.out.println("Pilih kecepatan service:");
        System.out.println("1. Express");
        System.out.println("2. Regular");
        int speedChoice = in.nextInt();
        in.nextLine();

        if (speedChoice == 1) {
            kendaraan.serviceExpress(true);
        } else if (speedChoice == 2) {
            kendaraan.serviceExpress();
        } else {
            System.out.println("Pilihan kecepatan service tidak valid.");
            return null;
        }

        return kendaraan;
    }



    private static Pelanggan inputPelangganData() {
        System.out.println("Masukkan data pemilik kendaraan : ");
        System.out.println("==================================");
        System.out.print("Nama Pemilik: ");
        String namaPemilik = in.nextLine();
        System.out.print("Nomor Telepon: ");
        String noHp = in.nextLine();
        System.out.print("Alamat: ");
        String alamat = in.nextLine();
        System.out.print("Jenis Kelamin (P/L): ");
        char jenisKelamin = in.next().charAt(0);
        in.nextLine();

        return new Pelanggan(namaPemilik, noHp, alamat, jenisKelamin);
    }

    private static void assignWorker() {
        System.out.println("Pegawai yang tersedia untuk bekerja : ");
        for (Pegawai pegawai : pegawaiList) {
            if (pegawai.kerja == 0) {
                System.out.println( pegawai.id +"."+ pegawai.nama);
            }
        }
        System.out.print("Pilih pegawai : ");
        int pilPegawai = in.nextInt();
        in.nextLine();

        if (pilPegawai >= 1 && pilPegawai <= pegawaiList.length && pegawaiList[pilPegawai - 1].kerja == 0) {
            pegawaiList[pilPegawai - 1].kerja = 1;
        } else {
            System.out.println("Pegawai tidak tersedia. Silakan pilih pegawai lain.");
        }
    }

    private static void tambahKomponen() {
        System.out.println("Pilih kendaraan yang sedang diservis:");
        for (int i = 0; i < kendaraanCount; i++) {
            if (kendaraanList[i].isInProgress()) {
                System.out.println((i + 1) + ". Plat No: " + kendaraanList[i].getPlatNo());
            }
        }
        System.out.print("Masukkan nomor kendaraan: ");
        int index = in.nextInt() - 1;
        in.nextLine();

        if (index >= 0 && index < kendaraanCount && kendaraanList[index].isInProgress()) {
            pilihKomponen(kendaraanList[index]);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void pilihKomponen(Kendaraan kendaraan) {
        System.out.println("Pilih komponen yang digunakan (0 untuk selesai):");
        for (int i = 0; i < komponenList.length; i++) {
            if (komponenList[i].getVehicleType() == kendaraan.getJenisKendaraan()) {
                System.out.println((i + 1) + ". " + komponenList[i].getName() + " - Rp " + komponenList[i].getPrice());
            }
        }

        while (true) {
            System.out.print("Pilih nomor komponen: ");
            int choice = in.nextInt() - 1;
            if (choice == -1) break;

            if (choice >= 0 && choice < komponenList.length && komponenList[choice].getVehicleType() == kendaraan.getJenisKendaraan()) {
                kendaraan.addComponent(komponenList[choice]);
                System.out.println("Komponen " + komponenList[choice].getName() + " ditambahkan.");
            } else {
                System.out.println("Pilihan tidak valid atau komponen tidak sesuai jenis kendaraan.");
            }
        }
        in.nextLine();
    }

    private static void showAntrian() {
        System.out.println("Lihat antrian : ");
        for (int i = 0; i < kendaraanCount; i++) {
            if (kendaraanList[i].isInProgress()) {
                System.out.println("Plat No : " + kendaraanList[i].getPlatNo());
                System.out.println("Yang mengerjakan : " + pegawaiList[i].nama);
            }
        }
    }

    private static void completeService() {
        System.out.println("Selesaikan service kendaraan");
        System.out.println("Kendaraan yang sedang diservice:");
        for (int i = 0; i < kendaraanCount; i++) {
            if (kendaraanList[i].isInProgress()) {
                System.out.println((i + 1) + ". Plat No: " + kendaraanList[i].getPlatNo());
            }
        }
        System.out.print("Pilih nomor kendaraan untuk menyelesaikan service: ");
        int serviceIndex = in.nextInt() - 1;
        in.nextLine();

        if (serviceIndex >= 0 && serviceIndex < kendaraanCount && kendaraanList[serviceIndex].isInProgress()) {
            int biaya = kendaraanList[serviceIndex].calculateCost();
            kendaraanList[serviceIndex].setInProgress(false);
            System.out.println("Service untuk kendaraan " + kendaraanList[serviceIndex].getPlatNo() + " telah diselesaikan.");
            System.out.println("Biaya service: " + biaya);
            resetWorkerStatus();
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void resetWorkerStatus() {
        for (Pegawai pegawai : pegawaiList) {
            if (pegawai.kerja == 1) {
                pegawai.kerja = 0;
            }
        }
    }

    private static void showRiwayat() {
        System.out.println("Lihat Riwayat");
        for (int i = 0; i < kendaraanCount; i++) {
            if (!kendaraanList[i].isInProgress()) {
                kendaraanList[i].printServiceHistory();
                System.out.println("Yang mengerjakan : " + pegawaiList[i].nama);
            }
        }
    }

    private static void printNotaPembayaran() {
        System.out.println("Cetak nota pembayaran");
        System.out.print("Masukkan plat nomor kendaraan: ");
        String platNo = in.nextLine();

        for (int i = 0; i < kendaraanCount; i++) {
            if (kendaraanList[i].getPlatNo().equals(platNo)) {

                if (!kendaraanList[i].isInProgress()) {

                    System.out.println("=== Nota Pembayaran ===");
                    System.out.println("Data Kendaraan:");
                    System.out.println("Plat No: " + kendaraanList[i].getPlatNo());
                    System.out.println("Warna: " + kendaraanList[i].getWarna());
                    System.out.println("Merk: " + kendaraanList[i].getMerk());
                    System.out.println("Tipe: " + kendaraanList[i].getTipe());
                    System.out.println("Jenis Kendaraan: " + (kendaraanList[i].getJenisKendaraan() == '4' ? "Mobil" : "Motor"));

                    Pelanggan pemilik = kendaraanList[i].getPemilik();
                    System.out.println("Data Pemilik:");
                    System.out.println("Nama: " + pemilik.getNama());
                    System.out.println("Nomor Telepon: " + pemilik.getNoHp());
                    System.out.println("Alamat: " + pemilik.getAlamat());
                    System.out.println("Jenis Kelamin: " + (pemilik.getJenisKelamin() == 'P' ? "Perempuan" : "Laki-laki"));

                    System.out.println("Data Pekerja:");
                    for (Pegawai pegawai : pegawaiList) {
                        if (pegawai.kerja == 1) {
                            System.out.println("Nama Pegawai: " + pegawai.nama);
                            System.out.println("Nomor HP Pegawai: " + pegawai.noTelp);
                        }
                    }

                    int biaya = kendaraanList[i].calculateCost();
                    System.out.println("Biaya Service: " + biaya);
                    return;
                } else {
                    System.out.println("Service untuk kendaraan ini belum selesai.");
                    return;
                }
            }
        }
        System.out.println("Plat nomor tidak ditemukan.");
    }
}
