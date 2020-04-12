package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

class WriteFile extends ScanFile {

    static void filesFolder(File folder) throws IOException {

        File myfile = new File("D:/" + nameFiles + ".txt");
        PrintWriter pw = new PrintWriter(new FileWriter(myfile, true));

        for (File file : Objects.requireNonNull(folder.listFiles())) {

            if (file.isDirectory() && file.listFiles() == null) {
                //System.out.println();
            } else if (file.isFile()) {
                pw.println("FILE:      " + file.getName() + ", SIZE: " + file.length() + " Byte");
            } else if (file.isDirectory()) {
                long size = getFolderSize(file);
                pw.println("------------------------------------");
                pw.println("DIRECTORY: " + file.getName() + ", SIZE: " + size + " Byte");
                filesFolder(file);
                pw.println("------------------------------------");
            }
        }
        pw.close();
    }
}


