package exercise3;

public class StudyRoom {
    // Estado privado requerido por el diagrama
    private String title;
    private final int capacity;
    private int reserved;

    // Constructor principal que acepta título y capacidad
    public StudyRoom(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.reserved = 0; // Inicialmente sin asientos reservados
    }

    // Constructor sobrecargado que acepta solo el título
    public StudyRoom(String title) {
        this(title, 4); // Delegación al constructor principal
    }

    // Accesos públicos
    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getReserved() {
        return reserved;
    }

    // Método para obtener los asientos restantes
    public int getRemaining() {
        return capacity - reserved;
    }

    // Reservar un número específico de asientos
    public boolean reserve(int seats) {
        if (seats > 0 && seats <= getRemaining()) {
            reserved += seats;
            return true;
        }
        return false; // Rechazado si excede la capacidad o es inválido
    }

    // Sobrecarga de reserva para reservar 1 asiento por defecto
    public boolean reserve() {
        return reserve(1);
    }

    // Cancelar reservas de asientos
    public boolean cancel(int seats) {
        if (seats > 0 && seats <= reserved) {
            reserved -= seats;
            return true;
        }
        return false; // Rechazado si se intenta cancelar más de lo reservado
    }
}