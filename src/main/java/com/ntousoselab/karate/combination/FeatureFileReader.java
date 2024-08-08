package com.ntousoselab.karate.combination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FeatureFileReader {
    public static String readFeatureFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
}
