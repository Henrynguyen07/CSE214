import java.util.*;

/**
 * This class will hold the methods to manipulate the array of applicants (HiringTable). This
 * class includes adding, removing, getting, searching, and printing out the array of Applicants
 *
 * Author: Henry Nguyen
 * Email: Henry.Nguyen@stonybrook.edu
 * Stony Brook ID: 111484010
 * Recitation: 08
 **/

public class HiringTable implements Cloneable {
    // CONSTANTS

    private final static int MAX_SKILLS = 3;
    private final static int MAX_COMPANIES = 3;
    private final static int MAX_APPLICANTS = 50;

    Scanner input = new Scanner(System.in);
    // INSTANCE VARIABLES
    private Applicant[] data;
    private int applicantCounter = 0;

    // CONSTRUCTOR
    public HiringTable() {
        data = new Applicant[0];
    }

    // METHODS

    /**
     * Represents the amount of objects in the Applicant array
     *
     * @return
     *      Amount of valid applicant objects.
     *
     * <dt>Precondition:
     *      The HiringTable has been instantiated.
     */
    public int size() {
        return applicantCounter;
    }

    /**
     * Adds an applicant to the end of the list
     * @param newApplicant
     *      The new Applicant object to add to the list
     *
     * <dt>Preconditions:
     *      The Applicant object has been instantiated and the number of applicants in the list
     *      is less than the MAX_APPLICANTS
     *
     * <dt>Postcondition:
     *      New applicant is added at the end of the list.
     * @throws FullTableException
     *      Indicates that there the list is full and no more applicants can be added.
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException {
        try {
            if (applicantCounter < MAX_APPLICANTS) {
                data = Arrays.copyOf(data, size() + 1);
                data[size()] = newApplicant;
                applicantCounter++;
                System.out.println("Applicant " + newApplicant.getApplicantName() + " has been successfully added to the hiring system");
            } else {
                throw new FullTableException("Error - FullTableException: Indicates that there is no more room in the HiringTable for new Applicants");
            }
        } catch (FullTableException e) {
            System.out.println(e);
        }
    }

    /**
     * This will remove the applicant name from the HiringTable
     * @param name
     *      The name of the applicant to be removed
     *
     * <dt>Precondition:
     *      HiringTable is instantiated
     *
     * <dt>Postcondition:
     *      Applicant with user inputted name is removed from the list. Then applicants after the removed index is shifted down to remove
     *      any gaps.
     * @throws ApplicantNotFoundException
     *      Indications that the applicant with the given name was not found
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException {
        System.out.println();
        Applicant[] tempArray = new Applicant[size() - 1];
        try {
            if (indexName(name) >= 0) {
                System.arraycopy(data, 0, tempArray, 0, indexName(name));
                System.arraycopy(data, indexName(name) + 1, tempArray, indexName(name), data.length - indexName(name) - 1);
                data = new Applicant[size()];
                System.arraycopy(tempArray, 0, data, 0, tempArray.length);
                applicantCounter--;
                System.out.println("Applicant " + name + " has been successfully removed from the hiring system.");
            } else {
                throw new ApplicantNotFoundException
                        ("Error - ApplicantNotFoundException: Indications that the applicant with the given name was not found");
            }
        } catch (ApplicantNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * This will return the Applicant toString of user inputted index.
     * @param name
     *      User input of name to find
     * <dt>Precondition:
     *      HiringTable object has been instantiated.
     *
     * @return
     *      Applicant object in a Tabular form
     *
     * @throws ApplicantNotFoundException
     *      Indications that the applicant with the given name was not found
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException {
        try {
            if (indexName(name) >= 0) {
                return data[indexName(name)];
            } else {
                throw new ApplicantNotFoundException("Error - ApplicantNotFoundException: Indications that the applicant with the given name was not found");
            }
        } catch (ApplicantNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    /**i
     *  The refine search method will first check if any of the inputs are empty. If inputs are empty, filtering
     *  would not occur. If inputs are not empty, filtering would occur. For all cases, it will iterate through
     *  the array of applicants. To filter for company, it will iterate through the company array and compare it to
     *  the user inputted company filter. If its equal, applicant to be placed in a temporary array to then be filtered through.
     *  The process is repeated for skills, college and GPA. Time complexity will be O(n) because there is a limit of skills and
     *  company a applicant can have (which is 3) thus leaving O(3n).
     *
     * @param table
     *      The list of applicants
     * @param company
     *      User input company name to filter for
     * @param skill
     *      User input skill to filter for
     * @param college
     *      User input college name to filter for
     * @param GPA
     *      User input GPA minimum to filter for. Any GPA greater is also filtered
     *
     * <dt>Preconditions:
     *      HiringTable object has been instantiated.
     *
     * <dt>Postcondition:
     *      Display a tabular formatted table of each applicant filtered from the user inputs.
     */

