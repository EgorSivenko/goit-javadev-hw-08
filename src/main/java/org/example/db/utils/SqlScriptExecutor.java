package org.example.db.utils;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SqlScriptExecutor {
    public static List<String> parseSqlScript(String scriptFilePath) {
        List<String> sqlStatements = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
            StringBuilder currentStatement = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();

                if (line.isBlank() || line.startsWith("--")) {
                    continue;
                }
                currentStatement.append(line).append(" ");

                if (line.endsWith(";")) {
                    sqlStatements.add(currentStatement.toString());
                    currentStatement.setLength(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlStatements;
    }
}
