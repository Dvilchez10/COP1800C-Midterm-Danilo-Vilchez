package exercise4;

import java.util.Scanner;

public class ResourceApp {

    public static void main(String[] args) {
        // Crear el catálogo inicial requerido por el examen
        CampusResource[] catalog = {
                new StudySpace("Quiet Study", 4),     // Recurso 1 (Cap 4, Fee 3)
                new ComputerLab("Computer Lab", 3),   // Recurso 2 (Cap 3, Fee 8)
                new StudySpace("Group Study", 2)      // Recurso 3 (Cap 2, Fee 3)
        };

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== CAMPUS RESOURCE RESERVATION APP ===");

        while (running) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1 = Reserve");
            System.out.println("2 = Cancel");
            System.out.println("3 = Report");
            System.out.println("0 = Exit");
            System.out.print("Seleccione una opción: ");

            String input = scanner.nextLine();
            int choice = -1;

            try {
                choice = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no numérica. Intente nuevamente.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleReservation(catalog, scanner, true);
                    break;
                case 2:
                    handleReservation(catalog, scanner, false);
                    break;
                case 3:
                    printReport(catalog);
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    running = false;
                    break;
                default:
                    System.out.println("Error: Opción no soportada en el menú.");
                    break;
            }
        }
        scanner.close();
    }

    // Método auxiliar para gestionar reservas
    private static void handleReservation(CampusResource[] catalog, Scanner scanner, boolean isReserve) {
        String actionName = isReserve ? "reservar" : "cancelar";

        System.out.print("Ingrese el número del recurso (1 al " + catalog.length + "): ");
        String resInput = scanner.nextLine();

        try {
            int resNum = Integer.parseInt(resInput.trim());
            // Validación de nivel de aplicación
            if (resNum < 1 || resNum > catalog.length) {
                System.out.println("Error de aplicación: Número de recurso fuera de rango.");
                return;
            }

            CampusResource selectedResource = catalog[resNum - 1];

            System.out.print("Ingrese la cantidad de asientos a " + actionName + ": ");
            String seatInput = scanner.nextLine();
            int seats = Integer.parseInt(seatInput.trim());

            boolean success = false;
            if (isReserve) {
                success = selectedResource.reserve(seats);
            } else {
                success = selectedResource.cancel(seats);
            }

            if (success) {
                System.out.println("¡Operación de " + actionName + " exitosa!");
            } else {
                System.out.println("Operación rechazada (por límites de capacidad o valores inválidos).");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
        }
    }

    // Método para imprimir el reporte detallado usando StringBuilder
    public static void printReport(CampusResource[] catalog) {
        StringBuilder sb = new StringBuilder();
        int totalAppRevenue = 0;

        sb.append("\n=== REPORTE DE RECURSOS DEL CAMPUS ===\n");
        sb.append(String.format("%-3s | %-15s | %-10s | %-10s | %-10s | %-10s\n",
                "No.", "Título", "Reservados", "Restantes", "Ingresos", "Ocupación"));
        sb.append("--------------------------------------------------------------------\n");

        for (int i = 0; i < catalog.length; i++) {
            CampusResource res = catalog[i];
            int revenue = res.getRevenue();
            double occupancy = res.getOccupancy();
            totalAppRevenue += revenue;

            sb.append(String.format("%-3d | %-15s | %-10d | %-10d | $%9.2f | %9.1f%%\n",
                    (i + 1),
                    res.getTitle(),
                    res.getReserved(),
                    res.getRemaining(),
                    (double) revenue,
                    occupancy
            ));
        }

        sb.append("--------------------------------------------------------------------\n");
        sb.append(String.format("Ingresos Totales Acumulados de la Aplicación: $%d.00\n", totalAppRevenue));

        System.out.print(sb.toString());
    }
}