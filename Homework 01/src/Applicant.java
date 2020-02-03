
/**
 * The Applicant class will holds the attributes of being an "Applicant" for hiringTable object.
 * Paramemters inlcude: companyName, applicantName, applicantGPA, applicantCollege, applicantSKills, and inputValid
 *
 *
 * @Author: Henry Nguyen
 *      Email: Henry.Nguyen@stonybrook.edu
 *      Stony Brook ID: 111484010
 *      Recitation: 08
 **/

public class Applicant implements Cloneable {
    //INSTANCE VARIABLES
    /**
     * @param companyName
     *      companyName[i] represents company name.
     * @param applicantName
     *      Represents applicant Name
     * @param applicantGPA
     *      Represents applicant GPA
     * @param applicantCollege
     *      Represents applicant college
     * @param applicantSkills
     *      applicantSkills[i] represents applicant skills.
     */
    private String[] companyName;
    private String applicantName;
    private double applicantGPA;
    private String applicantCollege;
    private String[] applicantSkills;
    private boolean inputValid = false;


    // CONSTRUCTOR FOR APPLICANT
    public Applicant() {

    }
    /**
     * Returns an instance of Applicant
     *
     * @param companyName
     *      The company name of the applicant
     * @param applicantName
     *      The name of the applicant
     * @param applicantGPA
     *      The GPA of the applicant
     * @param applicantCollege
     *      The college attended of the applicant
     * @param applicantSkills
     *      The skills of the applicant
     *
     * <dt>Precondition:
     *      The parameters of each input should have at least 1 argument inputted
     *      If any input is empty, an exception will be thrown.
     *
     * @exception IllegalArgumentException
     *      Indicates that an input for the applicant is invalid (Empty input or GPA is
     *      less than or equal to 0 or greater than 4.
     */

    public Applicant(String[] companyName, String applicantName, double applicantGPA, String applicantCollege, String[] applicantSkills) {
        setApplicantCompanyName(companyName);
        setApplicantName(applicantName);
        setApplicantGPA(applicantGPA);
        setApplicantCollege(applicantCollege);
        setApplicantSkills(applicantSkills);

    }

    // GETTERS AND SETTERS
    /**
     * @return
     *      The array of Applicant companies.
     */
    public String[] getApplicantCompanyName() {
        return companyName;
    }

    /**
     * @return
     *      The applicant name of Applicant.
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * @return
     *      The applicant GPA
     */
    public double getApplicantGPA() {
        return applicantGPA;
    }

    /**
     * @return
     *      The applicant college
     */
    public String getApplicantCollege() {
        return applicantCollege;
    }

    /**
     * @return
     *      The skills of applicant
     */
    public String[] getApplicantSkills() {
        return applicantSkills;
    }

    /**
     * @param inputCompanyName
     *      sets the Applicant object company name to the user inputted company name.
     * <dt>Precondition:
     *      User input has at least 1 input.
     * @exception IllegalArgumentException
     *      Thrown when a user inputs empty for company
     */
    public void setApplicantCompanyName(String[] inputCompanyName) throws IllegalArgumentException {
        if (inputCompanyName[0] == null) {
            throw new IllegalArgumentException("Error: Company cant be empty");
        } else {
            this.companyName = inputCompanyName;
        }
    }

    /**
     * @param inputApplicantName
     *      sets the Applicant object name
     *
     * <dt>Preconditions:
     *      User input is not blank.
     *
     * @exception IllegalArgumentException
     *      Thrown when a user inputs empty for Applicant name
     */
    public void setApplicantName(String inputApplicantName) throws IllegalArgumentException {
        if (inputApplicantName.isEmpty()) {
            throw new IllegalArgumentException("Error: Name cant be empty");
        }
        this.applicantName = inputApplicantName;
    }

    /**
     * @param inputApplicantGPA
     *      Sets the Applicant object GPA
     *
     * <dt>Precondition:
     *      GPA is > 0 and <= 4
     *
     * @exception IllegalArgumentException
     *      Thrown when GPA is less than or equal to 0 or greater than 4
     */
    public void setApplicantGPA(double inputApplicantGPA) throws IllegalArgumentException {
        if (inputApplicantGPA <= 0 || inputApplicantGPA > 4) {
            throw new IllegalArgumentException("Error: GPA cannot be less than or equal to 0 or greater than 4");
        }
        this.applicantGPA = inputApplicantGPA;
    }

