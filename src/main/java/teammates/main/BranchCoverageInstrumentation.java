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

    public static void printCoverageDataToFile(Map<Integer, Boolean> coverageFunction, int id) {
        try {
            FileWriter writer = null;
            if (id == 1) {
                writer = new FileWriter("coverageFunction1_data.txt");
            } else if (id == 2) {
                writer = new FileWriter("coverageFunction2_data.txt");
            } else if (id == 3) {
                writer = new FileWriter("coverageFunction3_data.txt");
            } else if (id == 4) {
                writer = new FileWriter("coverageFunction4_data.txt");
            } else if (id == 5) {
                writer = new FileWriter("coverageFunction5_data.txt");
            }

            writer.write("#########Function"+id+"#########\n");
            for (Map.Entry<Integer, Boolean> entry : coverageFunction.entrySet()) {
                writer.write("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered\n" : "Not Covered\n"));
            }
            writer.write("###########################\n");
            writer.write("Time: " + java.time.LocalTime.now() + "\n");
            writer.close();
            System.out.println("Coverage data written to coverage_data.txt");
        } catch (IOException e) {
             System.out.println("Error writing coverage data to file: " + e.getMessage());
        }
    }
}

