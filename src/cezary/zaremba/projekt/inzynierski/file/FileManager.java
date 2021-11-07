package cezary.zaremba.projekt.inzynierski.file;

import java.io.*;
import java.util.List;

public class FileManager {

    private String convertToCSV(String[] data) {
        return String.join(",", data);
    }

    public void writeToCSV(List<String[]> data) throws IOException {
        File csvOutputFile = new File("result.csv");
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            data.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        }
    }

}
