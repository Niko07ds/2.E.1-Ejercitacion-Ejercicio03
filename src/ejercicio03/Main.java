package ejercicio03;

public class Main {

    public static void main(String[] args) {

        // Obtenemos la instancia única del registrador
        AppLogger logger1 = AppLogger.getInstance();

        // Registramos eventos de distintos niveles
        logger1.logInfo("La aplicación se ha iniciado.");
        logger1.logWarning("El espacio disponible es reducido.");
        logger1.logError("No se pudo procesar una operación.");

        // Obtenemos nuevamente la instancia
        AppLogger logger2 = AppLogger.getInstance();

        // Comprobamos que ambas referencias apuntan al mismo objeto
        System.out.println("¿Es el mismo registrador? " +
                (logger1 == logger2));

        // Registramos otro evento desde la segunda referencia
        logger2.logInfo("La aplicación continúa funcionando.");
    }
}
