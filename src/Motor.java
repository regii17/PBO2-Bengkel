import java.util.ArrayList;
import java.util.List;

// inheritance
public class Motor extends Kendaraan {
    private static final int BIAYA_SERVICE_EXPRESS = 150000;
    private static final int BIAYA_SERVICE_REGULAR = 80000;
    private List<Komponen> components = getComponents();

    public Motor(String platNo, String warna, String merk, String tipe, Pelanggan pemilik) {
        super(platNo, warna, merk, tipe, '2', pemilik);
    }


    // overriding
    @Override
    public int calculateCost() {
        int baseCost = isExpressService() ? BIAYA_SERVICE_EXPRESS : BIAYA_SERVICE_REGULAR;
        int componentCost = components.stream().mapToInt(Komponen::getPrice).sum();
        return baseCost + componentCost;
    }

    @Override
    public void printServiceHistory() {
        System.out.println("Motor service history for " + getPlatNo());
    }
}
