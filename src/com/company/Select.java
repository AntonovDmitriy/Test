package com.company;

import java.io.*;
import java.util.Objects;

class Select extends ScanFile implements FilenameFilter {

    String ext;

    static void findFiles(File folder) throws IOException {

        File myfile = new File("D:/" + nameFiles + ".txt");
        PrintWriter pw = new PrintWriter(new FileWriter(myfile, true));

        for (File a : Objects.requireNonNull(folder.listFiles((new Select(extend))))) {
            System.out.println("Найден файл: " + a.getName() + " Размер: " + a.length() + " Байт");
            pw.println("FILE:      " + a.getName() + ", SIZE: " + a.length() + " Byte");
        }
        pw.close();
        for (File b : Objects.requireNonNull(folder.listFiles())) {
            if (b.isDirectory() && b.listFiles() != null) {
                findFiles(b);
            }
        }
    }

    public Select(String ext) {
        this.ext = ext.toLowerCase();
    }

    public boolean accept(File folder, String name) {
        return name.toLowerCase().endsWith(ext);
    }
}


