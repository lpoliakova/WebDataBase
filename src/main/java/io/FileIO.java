package io;

import java.io.File;
import java.nio.file.Paths;

final class FileIO {
    private static final String FILE_ENDING = ".xml";

    static void createDirectory(String name) {
        File dir = new File(name);
        if (dir.exists()) {
            throw new IllegalArgumentException("directory with name " + name + " already exists");
        }

        try {
            dir.mkdir();
        } catch (SecurityException ex) {
            System.out.println("permission denied: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    static void deleteDirectory(String name){
        File dir = new File(name);
        if (!dir.exists()) {
            throw new IllegalArgumentException("directory with name " + name + " does not exist");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getName() + " is not a directory");
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                delete(file);
            }
        }
        delete(dir);
    }

    static void deleteFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("file with name " + file.getName() + " does not exist");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(file.getName() + " is not a file");
        }
        delete(file);
    }

    private static void delete(File file) {
        try {
            file.delete();
        } catch (SecurityException ex) {
            System.out.println("permission denied: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    static File convertToFile(String schemaName, String tableName) {
        return Paths.get(schemaName, tableName + FILE_ENDING).toFile();
    }

}
