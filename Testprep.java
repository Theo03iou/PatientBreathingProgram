import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class patient {
    int[] dayNumber = new int[32];
    int[] peakFlows = new int[32];
}

public class Testprep {
    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        patient newPatient = new patient();
        final String dailyQ = "What is your prescribed dangerous peak level?";
        System.out.println(dailyQ);

        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();

        for (int i = 0; i < 31; i++) {
            while (limit != 0 ) {
                takeReading(limit, newPatient);
            }
        }
        scanner.close();
    }

    private static void takeReading(int limit, patient newPatient) throws IOException  {
        Scanner scanner = new Scanner(System.in);
        int dailyreadings;
        int newPeak = 0;
        
        System.out.println("What day is it today? E.G. 22, 4, 3, 2");
        int currentDate = scanner.nextInt();

        if (currentDate <= 31) {

        for (int i = 0; i < 3; i++) {
            int t = i + 1;
            System.out.println("[" + t + "] What is your reading");
            int currentReading = scanner.nextInt();
            dailyreadings = currentReading;

            if (dailyreadings == 0) {
                terminate(newPatient);

            } else if (dailyreadings > 801) {
                System.out.println("invalid input, please enter a number between 1 and 100");
                takeReading(limit, newPatient);
            }
            else if (dailyreadings <= limit) {
                String message = goToHospital();
                System.out.println(message);
              } else if (dailyreadings > newPeak) {
                newPeak = dailyreadings;
            } 
        }

        // System.out.println(newPeak);
        setDay(currentDate, newPatient, newPeak);
        setPeak(newPatient, newPeak, currentDate);
        

        // System.out.println(getDay(newPatient, currentDate));
        // System.out.println(getPeak(newPatient, currentDate));

    }
      
}
        // Setters for peak

    private static void setPeak(patient newPatient, int newPeak, int currentDate) {
        newPatient.peakFlows[currentDate] = newPeak;
    }

    private static int getPeak(patient newPatient, int currentDate) {
        return newPatient.peakFlows[currentDate];
    }

        // Setters for date 

    private static void setDay(int currentDate, patient newPatient, int newPeak) {
        newPatient.dayNumber[currentDate] = currentDate;
    }

    private static int getDay(patient newPatient, int currentDate) {
        return newPatient.dayNumber[currentDate];
    }

    
    private static String goToHospital() {
        return "You need to go to hospital";
    }

    private static void terminate(patient newPatient) throws IOException {        

        BufferedWriter inputStream = new BufferedWriter(new FileWriter("text1.txt"));

        for (int i = 0; i < newPatient.dayNumber.length; i++) {
            System.out.println(getDay(newPatient, i));
            System.out.println(getPeak(newPatient, i));

            inputStream.write(getDay(newPatient, i) + "," + getPeak(newPatient, i) + "\n");
        }

        inputStream.close();
        System.exit(0);
    }

}

