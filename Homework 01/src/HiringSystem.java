import java.util.Scanner;

/**
 * This class will be the driver for the project. Using classes HiringTable and
 * Applicant, it will be calling methods and obtaining user input. It will also
 * be catching exceptions.
 *
 * Author: Henry Nguyen
 * Email: Henry.Nguyen@stonybrook.edu
 * Stony Brook ID: 111484010
 * Recitation: 08
 **/

public class HiringSystem {

    public static void main(String[] args) throws FullTableException, ApplicantNotFoundException, IllegalArgumentException {
        HiringTable hiringTable = new HiringTable();
        Scanner input = new Scanner(System.in);
        HiringTable clonedTable = null;

        // Variables
        int counter = 0;
        int companyCounter;
        int skillsCounter;
        boolean flagCounter = false;
        boolean stopFlag = false;

        System.out.println("----------- Welcome to BookFace -----------");
        System.out.println("-------------------------------------------");
        while (stopFlag == false) {
            // UI MENU
            System.out.println("(A) Add Applicant");
            System.out.println("(R) Remove Applicant");
            System.out.println("(G) Get Applicant");
            System.out.println("(P) Print List");
            System.out.println("(RS) Refine Search");
            System.out.println("(S) Size");
            System.out.println("(B) Backup");
            System.out.println("(CB) Compare Backup");
            System.out.println("(RB) Revert Backup");
            System.out.println("(Q) Quit");
            System.out.println();
            System.out.print("Please enter a command: ");
            String command = input.nextLine();
            switch (command.toUpperCase()) {
                case "A":
                    String tempApplicantName;
                    double tempApplicantGPA;
                    String tempApplicantCollege;
                    String tempCompany;
                    String tempSkill;
                    String[] tempApplicantCompanies = new String[hiringTable.getMaxCompanies()];
                    String[] tempApplicantSkills = new String[hiringTable.getMaxSkills()];
                    Applicant newApplicant = new Applicant();

                    System.out.print("Enter Applicant Name: ");
                    tempApplicantName = input.nextLine();
                    try {
                        newApplicant.setApplicantName(tempApplicantName);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                        System.out.println();
                        break;
                    }

                    System.out.print("Enter Applicant GPA: ");
                    tempApplicantGPA =input.nextDouble();
                    input.nextLine();
                    try {
                        newApplicant.setApplicantGPA(tempApplicantGPA);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                        System.out.println();
                        break;
                    }

                    System.out.print("Enter Applicant College: ");
                    tempApplicantCollege = input.nextLine();
                    try {
                        newApplicant.setApplicantCollege(tempApplicantCollege);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                        System.out.println();
                        break;
                    }

                    counter = 0;
                    flagCounter = false;
                    companyCounter = hiringTable.getMaxCompanies();

                    // This will add all the company inputs to an array
                    while (flagCounter == false && counter < hiringTable.getMaxCompanies()) {
                        System.out.print("Enter up to " + companyCounter + " Companies: ");
                        tempCompany = input.nextLine();
                        if (!tempCompany.isEmpty()) {
                            tempApplicantCompanies[counter] = tempCompany;
                            counter++;
                            companyCounter--;
                        } else {
                            flagCounter = true;
                        }
                    }
                    try {
                        newApplicant.setApplicantCompanyName(tempApplicantCompanies);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                        System.out.println();
                        break;
                    }

                    counter = 0;
                    flagCounter = false;
                    skillsCounter = hiringTable.getMaxSkills();

                    // This will add all the skill inputs to an array
                    while (flagCounter == false && counter < hiringTable.getMaxSkills()) {
                        System.out.print("Enter up to " + skillsCounter + " Skills: ");
                        tempSkill = input.nextLine();
                        if (!tempSkill.isEmpty()) {
                            tempApplicantSkills[counter] = tempSkill;
                            counter++;
                            skillsCounter--;
                        } else {
                            flagCounter = true;
                        }
                    }
                    try {
                        newApplicant.setApplicantSkills(tempApplicantSkills);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                        System.out.println();
                        break;
                    }
                    hiringTable.addApplicant(newApplicant);
                    System.out.println();
                    break;
                case "R":
                    System.out.print("Enter applicant name: ");
                    hiringTable.removeApplicant(input.nextLine());
                    System.out.println();
                    break;
                case "G":
                    System.out.print("Enter applicant Name: ");
                    System.out.println(hiringTable.getApplicant(input.nextLine()));
                    break;
                case "P":
                    hiringTable.printApplicantTable();
                    System.out.println();
                    break;
                case "RS":
                    String companyFilter;
                    String skillFilter;
                    String collegeFilter;
                    String gpaFilter;
                    System.out.print("Enter a company to filter for: ");
                    companyFilter = input.nextLine();
                    System.out.print("Enter a skill to filter for: ");
                    skillFilter = input.nextLine();
                    System.out.print("Enter a college to filter for: ");
                    collegeFilter = input.nextLine();
                    System.out.print("Enter the minimum GPA to filter for: ");
                    gpaFilter = input.nextLine();
                    if (gpaFilter.isEmpty()) {
                        gpaFilter = "0";
                    }
                    System.out.println();
                    hiringTable.refineSearch(hiringTable,companyFilter, skillFilter, collegeFilter, Double.valueOf(gpaFilter));
                    System.out.println();
                    break;
                case "S":
                    System.out.println("There are " + hiringTable.size() + " applicants in the hiring system");
                    System.out.println();
                    break;
                case "B":
                    clonedTable = (HiringTable) hiringTable.clone();
                    break;
                case "CB":
                    boolean compareValue = false;
                    if (hiringTable.getHiringTable().length == clonedTable.getHiringTable().length) {
                        for (int x = 0; x < hiringTable.size(); x++) {
                            if (hiringTable.getHiringTable()[x].equals(clonedTable.getHiringTable()[x])) {
                                compareValue = true;
                            } else {
                                compareValue = false;
                                break;
                            }
                        }
                    } else {
                        compareValue = false;
                    }
                    if (compareValue == true) {
                        System.out.println("Current list is the same as the backup copy.");
                    } else {
                        System.out.println("Current list is NOT the same as the backup copy.");
                    }
                    System.out.println();
                    break;
                case "RB":
                    System.out.println("\nSuccessfully reverted to the backup copy");
                    hiringTable = clonedTable;
                    System.out.println();
                    break;
                case "Q":
                    System.out.println("Quitting Program ...");
                    stopFlag = true;
                    break;
            }
        }
    }
}
