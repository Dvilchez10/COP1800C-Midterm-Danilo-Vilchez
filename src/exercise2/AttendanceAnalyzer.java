package exercise2;

import java.util.Scanner;

public class AttendanceAnalyzer {

    public static void main(String[] args) {
        // Declaración de la matriz de visitas
        int[][] visits = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== CAMPUS STUDY CENTER - ATTENDANCE ANALYZER ===");

        // Menú repetitivo con while y switch
        while (running) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1 = Summary");
            System.out.println("2 = Find room");
            System.out.println("0 = Exit");
            System.out.print("Seleccione una opción: ");

            String input = scanner.nextLine();

            // Validación de entrada del menú
            int choice = -1;
            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no numérica. Por favor ingrese un número válido.");
                continue; // Regresa al inicio del menú sin cerrar el programa
            }

            switch (choice) {
                case 1:
                    printSummary(visits);
                    break;
                case 2:
                    findRoomSearch(visits, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    running = false;
                    break;
                default:
                    System.out.println("Error: Opción no soportada. Intente nuevamente.");
                    break;
            }
        }
        scanner.close();
    }

    // Método que calcula y retorna los totales por cada sala
    public static int[] roomTotals(int[][] visits) {
        int[] totals = new int[visits.length];
        for (int i = 0; i < visits.length; i++) {
            int sum = 0;
            for (int j = 0; j < visits[i].length; j++) {
                sum += visits[i][j];
            }
            totals[i] = sum;
        }
        return totals;
    }

    // Método que calcula y retorna los totales por cada período
    public static int[] periodTotals(int[][] visits) {
        int periods = visits[0].length;
        int[] totals = new int[periods];
        for (int j = 0; j < periods; j++) {
            int sum = 0;
            for (int i = 0; i < visits.length; i++) {
                sum += visits[i][j];
            }
            totals[j] = sum;
        }
        return totals;
    }

    // Método que imprime el resumen detallado y estadísticas
    public static void printSummary(int[][] visits) {
        int[] rTotals = roomTotals(visits);
        int[] pTotals = periodTotals(visits);

        int grandTotal = 0;
        int totalCells = 0;
        for (int r : rTotals) {
            grandTotal += r;
        }

        for (int i = 0; i < visits.length; i++) {
            for (int j = 0; j < visits[i].length; j++) {
                totalCells++;
            }
        }

        double average = (double) grandTotal / totalCells;

        // Encontrar la sala con más visitas aplicando la regla de desempate
        int maxVisits = -1;
        int bestRoomIndex = 0;
        for (int i = 0; i < rTotals.length; i++) {
            if (rTotals[i] > maxVisits) {
                maxVisits = rTotals[i];
                bestRoomIndex = i; // Guarda el índice menor en caso de empate
            }
        }

        // Conteo de salas ocupadas
        int busyRoomsCount = 0;
        for (int r : rTotals) {
            if (r >= 6) {
                busyRoomsCount++;
            }
        }

        System.out.println("\n--- REPORTE DE RESUMEN ---");
        System.out.println("Gran total de visitas: " + grandTotal);
        System.out.printf("Promedio decimal por celda: %.2f%n", average);
        System.out.println("Sala con mayor asistencia: Room " + (bestRoomIndex + 1) + " (" + maxVisits + " visitas)");
        System.out.println("Cantidad de salas ocupadas (Busy rooms >= 6): " + busyRoomsCount);
    }

    // Búsqueda interactiva de sala con validación de índices y excepciones
    public static void findRoomSearch(int[][] visits, Scanner scanner) {
        System.out.print("Ingrese el número de sala a buscar (1 al " + visits.length + "): ");
        String roomInput = scanner.nextLine();

        try {
            int roomNumber = Integer.parseInt(roomInput.trim());
            // Validación estricta del rango de la sala antes de indexar
            if (roomNumber < 1 || roomNumber > visits.length) {
                System.out.println("Error: Número de sala fuera de rango. Debe estar entre 1 y " + visits.length + ".");
                return;
            }

            int index = roomNumber - 1; // Conversión de número mostrado a índice de arreglo
            int[] rTotals = roomTotals(visits);
            int roomTotal = rTotals[index];
            boolean isBusy = roomTotal >= 6;

            System.out.println("--- RESULTADO DE BÚSQUEDA ---");
            System.out.println("Sala consultada: Room " + roomNumber);
            System.out.println("Total de visitas: " + roomTotal);
            System.out.println("Estado: " + (isBusy ? "Ocupada (Busy)" : "No ocupada"));

        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada no válida. Debe ingresar un número entero.");
        }
    }
}