package com.ddamiani.codejam;

import com.ddamiani.codejam.filehandler.CodeJamFileHandler;

import java.lang.reflect.Constructor;

/**
 * Generic runner for CodeJam classes
 */
public class Runner {
    private static final String packageBaseName = Runner.class.getPackage().getName();

    public static void main(String[] args) {
        final ClassLister<CodeJamFileHandler> problemGetter = new ClassLister<>(CodeJamFileHandler.class);

        if (args.length > 0) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                System.out.println("usage: codejam-runner [-h] PROBLEM_NAME INPUT_FILE [OUTPUT_FILE]");
                System.out.println("\nCommand line runner for Google Codejam solutions");
                System.out.println("\noptional arguments:");
                System.out.println("  -h, --help    show this help message and exit");
                System.out.println("\navailable problems:");
                for (Class<? extends CodeJamFileHandler> problem : problemGetter.getClassSetInPackage(packageBaseName)) {
                    System.out.println("  " + problem.getSimpleName());
                }
                System.exit(0);
            }
        }

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
            Class<? extends CodeJamFileHandler> problemClass = problemGetter.getClassInPackage(packageBaseName, args[0]);
            if (problemClass != null) {
                Constructor constructor = problemClass.getConstructor(String.class, String.class);
                CodeJamFileHandler fileHandler = (CodeJamFileHandler) constructor.newInstance(inputName, outputName);
                fileHandler.operate();
                fileHandler.close();
            } else {
                System.out.println("The chosen problem \"" + args[0] + "\" is not a valid problem!");
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }
}
