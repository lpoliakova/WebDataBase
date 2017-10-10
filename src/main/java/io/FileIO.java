package io;

import java.io.File;
import java.nio.file.Paths;

public class FileIO {
    private static final String FILE_ENDING = ".xml";

    public static void createDirectory(String name){
        File dir = new File(name);

        if (!dir.exists()) {
            try{
                dir.mkdir();
            }
            catch(SecurityException ex){
                System.out.println("permission denied: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static void deleteDirectory(String name){
        File dir = new File(name);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                delete(file);
            }
        }
        delete(dir);
    }

    public static void deleteFile(String schemaName, String name) {
        File dir = convertToFile(schemaName, name);
        delete(dir);
    }

    public static void delete(File file){
        if (file.exists()) {
            try{
                file.delete();
            }
            catch(SecurityException ex){
                System.out.println("permission denied: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public static File convertToFile(String schemaName, String tableName) {
        return Paths.get(schemaName, tableName + FILE_ENDING).toFile();
    }

}
