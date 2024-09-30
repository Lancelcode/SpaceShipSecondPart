package spaceshipsecondarray;

import java.util.ArrayList;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.HashSet;
import java.util.StringTokenizer;







public class SpaceShipSecondArray {

    // Method to load data from the mission file
    public static String[] loadData(String filename) throws IOException {
        ArrayList<String> arrList = new ArrayList<>();
        File myFile = new File(filename);

        try (FileReader fr = new FileReader(myFile); BufferedReader br = new BufferedReader(fr)) {
            String data;

            // Read the file line by line
            while ((data = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data, ",");
                while (st.hasMoreTokens()) {
                    arrList.add(st.nextToken().trim());
                }
            }
        }
        // If there's an I/O error, we throw it back to be handled in main
        return arrList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] missions = {"src/mission1.txt", "src/mission2.txt", "src/mission3.txt", "src/mission4.txt", "src/mission5.txt", "src/mission6.txt", "src/mission7.txt"};
        final int MAX_PODS = 18; // Maximum allowed pods for a mission

        for (String mission : missions) {
            System.out.println("\n********************************************");
            System.out.println("*** Launching Mission: " + mission + "  ***");
            System.out.println("********************************************");
            String[] sd;

            // Try loading the data
            try {
                sd = loadData(mission);
            } catch (IOException e) {
                System.out.println("Aborting mission: " + mission + " due to file reading error. Cause: " + e.getMessage());
                System.out.println("***************************************************************************************");
                continue; // Skip to the next mission if there's a file error
            }

            // Check if there are too many pods in the mission
            if (sd.length > MAX_PODS) {
                System.out.println("*************************************************************************************************");
                System.out.println("*** Aborting mission: " + mission + " due to too many pods (" + sd.length + " pods). Maximum allowed is " + MAX_PODS + ". ***");
                System.out.println("*************************************************************************************************");
                continue; // Skip the rest of the mission processing and move to the next mission
            }

            // Set to track duplicate pod IDs
            HashSet<String> uniquePods = new HashSet<>();

            // Validate pods for duplicates and correct ID format (3 characters)
            boolean abortMission = false;
            for (String pod : sd) {
                if (!uniquePods.add(pod)) {
                    System.out.println("***********************************************************************");
                    System.out.println("*** Aborting mission: " + mission + " due to duplicate pod ID: " + pod + " ***");
                    System.out.println("***********************************************************************");
                    abortMission = true;
                    break;
                }

                // Check if pod ID has exactly 3 characters
                if (pod.length() != 3) {
                    System.out.println("****************************************************************************************************************");
                    System.out.println("*** Aborting mission: " + mission + " due to invalid pod ID format: " + pod + ". Pod ID must be exactly 3 characters." + " ***");
                    System.out.println("****************************************************************************************************************");
                    abortMission = true;
                    break;
                }
            }

            if (abortMission) {
                continue; // Skip the rest of the mission if there are issues with pod validation
            }

            Stack<String> container1 = new ArrayStack<>();
            Stack<String> container2 = new ArrayStack<>();
            Queue<String> corridor1 = new ArrayQueue<>();

            System.out.println("\n------------------------------\n");
            System.out.println("\nInitial container1 amount of pods: " + (container1.size() == 0 ? "Is empty" : container1.size()));
            System.out.println("\nInitial container2 amount of pods: " + (container2.size() == 0 ? "Is empty" : container2.size()));
            System.out.println("\n------------------------------\n");

            for(int k=0; k< sd.length; k++)
            {
                System.out.print(sd[k]+" ");
                if(container1.size()<9)
                {
                    container1.push(sd[k]);
                }
            }
            System.out.println("\n-------------------------------total pods------------------------------\n");


            System.out.println("\nLoading cargo to container 1 and sending to the ship!");

            // Load the first 9 elements into container1
            for (int i = 9; i < sd.length && container1.size() < 9; i++) {
                container1.push(sd[i]);
            }
            for (int k = 9; k < sd.length && container2.size() < 9; k++) {
                container2.push(sd[k]);

            }
            System.out.println("\ncontainer1 size: " + container1.size());
            System.out.println("container2 size: " + container2.size());
            System.out.println(container2.size() == 0
                    ? "There are no elements remaining to load into container2"
                    : "Preparing to load " + container2.size() + " elements into container2");

            System.out.println("\n------------------------------\n");
            System.out.println("\nShuttle has reached the space station, the container 1 has " +
                    (container1.size() == 0 ? "no pods" : container1.size() + " pods") +
                    ", container 2 has " +
                    (container2.size() == 0 ? "no pods" : container2.size() + " pods"));
            System.out.println("\nLoading pods into containers!" + container1.toString());



            System.out.println("\n===================");
            System.out.println("\nContainer1 size: " + (container1.size() == 0 ? "Is empty" : container1.size()));
            System.out.println("Container2 size: " + (container2.size() == 0 ? "Is empty" : container2.size()));
            System.out.println("\n===================\n");

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\nTransporting pods to the loading bay through the corridor.");
            System.out.println("\n----------------------------------------------------------\n");

            // Transfer elements from containers to the queue (corridor1)
            while (!container1.empty()) {
                corridor1.add(container1.pop());
            }

            System.out.println("\nCorridor contents: " + corridor1);

            while (!container2.empty()) {
                corridor1.add(container2.pop());
            }

            System.out.println("\nCorridor contents: " + corridor1);

            ArrayList<String> bay1 = new ArrayList<>();
            ArrayList<String> bay2 = new ArrayList<>();
            ArrayList<String> bay3 = new ArrayList<>();

            // Sort elements into the respective bays based on first character
            while (!corridor1.empty()) {
                String pod = corridor1.remove();
                       if (pod.charAt(0) == 'P') {
                    bay1.add(pod);
                } else if (pod.charAt(0) == 'F') {
                    bay2.add(pod);
                } else if (pod.charAt(0) == 'T') {
                    bay3.add(pod);
                }
            }

            // Print contents of each bay
            System.out.println("\n+++++++++++++++++++++++");
            System.out.println("\nCounting units in bay's");
            System.out.println("\nPersonal Bay: " + bay1);
            System.out.println("\nFood Bay: " + bay2);
            System.out.println("\nTechnological Bay: " + bay3);
        }
    }
}