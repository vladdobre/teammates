package teammates.main;

import java.util.HashMap;
import java.util.Map;

public class BranchCoverageInstrumentation {
    public static Map<Integer, Boolean> coverageFunction1 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction2 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction3 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction4 = new HashMap<>();
    public static Map<Integer, Boolean> coverageFunction5 = new HashMap<>();

    public static void printCoverageData() {
        System.out.println("#########Function1#########");
        for (Map.Entry<Integer, Boolean> entry : coverageFunction1.entrySet()) {
            System.out.println("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered" : "Not Covered"));
        }
        System.out.println("###########################");
        System.out.println("#########Function2#########");
        for (Map.Entry<Integer, Boolean> entry : coverageFunction2.entrySet()) {
            System.out.println("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered" : "Not Covered"));
        }
        System.out.println("###########################");
        System.out.println("#########Function3#########");
        for (Map.Entry<Integer, Boolean> entry : coverageFunction3.entrySet()) {
            System.out.println("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered" : "Not Covered"));
        }
        System.out.println("###########################");
        System.out.println("#########Function4#########");
        for (Map.Entry<Integer, Boolean> entry : coverageFunction4.entrySet()) {
            System.out.println("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered" : "Not Covered"));
        }
        System.out.println("###########################");
        System.out.println("#########Function5#########");
        for (Map.Entry<Integer, Boolean> entry : coverageFunction5.entrySet()) {
            System.out.println("Branch " + entry.getKey() + ": " + (entry.getValue() ? "Covered" : "Not Covered"));
        }
        System.out.println("###########################");
    }
}
