package aPackage;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static QueueInterface<Patient> normalQueue = new LinkedQueue<>();
    private static PriorityQueueInterface<Patient> emergencyQueue = new PriorityQueueArrayBased<>();
    private static StackInterface<String> diagnosisHistory = new ArrayStack<>();
    private static DequeInterface<Patient> patientRecords = new LinkedDeque<>();
    private static MaxHeapInterface<Integer> roomHeap = new MaxHeap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Mark Patient as Emergency");
            System.out.println("3. Diagnose Patient");
            System.out.println("4. Undo Last Diagnosis");
            System.out.println("5. View First and Last Registered Patient");
            System.out.println("6. Assign Room to Patient");
            System.out.println("7. Discharge Patient");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    markAsEmergency(scanner);
                    break;
                case 3:
                    diagnosePatient(scanner);
                    break;
                case 4:
                    undoDiagnosis();
                    break;
                case 5:
                    viewPatientRecords();
                    break;
                case 6:
                    assignRoom();
                    break;
                case 7:
                    dischargePatient(scanner);
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter symptoms: ");
        String symptoms = scanner.nextLine();
        Patient newPatient = new Patient(name, symptoms, 5); // Default normal priority
        normalQueue.enqueue(newPatient);
        patientRecords.addToBack(newPatient);
        System.out.println("Patient registered.");
    }

    private static void markAsEmergency(Scanner scanner) {
        System.out.print("Enter patient name to mark as emergency: ");
        String name = scanner.nextLine();
        boolean found = false;

        int size = ((LinkedQueue<Patient>) normalQueue).getSize();
        for (int i = 0; i < size; i++) {
            Patient patient = normalQueue.dequeue();
            if (patient.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter priority level (1=Critical, 2=Serious, 3=Stable): ");
                int priority = scanner.nextInt();
                scanner.nextLine();
                Patient emergencyPatient = new Patient(patient.getName(), patient.getSymptoms(), priority);
                emergencyQueue.add(emergencyPatient);
                found = true;
                break;
            } else {
                normalQueue.enqueue(patient);
            }
        }

        if (found)
            System.out.println("Patient moved to emergency queue.");
        else
            System.out.println("Patient not found.");
    }

    private static void diagnosePatient(Scanner scanner) {
        System.out.print("Enter diagnosis for latest patient: ");
        String diagnosis = scanner.nextLine();
        diagnosisHistory.push(diagnosis);
        System.out.println("Diagnosis recorded.");
    }

    private static void undoDiagnosis() {
        if (diagnosisHistory.isEmpty()) {
            System.out.println("No diagnosis to undo.");
        } else {
            String undone = diagnosisHistory.pop();
            System.out.println("Undid diagnosis: " + undone);
        }
    }

    private static void viewPatientRecords() {
        if (patientRecords.isEmpty()) {
            System.out.println("No patient records.");
        } else {
            System.out.println("First Patient: " + patientRecords.getFront());
            System.out.println("Last Patient: " + patientRecords.getBack());
        }
    }

    private static void assignRoom() {
        if (roomHeap.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            int assignedRoom = roomHeap.removeMax();
            System.out.println("Assigned Room: " + assignedRoom);
        }
    }

    private static void dischargePatient(Scanner scanner) {
        System.out.print("Enter room number to free: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        roomHeap.add(roomNumber);
        System.out.println("Room " + roomNumber + " is now available.");
    }

    private static void initializeRooms() {
        for (int i = 101; i <= 110; i++) {
            roomHeap.add(i);
        }
    }
}