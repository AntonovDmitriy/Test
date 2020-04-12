package com.company;

import java.io.File;

public class CommandLineArgumentSupplier implements ArgumentSupplier{

    private String[] argumentsArray;

    public CommandLineArgumentSupplier(String[] argumentsArray) {
        this.argumentsArray = argumentsArray;
    }


    @Override
    public Arguments processArguments() throws ArgumentsException {
        if(argumentsArray!=null && argumentsArray.length > 3){
            File scanDirectory = checkAndGetScanDirectory(argumentsArray[0]);
            File scanReportPath = checkAndGetReportPath(argumentsArray[1]);
            File scanReportFileName = checkAndGetReportFileName(argumentsArray[2]);
            String extensionFilter = checkAndGetExtensionFilter(argumentsArray);
            return new Arguments(scanDirectory, scanReportPath, scanReportFileName, extensionFilter);
        }else{
            throw new ArgumentsException("Неправильное число аргументов. Минимальное число 3");
        }
    }

    public Arguments processArguments() {
        Arguments e =null;
        File file =new File (argumentsArray[0]) ;
        if (!file.exists()|| (file.isDirectory() && file.length() == 0))
        {
            System.out.println("В каталоге нет файлов!!!");
        } else{
            e = new Arguments (argumentsArray[0],argumentsArray[1],argumentsArray[2],argumentsArray[3]);
        }

        return e;

    }
}
}
