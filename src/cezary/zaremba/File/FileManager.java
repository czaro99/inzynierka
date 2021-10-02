package cezary.zaremba.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    public static void writeFile(List<String> content) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("attenuation.txt"));
            for (String s:
                 content) {
                bufferedWriter.write(s+"\n");
            }
            bufferedWriter.close();
        } catch (Exception exception) {
            System.out.println("error");
        }
    }


}