    /**
     * @param inputApplicantCollege
     *      Sets the Applicant object college
     *
     * <dt>Precondition:
     *      User input is not blank.
     *
     * @exception IllegalArgumentException
     *      Thrown when user inputs empty for Applicant college
     */
    public void setApplicantCollege(String inputApplicantCollege) throws IllegalArgumentException {
        if (inputApplicantCollege.isEmpty()) {
            throw new IllegalArgumentException("Error: Applicant college cant be empty");
        }
        this.applicantCollege = inputApplicantCollege;
    }

    /**
     * @param inputApplicantSkills
     *      Sets the Applicant Object skill
     *
     * <dt>Precondition:
     *      User input has at least 1 skill.
     *
     * @exception IllegalArgumentException
     *      Thrown when user inputs empty skills for Applicant
     */
    public void setApplicantSkills(String[] inputApplicantSkills) throws IllegalArgumentException {
        if (inputApplicantSkills[0] == null) {
            throw new IllegalArgumentException("Error: Applicant skills cant be empty");
        }
        this.applicantSkills = inputApplicantSkills;
    }

    // METHODS
    /**
     * This will deep clone the current Applicant object. Manually cloning each attribute
     * of object applicant
     * @return
     *      A new object called "cloned"
     */
    @Override
    public Object clone() {
        Applicant cloned = new Applicant();
        cloned.setApplicantCompanyName(this.getApplicantCompanyName());
        cloned.setApplicantCollege(this.getApplicantCollege());
        cloned.setApplicantGPA(this.getApplicantGPA());
        cloned.setApplicantName(this.getApplicantName());
        cloned.setApplicantSkills(this.getApplicantSkills());
        return cloned;
    }

    /**
     * The equals method would check if its an instance of object "Applicant"
     * then would compare each element of applicant individually and return
     * either true or false if all the elements are the same or not.
     *
     * @param obj
     *      Is the comparing object
     * @return
     *      True: If Applicant and object is equal
     *      False: If Applicant and object is not equal
     */

    public boolean equals(Object obj) {
        if (obj instanceof Applicant) {
            Applicant temp = (Applicant) obj;
            return (temp.getApplicantName() == this.getApplicantName() && temp.getApplicantCompanyName() == this.getApplicantCompanyName() &&
                    temp.getApplicantCollege() == this.getApplicantCollege() && temp.getApplicantGPA() == this.getApplicantGPA() && temp.getApplicantSkills() == this.getApplicantSkills());
        } else {
            return false;
        }
    }

    /**
     * Outputs all of the Applicant attributes and printing in a tabular form.
     * @return
     *      Returns the String of Applicant Object.
     */

    public String toString() {
        return ("\nApplicant Name: " + this.getApplicantName() + "\n" +
                "Applicant Applying From: " + stringCompany() + "\n" +
                "Applicant GPA: " + this.getApplicantGPA() + "\n" +
                "Applicant College: " + this.getApplicantCollege() + "\n" +
                "Applicant Skills: " + stringSkill()) + "\n";
    }

    /**
     * Will convert the company array to a String removing "[", "]" and null.
     * @return
     *      A string of the array as shown in the example outputs.
     */
    // Converting Array to String Format
    public String stringCompany() {
        StringBuilder string = new StringBuilder();
        for (String x : getApplicantCompanyName()) {
            if (x != null) {
                string.append(x);
                string.append(", ");
            }
        }
        if (string.toString().isEmpty()) {
            return string.toString();
        } else {
            return string.deleteCharAt(string.length() - 2).toString();
        }
    }

    /**
     * Will convert the skill array to a String removing "[", "]" and null.
     * @return
     *      A string of the array as shown in the example outputs.
     */

    public String stringSkill() {
        StringBuilder string = new StringBuilder();
        for (String x : getApplicantSkills()) {
            if (x != null) {
                string.append(x);
                string.append(", ");
            }
        }
        if (string.toString().isEmpty()) {
            return string.toString();
        } else {
            return string.deleteCharAt(string.length() - 2).toString();
        }
    }
}

