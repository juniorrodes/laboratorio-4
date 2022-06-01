package services;

import patient.Patient;

import java.util.Comparator;

public class PatientAgeComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        return  Integer.compare(o2.age(), o1.age());
    }
}
