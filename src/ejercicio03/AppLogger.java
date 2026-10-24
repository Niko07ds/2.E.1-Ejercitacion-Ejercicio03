package ejercicio03;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {

    // Instancia única del registrador
    private static AppLogger instance;

    // Flujo de escritura compartido
    private final BufferedWriter writer;

    // Formato de fecha y hora de los mensajes
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Constructor privado para impedir otras instancias
    private AppLogger() {
        try {
            writer = Files.newBufferedWriter(
                    Path.of("application.log"),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("Registrador iniciado correctamente.");
        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo iniciar el registrador.", e
            );
        }
    }

    // Devuelve siempre la misma instancia
    public static synchronized AppLogger getInstance() {
        if (instance == null) {
            instance = new AppLogger();
        }

        return instance;
    }

    // Registra un mensaje informativo
    public synchronized void logInfo(String message) {
        log("INFO", message);
    }

    // Registra una advertencia
    public synchronized void logWarning(String message) {
        log("WARNING", message);
    }

    // Registra un error
    public synchronized void logError(String message) {
        log("ERROR", message);
    }

    // Añade la fecha, el nivel y el mensaje al registro
    private synchronized void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        String entry = timestamp + " [" + level + "] " + message;

        try {
            writer.write(entry);
            writer.newLine();
            writer.flush();

            System.out.println(entry);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo escribir el registro.", e
            );
        }
    }
}
