package server.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

final class FileIO {

    static void createDirectory(File dir) {
        if (dir.exists()) {
            throw new IllegalArgumentException("directory with name already exists");
        }

        try {
            dir.mkdir();
            Files.copy(Paths.get(XmlUtilities.getDtd()), Paths.get(dir.toString(), XmlUtilities.getDtd()), REPLACE_EXISTING);
        } catch (SecurityException ex) {
            System.out.println("permission denied: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("can not create dtd file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    static void deleteDirectory(File dir){
        if (!dir.exists()) {
            throw new IllegalArgumentException("directory does not exist");
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
            throw new IllegalArgumentException("file with name does not exist");
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("is not a file");
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

    static File getSchemaLocation(String schemaName) {
        return Paths.get(SchemaIO.DATABASE_LOCATION, schemaName).toFile();
    }

    static File convertToFile(String schemaName, String tableName) {
        return Paths.get(schemaName, tableName + TableIO.TABLE_ENDING).toFile();
    }
}
