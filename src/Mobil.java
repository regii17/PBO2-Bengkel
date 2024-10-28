import java.util.ArrayList;
import java.util.List;

public class Mobil extends Kendaraan {
    private static final int BIAYA_SERVICE_EXPRESS = 250000;
    private static final int BIAYA_SERVICE_REGULAR = 120000;
    private List<Komponen> components = getComponents();

    public Mobil(String platNo, String warna, String merk, String tipe, Pelanggan pemilik) {
        super(platNo, warna, merk, tipe, '4', pemilik);
    }

    @Override
    public int calculateCost() {
//        return isExpressService() ? BIAYA_SERVICE_EXPRESS : BIAYA_SERVICE_REGULAR;
        int baseCost = isExpressService() ? BIAYA_SERVICE_EXPRESS : BIAYA_SERVICE_REGULAR;
        int componentCost = components.stream().mapToInt(Komponen::getPrice).sum();
        return baseCost + componentCost;
    }

    @Override
    public void printServiceHistory() {
        System.out.println("Mobil service history for " + getPlatNo());
    }
}
