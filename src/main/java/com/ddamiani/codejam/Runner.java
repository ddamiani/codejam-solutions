package com.ddamiani.codejam;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.lang.reflect.Constructor;

/**
 * Generic runner for CodeJam classes
 */
public class Runner {
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.err.println("Incorrect number of input parameters!");
            System.exit(1);
        }

        String inputName = args[1];
        String outputName = null;
        if (args.length == 3) {
            outputName = args[2];
        }

        try {
            Constructor constructor = Class.forName(args[0]).getConstructor(String.class, String.class);
            CodeJamFileHandler fileHandler = (CodeJamFileHandler) constructor.newInstance(inputName, outputName);
            fileHandler.operate();
            fileHandler.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }

    }
}
