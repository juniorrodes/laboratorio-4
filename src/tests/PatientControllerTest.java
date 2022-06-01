package tests;

// import JUnit packages
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// import service related packages
import patient.Patient;
import services.PatientController;

public class PatientControllerTest {
    private PatientController patientController;

    @Before
    public void setUp() {
        this.patientController = new PatientController();
    }

    @Test
    public void testFailToInsertNullPatient() {
        assertFalse(this.patientController.addPatient(null));
    }

    @Test
    public void testInsertOnePatient() {
        int queueSize = this.patientController.getQueueSize();
        assertTrue(this.patientController.addPatient(new Patient("José", 22)));
        assertEquals(this.patientController.getQueueSize(), queueSize + 1);
    }

    @Test
    public void testVaccinatePatient() {
        Patient vaccinatedPatient = new Patient("Vendelino", 80);
        this.patientController.addPatient(new Patient("José", 22));
        this.patientController.addPatient(vaccinatedPatient);
        this.patientController.addPatient(new Patient("João", 25));

        int patientListSize = this.patientController.getListSize();
        int patientQueueSize = this.patientController.getQueueSize();

        assertEquals(this.patientController.vaccinatePatient(), vaccinatedPatient);
        assertEquals(this.patientController.getListSize(), patientListSize + 1);
        assertEquals(this.patientController.getQueueSize(), patientQueueSize - 1);
    }
}
