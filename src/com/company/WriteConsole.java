package com.company;

import java.io.File;
import java.util.Objects;

class WriteConsole extends ScanFile {

    static void filesFolder(File folder) {

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory() && file.listFiles() == null){
                System.out.println(file+" Недоступная папка !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            else if (file.isFile()) {
                System.out.println("FILE:      " + file.getName() + ", SIZE: " + file.length() + " Byte");

            } else if (file.isDirectory()) {
                long size = getFolderSize(file);
                System.out.println("DIRECTORY: " + file.getName() + ", SIZE: " + size + " Byte");
                filesFolder(file);
            }
        }
    }
}

