package services;

import patient.Patient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;


public class PatientController {

    PriorityQueue<Patient> patientPriorityQueue;
    ArrayList<Patient> vaccinatedPatientList;

    public PatientController() {
        PatientAgeComparator patientAgeComparator = new PatientAgeComparator();
        this.patientPriorityQueue = new PriorityQueue<Patient>(patientAgeComparator);
        this.vaccinatedPatientList = new ArrayList<Patient>();
    }

    public boolean addPatient(Patient newPatient) {
        if (newPatient == null) {
            return false;
        }
        return this.patientPriorityQueue.add(newPatient);
    }

    public Patient vaccinatePatient() {
        if (this.patientPriorityQueue.isEmpty()) {
            return null;
        }
        Patient vaccinatedPatient = this.patientPriorityQueue.remove();

        this.vaccinatedPatientList.add(vaccinatedPatient);
        return vaccinatedPatient;
    }

    public int getQueueSize() {
        return this.patientPriorityQueue.size();
    }

    public int getListSize() {
        return this.vaccinatedPatientList.size();
    }

    public PriorityQueue<Patient> getPatientPriorityQueue() {
        return new PriorityQueue<Patient>(this.patientPriorityQueue);
    }

    public Iterator<Patient> getVaccinatedPatientsIterator() {
        return vaccinatedPatientList.iterator();
    }
}
