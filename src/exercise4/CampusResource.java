package exercise4;

import exercise3.StudyRoom;

public abstract class CampusResource extends StudyRoom implements Priced {

    // Constructor que reutiliza el de StudyRoom validando títulos y capacidades
    public CampusResource(String title, int capacity) {
        super(title, capacity);
    }

    // Método abstracto que implementarán las subclases
    public abstract int feePerSeat();

    // Cálculo de ingresos totales basado en reservas actuales y tarifa por asiento
    public int getRevenue() {
        return getReserved() * feePerSeat();
    }

    // Cálculo de ocupación en porcentaje
    public double getOccupancy() {
        if (getCapacity() == 0) {
            return 0.0;
        }
        return ((double) getReserved() / getCapacity()) * 100.0;
    }
}