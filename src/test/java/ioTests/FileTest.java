package ioTests;

import io.FileIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

public class FileTest {
    @Test
    void directoryCreationTest() {
        String dirName = "testSchema";
        FileIO.createDirectory(dirName);
        File dir = new File(dirName);
        Assertions.assertTrue(dir.exists());
    }

    @Test
    void directoryDeletionTest() {
        String dirName = "testSchema";
        FileIO.deleteDirectory(dirName);
        File dir = new File(dirName);
        Assertions.assertFalse(dir.exists());
    }

    @Test
    void fileDeletionTest() {
        String schemaName = "testSchema";
        String fileName = "testFile";
        FileIO.deleteFile(schemaName, fileName);
        File file = Paths.get(schemaName, fileName).toFile();
        Assertions.assertFalse(file.exists());
    }
}
