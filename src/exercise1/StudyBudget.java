package exercise1;

public class StudyBudget {

    // Definición de constantes estáticas
    public static final int BLOCK_MINUTES = 30;
    public static final int FEE_PER_BLOCK = 5;
    public static final double DISCOUNT_RATE = 0.20;

    public static void main(String[] args) {
        // Valores iniciales de prueba
        String rawName = " Lucia Mendez";
        int minutes = 150;
        boolean isMemberActive = true;

        System.out.println("=== EXERCISE 1: STUDY BUDGET REPORT ===");

        // Validación de datos de entrada
        if (rawName == null || rawName.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }
        if (minutes <= 0) {
            System.out.println("Error: La duración en minutos debe ser mayor que cero.");
            return;
        }

        // Limpieza de cadena y extracción de propiedades
        String cleanedName = rawName.trim();
        String upperName = cleanedName.toUpperCase();
        char firstInitial = cleanedName.charAt(0);

        // Cálculos de bloques y minutos
        int completeBlocks = minutes / BLOCK_MINUTES;
        int leftoverMinutes = minutes % BLOCK_MINUTES;

        //Elegibilidad del descuento
        boolean isEligibleForDiscount = isMemberActive && (completeBlocks >= 4);

        //Cálculo de tarifas y descuentos
        double baseFee = completeBlocks * FEE_PER_BLOCK;
        double discountAmount = 0.0;

        if (isEligibleForDiscount) {
            discountAmount = baseFee * DISCOUNT_RATE;
        }

        double finalFee = baseFee - discountAmount;

        // Impresión del reporte
        System.out.println("Nombre original: " + rawName);
        System.out.println("Nombre limpio: " + cleanedName);
        System.out.println("Nombre en mayúsculas: " + upperName);
        System.out.println("Inicial del nombre: " + firstInitial);
        System.out.println("Membresía activa: " + isMemberActive);
        System.out.println("Minutos totales: " + minutes);
        System.out.println("Bloques completos de 30 min: " + completeBlocks);
        System.out.println("Minutos sobrantes: " + leftoverMinutes);
        System.out.println("Elegible para descuento: " + isEligibleForDiscount);
        System.out.printf("Tarifa base (Fee): $%.2f%n", baseFee);
        System.out.printf("Monto de descuento: $%.2f%n", discountAmount);
        System.out.printf("Tarifa final a pagar: $%.2f%n", finalFee);
    }
}