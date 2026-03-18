package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import enums.LogLevel;

public class Logger {
    private String message;
    private Date createdAt;
    private String className;

    public Logger(String message, String className) {
        this.message = message;
        this.className = className;
        this.createdAt = new Date();
    }

    public void log(LogLevel level, String filePath) {
        String log = "[" + level + "] " + createdAt + " [" + className + "]" + " - " + message;
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true))) {
            br.write(log);
            br.newLine();
        } catch (IOException e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }

    public void LogInfo() {
        log(LogLevel.INFO, "logs/info.log");
    }

    public void LogError() {
        log(LogLevel.ERROR, "logs/error.log");
    }
}
