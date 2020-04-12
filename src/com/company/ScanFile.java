package com.company;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class ScanFile {

    static String nameFiles;      // имя файла для сохранения
    static String extend;         // выбор файлов с расширением
    static String directory;      // выбор каталога для сканирования

    public static void main(String[] args) throws IOException {






        ArgumentSupplier argumentSupplier= new CommandLineArgumentSupplier(args);
        Arguments arguments = argumentSupplier.processArguments();
        logExistingDrives();

        try(
        Collecion<ScanWriter> writers = List.of(new FileDescriptionWriter(arguments.getResultFileName()),
                                        new ConsoleDescriptionWriter(arguments.getResultFileName()));
        FileScanner scanner = new FileScanner(arguments, writers);
        scanner.execute();
        )catch (.........){
            .......
        }


        try {
            FileScanner fileScanner = new FileScanner(arguments.getDirectory(), arguments.getFilteringFileExtensions);
            Collection<Files> files =  fileScanner.scan();

        } catch(.....){
            ..........
        }



        logExistingDrives();

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите путь к каталогу для сканирования: ");
            directory = scan.nextLine();
            File folder = new File(directory);
            if (folder.isDirectory() && folder.length() == 0) {
                System.out.println("В каталоге нет файлов...");
                continue;
            } else if (!folder.exists()) {
                System.out.println("Каталог не найден, попробуйте еще раз...");
                continue;
            } else if (folder.exists()) {
                System.out.println("Каталог найден!\nВыберите дальнейшие варианты: ");
            }
            break;
        }


        while (true) {
            Scanner scan = new Scanner(System.in);
            File folder = new File(directory);
            System.out.println("Введите (1) для поиска всех папок и файлов в каталоге с указанием их размера\n" +
                    "Введите (2) для поиска файлов с необходимым расширением");
            int sel = scan.nextInt();
            if (sel == 1) {
                Scanner scan1 = new Scanner(System.in);
                System.out.print("Введите имя файла: ");
                nameFiles = scan1.nextLine();
                System.out.println("Список файлов будет сохранен в D:\\" + nameFiles + ".txt");
                WriteConsole.filesFolder(folder);
                WriteFile.filesFolder(folder);

            } else if (sel == 2) {
                Scanner scan2 = new Scanner(System.in);
                System.out.print("Введите тип расширения для поиска: ");
                extend = "." + scan2.nextLine();
                System.out.print("Введите имя файла: ");
                nameFiles = scan2.nextLine();
                System.out.println("Список файлов c расширением (" + extend + ") будет сохранен в D:\\" + nameFiles + ".txt");
                Select.findFiles(folder); // метод поиска файлов с заданным расширением
            } else {
                System.out.println("Выберите 1 или 2 !!!!!!!!!!!!!");
                continue;
            }
            System.out.println("Проверка завершена");
            break;
        }
    }

    private static void logExistingDrives() {
        File[] roots = File.listRoots();
        System.out.println("Доступные диски для сканирования:");
        for (File list : roots)
            System.out.println(list.getAbsoluteFile() + " , SIZE: " + list.getTotalSpace() + " Byte");
    }

    static long getFolderSize(File dir) {  // Измерение размера папки со всеми вложениями
        long size = 0;
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                //System.out.println(file);
                size += file.length();
            } else if (file.isDirectory() && file.listFiles() != null) {
                //System.out.println(file);
                size += getFolderSize(file);
            }
        }
        return size;
    }
}
