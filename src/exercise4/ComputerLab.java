package exercise4;

public class ComputerLab extends CampusResource {
    public static final int FEE = 8; // Tarifa fija por asiento

    public ComputerLab(String title, int capacity) {
        super(title, capacity);
    }

    @Override
    public int feePerSeat() {
        return FEE;
    }
}