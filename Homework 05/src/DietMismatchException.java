/**
 * Exception class when diets are mismatched.
 * Omnivore can eat both plants and animals
 * Carnivore can only eat animals
 * Herbivore can only eat animals
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */


public class DietMismatchException extends Exception {
    public DietMismatchException (String message) {
        super(message);
    }
}