    public static void refineSearch(HiringTable table, String company, String skill, String college, double GPA) {
        Applicant[] refinedApplicant = new Applicant[table.size()];
        System.out.println(String.format("%-40s%-25s%-15s%-25s%-25s", "Company Name", "Applicant", "GPA", "College", "Skills"));
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        Applicant[] app = table.getHiringTable().clone();

        if (!company.isEmpty()) {
            for (int i = 0; i < table.size(); i++) {
                int companyLength = app[i].getApplicantCompanyName().length;
                for (int j = 0; j < companyLength; j++) {
                    if (app[i].getApplicantCompanyName()[j] == (null)) {
                        refinedApplicant[i] = null;
                    } else if (!app[i].getApplicantCompanyName()[j].equalsIgnoreCase(company)) {
                        refinedApplicant[i] = null;
                    } else {
                        refinedApplicant[i] = app[i];
                        break;
                    }
                }
            }
        }

        if (!skill.isEmpty()) {
            for (int i = 0; i < table.size(); i++) {
                int skillLength = app[i].getApplicantSkills().length;
                for (int j = 0; j < skillLength; j++) {
                    if (app[i].getApplicantSkills()[j] == (null)) {
                        break;
                    } else if (!app[i].getApplicantSkills()[j].equalsIgnoreCase(skill)) {
                        refinedApplicant[i] = null;
                    } else {
                        refinedApplicant[i] = app[i];
                        break;
                    }
                }
            }
        }
        if (company.isEmpty() && skill.isEmpty() && GPA == 0) {
            for (int i = 0; i < table.size(); i++) {
                if (!app[i].getApplicantCollege().equalsIgnoreCase(college)) {
                    refinedApplicant[i] = null;
                } else {
                    refinedApplicant[i] = app[i];
                }
            }
        } else if (!college.isEmpty()) {
            for (int i = 0; i < table.size(); i++) {
                if (refinedApplicant[i] != null) {
                    if (!refinedApplicant[i].getApplicantCollege().equalsIgnoreCase(college)) {
                        refinedApplicant[i] = null;
                    } else {
                        refinedApplicant[i] = app[i];
                    }
                }
            }
        }

        if(company.isEmpty() && skill.isEmpty() && college.isEmpty()) {
            for (int i = 0; i < table.size(); i++) {
                if (app[i].getApplicantGPA() < GPA) {
                    refinedApplicant[i] = null;
                } else {
                    refinedApplicant[i] = app[i];
                }
            }
        } else if (GPA > 0) {
            for (int i = 0; i < table.size(); i++) {
                if (refinedApplicant[i] != null) {
                    if (refinedApplicant[i].getApplicantGPA() < GPA) {
                        refinedApplicant[i] = null;
                    } else {
                        refinedApplicant[i] = app[i];
                    }
                }
            }
        }


        for (int i = 0; i < app.length; i++) {
            if (refinedApplicant[i] != null) {
                System.out.println(String.format("%-40s%-25s%-15s%-25s%-25s", refinedApplicant[i].stringCompany(),
                        refinedApplicant[i].getApplicantName(), Double.toString(refinedApplicant[i].getApplicantGPA()), refinedApplicant[i].getApplicantCollege(),
                        refinedApplicant[i].stringSkill()));
            }
        }
    }

    /**
     * This method will print All applicants in the HiringTable in a tabular form.
     */
    public void printApplicantTable() {
        System.out.println(String.format("%-40s%-25s%-15s%-25s%-25s", "Company Name", "Applicant", "GPA", "College", "Skills"));
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < size(); i++) {
            System.out.println(String.format("%-40s%-25s%-15s%-25s%-25s", data[i].stringCompany(),
                    data[i].getApplicantName(), Double.toString(data[i].getApplicantGPA()), data[i].getApplicantCollege(),
                    data[i].stringSkill()));
        }
    }

    /**
     * This method will create a deep clone of the object HiringTable
     *
     * @return
     *      A Deep clone of HiringTable called "Clone"
     */
    public Object clone() {
        HiringTable cloned;
        try {
            cloned = (HiringTable) super.clone();
        } catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        cloned.data = data.clone();
        cloned.applicantCounter = applicantCounter;
        System.out.println("Successfully Created Backup");
        System.out.println();
        return cloned;
    }

    /**
     * This method will get the Array of Applicants
     * @return
     *      Array of applicants "Data"
     */
    public Applicant[] getHiringTable() {
        return data;
    }

    /**
     *
     * @param name
     *      User inputted name
     * @return
     *      Index of name in the Array of Applicants
     */
    public int indexName(String name) {
        int index = 0;
        for (int x = 0; x < size(); x++) {
            if (data[x].getApplicantName().equalsIgnoreCase(name)) {
                index = x;
                break;
            } else {
                index = -1;
            }
        }
        return index;
    }

    /**
     * @return
     *      Returns the maximum number of skills
     */
    public int getMaxSkills() {
        return MAX_SKILLS;
    }

    /**
     * @return
     *      Returns the maximum number of companies
     */
    public int getMaxCompanies() {
        return MAX_COMPANIES;
    }
}
