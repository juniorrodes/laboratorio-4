package util;

import patient.Patient;
import services.PatientController;

import java.util.*;

public class UI {
    private static final int ADD_PERSON = 1;
    private static final int VACCINATE_NEXT = 2;
    private static final int DISPLAY_QUEUE = 3;
    private static final int DISPLAY_LIST = 4;
    private static final int QUIT = 5;

    private PatientController patientController;
    private Scanner in;

    public UI() {
        this.patientController = new PatientController();
    }

    public void menu() {
        do {
            this.printLine("MENU");
            this.printLine("[1] Add person to the queue.");
            this.printLine("[2] Vaccinate next person in line.");
            this.printLine("[3] Display patient queue.");
            this.printLine("[4] Display vaccinated list.");
            this.printLine("[5] Quit.");
            this.printLine("Choose your option: ");
        } while (this.checkSelection() != QUIT);
    }


    public byte checkSelection() {
        byte selection = this.getSelection();

        switch (selection) {
            case ADD_PERSON:
                this.addNewPatient();
                break;
            case VACCINATE_NEXT:
                this.vaccinateNextPerson();
                break;
            case DISPLAY_QUEUE:
                this.displayPatientsQueue();
                break;
            case DISPLAY_LIST:
                this.displayVaccinatedList();
                break;
            case QUIT:
                break;
            default:
                this.printLine("Please insert a valid option");
                break;
        }

        return selection;
    }

    private void addNewPatient() {
        String name = this.getStringValue("Enter the patient name: ");
        int age = this.getNumberValue("Enter the patient age:");

        if(!this.patientController.addPatient(new Patient(name, age))) {
            this.printLine("Failed to insert new patient, please try again.");
        }
    }

    private void vaccinateNextPerson() {
        Patient patient = this.patientController.vaccinatePatient();
        if (patient != null) {
            this.printLine("Patient vaccinated: ");
            this.printPatient(patient);
        } else {
            this.printLine("Patient queue is empty, no vaccine applied.");
        }
    }

    private void displayVaccinatedList() {
        Iterator<Patient> patientsIterator = this.patientController.getVaccinatedPatientsIterator();

        this.printLine("Current list of vaccinated patients:");
        while (patientsIterator.hasNext()) {
            Patient patient = patientsIterator.next();
            this.printPatient(patient);
        }
    }

    private void displayPatientsQueue() {
        PriorityQueue<Patient> patientQueue = this.patientController.getPatientPriorityQueue();

        this.printLine("Current patient queue:");
        while (!patientQueue.isEmpty()) {
            Patient patient = patientQueue.remove();
            this.printPatient(patient);
        }
    }

    private byte getSelection() {
        try {
            this.in = new Scanner(System.in);
            return in.nextByte();
        } catch (InputMismatchException ex) {
            return 0;
        }
    }

    public void printLine(String message) {
        System.out.println(message);
    }

    private void printPatient(Patient patient) {
        this.printLine(String.format("Name: %s, age: %d", patient.name(), patient.age()));
    }

    public String getStringValue(String message) {
        this.printLine(message);
        return this.in.next();
    }

    public int getNumberValue(String message) {
        try {
            this.printLine(message);
            return Integer.parseInt(this.in.next());
        } catch (NumberFormatException e) {
            this.printLine("Please insert a number: ");
            return this.getNumberValue(message);
        }
    }





}
