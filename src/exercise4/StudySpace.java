package exercise4;

public class StudySpace extends CampusResource {
    public static final int FEE = 3; // Tarifa fija por asiento

    public StudySpace(String title, int capacity) {
        super(title, capacity);
    }

    @Override
    public int feePerSeat() {
        return FEE;
    }
}