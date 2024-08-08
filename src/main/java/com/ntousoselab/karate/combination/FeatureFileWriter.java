package com.ntousoselab.karate.combination;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FeatureFileWriter {
    public static void writeFeatureFile(String filePath, String content) throws IOException {
        Files.writeString(Paths.get(filePath), content);
    }
}
