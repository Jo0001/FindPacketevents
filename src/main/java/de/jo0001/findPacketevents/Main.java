package de.jo0001.findPacketevents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = ".";
        if (args.length == 1) {
            path = args[0];
            System.out.printf("Using provided path %s%n", path);
        }
        System.out.println("Checking for plugins using packetevents");
        Files.walk(Paths.get(path)).filter(f -> f.toString().toLowerCase().endsWith(".jar")).forEach(f -> {
            try {
                ZipFile zipFile = new ZipFile(f.toFile());
                if (zipFile.stream().anyMatch(ze -> ze.getName().contains("packetevents"))) {
                    System.out.println("Found " + f.getFileName());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Done");
    }
}
