package teammates.main;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;

public class BranchCoverageInstrumentation {
    public static Map<Integer, Boolean> coverageFunction1 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction2 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction3 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction4 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction5 = new HashMap<>();

    public static void printCoverageDataToFile(Map<Integer, Boolean> coverageFunction, int functionNumber, int numberOfBranches) {
        try {
            FileWriter writer = null;
            String fileName = "coverageFunction" + functionNumber + "_data.txt";
            writer = new FileWriter(fileName);

            writer.write("#########Function" + functionNumber + "#########\n");

            int coveredBranches = 0;
            for (Map.Entry<Integer, Boolean> entry : coverageFunction.entrySet()) {
                writer.write("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered\n" : "Not Covered\n"));
                if (entry.getValue()) {
                    coveredBranches++;
                }
            }

            writer.write("###########################\n");
            writer.write("Time: " + java.time.LocalTime.now() + "\n");
            writer.write("Branch Coverage Percentage: " + (coveredBranches * 100.0 / numberOfBranches) + "%\n");
            writer.close();
            System.out.println("Coverage data written to " + fileName);
        } catch (IOException e) {
             System.out.println("Error writing coverage data to file: " + e.getMessage());
        }
    }
}

