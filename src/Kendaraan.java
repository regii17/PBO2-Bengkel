import java.util.ArrayList;
import java.util.List;

public abstract class Kendaraan {

    // encapsulation
    private String platNo;
    private String warna;
    private String merk;
    private String tipe;
    private char jenisKendaraan;
    private Pelanggan pemilik;
    private boolean inProgress = false;
    private boolean expressService;
    private List<Komponen> components = new ArrayList<>();


    public Kendaraan(String platNo, String warna, String merk, String tipe, char jenisKendaraan, Pelanggan pemilik) {
        this.platNo = platNo;
        this.warna = warna;
        this.merk = merk;
        this.tipe = tipe;
        this.jenisKendaraan = jenisKendaraan;
        this.pemilik = pemilik;
    }

    public void addComponent(Komponen component) {
        components.add(component);
    }
    public boolean isExpressService(){
        return this.expressService;
    }

    // overload method
    public void serviceExpress(boolean express){
        this.expressService = express;
    }
    public void serviceExpress(){
        this.expressService = false;
    }

    public abstract int calculateCost() ;

//    public abstract int getExpressServiceCost();
//    public abstract int getRegularServiceCost();
    public abstract void printServiceHistory();

    public String getPlatNo() { return platNo; }
    public String getWarna() { return warna; }
    public String getMerk() { return merk; }
    public String getTipe() { return tipe; }
    public char getJenisKendaraan() { return jenisKendaraan; }
    public Pelanggan getPemilik() { return pemilik; }
    public List<Komponen> getComponents() {
        return components;
    }
    public boolean isInProgress() { return inProgress; }
    public void setInProgress(boolean inProgress) { this.inProgress = inProgress; }
}
