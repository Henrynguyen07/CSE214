/**
 * Custom exception thrown when the array Of Applicants is full in the HiringTable class
 *
 * Author: Henry Nguyen
 * Email: Henry.Nguyen@stonybrook.edu
 * Stony Brook ID: 111484010
 * Recitation: 08
 **/

public class FullTableException extends Exception {
    // Parameterized Constructor
    public FullTableException(String error) {
        super (error);
    }
}
