package obp2.hash;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class ExternalHash {
    static {
        // Extracts ltl3ba executable from resources to be able to run it.
        String platform = System.getProperty("os.name").toLowerCase();
        String resource = "/obp2/hash/lib/";
        String suffix = "";
        if (platform.contains("mac")) {
            resource += "Darwin_x86_64/libobp2hash.dylib";
            suffix = ".dylib";
        } else if (platform.contains("linux")) {
            resource += "Linux_x86_64/libobp2hash.so";
            suffix = ".so";
        } else {
            // lets consider that it windows .....
            resource += "windows_x86_64/libobp2hash.dll";
            suffix = ".dll";
        }

        Path libraryPath = null;

        // Creates a tmp file on the computer
        try (InputStream inStream = ExternalHash.class.getResourceAsStream(resource)){
            libraryPath = Files.createTempFile("libobp2hash", suffix);

            try (OutputStream outStream = Files.newOutputStream(libraryPath)) {
                byte[] buffer = new byte[1024*10];
                int len = inStream.read(buffer);
                while (len != -1) {
                    outStream.write(buffer, 0, len);
                    len = inStream.read(buffer);
                }
            }

            libraryPath.toFile().setExecutable(true, false);
            System.load(libraryPath.toString());
        } catch (IOException e) {
            System.err.println("Can't create temp file to extract libobp2hash");
        }
    }

    static long testArraysHashCode(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = Arrays.hashCode(bytes);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Arrays.hashCode " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }
}
