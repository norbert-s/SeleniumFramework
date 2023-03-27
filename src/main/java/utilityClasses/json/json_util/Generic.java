package utilityClasses.json.json_util;

import java.io.*;

public class Generic {
    public static void outputStreamWriter(String filePath, String data) throws IOException {
        try (OutputStream out = new FileOutputStream(filePath);
             Writer writer = new OutputStreamWriter(out,"UTF-8")) {
            writer.write(data);
        }
    }
}
