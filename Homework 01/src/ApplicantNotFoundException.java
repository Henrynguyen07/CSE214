/**
 * Custom exception thrown then Applicant not found in the HiringTable class.
 *
 * Author: Henry Nguyen
 * Email: Henry.Nguyen@stonybrook.edu
 * Stony Brook ID: 111484010
 * Recitation: 08
 **/

public class ApplicantNotFoundException extends Exception {
    // Parameterized Constructor
    public ApplicantNotFoundException(String error) {
        super (error);
    }
}
