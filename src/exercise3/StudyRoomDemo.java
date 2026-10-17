package exercise3;

public class StudyRoomDemo {

    public static void main(String[] args) {
        System.out.println("=== EXERCISE 3: STUDY ROOM DEMO ===");

        //Crear instancias usando constructores sobrecargados
        StudyRoom roomA = new StudyRoom("Quiet Pod", 5);
        StudyRoom roomB = new StudyRoom("Collaborative Lab"); // Capacidad por defecto 4

        printRoomState("Estado inicial Room A", roomA);
        printRoomState("Estado inicial Room B", roomB);

        //Probar reservas exitosas y fallidas
        System.out.println("\n--- Probando Reservas ---");
        boolean res1 = roomA.reserve(3);
        System.out.println("Reservar 3 en Room A: " + (res1 ? "Éxito" : "Rechazado"));
        printRoomState("Actual", roomA);

        boolean res2 = roomA.reserve(4); // Debe fallar porque supera los 2 asientos restantes
        System.out.println("Intentar reservar 4 más en Room A: " + (res2 ? "Éxito" : "Rechazado (Preserva estado)"));
        printRoomState("Actual (sin cambios)", roomA);

        // Reserva por defecto
        roomB.reserve();
        printRoomState("Room B tras reserva por defecto de 1", roomB);

        // Probar cancelaciones
        System.out.println("\n--- Probando Cancelaciones ---");
        boolean cancel1 = roomA.cancel(2);
        System.out.println("Cancelar 2 asientos en Room A: " + (cancel1 ? "Éxito" : "Rechazado"));
        printRoomState("Actual", roomA);

        boolean cancel2 = roomA.cancel(10); // Falla por exceder lo reservado
        System.out.println("Intentar cancelar 10 en Room A: " + (cancel2 ? "Éxito" : "Rechazado (Preserva estado)"));

        //Demostración de Alias
        System.out.println("\n--- Demostración de Alias ---");
        StudyRoom originalRoom = new StudyRoom("Conference Room", 10);
        StudyRoom aliasRoom = originalRoom; // Alias apuntando al mismo objeto

        aliasRoom.reserve(5);
        System.out.println("Reservados 5 asientos usando el alias.");
        printRoomState("Verificando originalRoom (afectado por el alias)", originalRoom);
    }

    private static void printRoomState(String label, StudyRoom room) {
        System.out.println("[" + label + "] Título: " + room.getTitle()
                + " | Capacidad: " + room.getCapacity()
                + " | Reservados: " + room.getReserved()
                + " | Restantes: " + room.getRemaining());
    }
}