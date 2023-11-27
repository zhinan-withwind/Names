package run.zhinan.names.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void saveToFile(String file, String content) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(content);
        writer.close();
    }
}
