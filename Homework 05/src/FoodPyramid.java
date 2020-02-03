/**
 * FoodPyramid class will be the main driver of this program. Contains a member of variable named tree that
 * interacts with the user. Upon running, user will be asked to put apex predator which cannot be changed.
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.util.*;

public class FoodPyramid {
    /**
     * @param tree
     *  Holds a reference of OrganismTree
     */
    OrganismTree tree;

    public FoodPyramid() {

    }

    public static void main(String[] args) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
        /**
         * @param node
         *      holds reference of nodes
         * @param nameInput
         *      Holds reference of user input of node name
         * @param organismClass
         *      Holds reference of user input being either H/C/O
         * @param selection
         *      Menu Selection
         *
         */
        OrganismTree tree = null;
        OrganismNode node = null;
        String nameInput;
        String organismClass;
        Scanner input = new Scanner(System.in);
        System.out.print("What is the name of the Apex Predator?: ");
        nameInput = input.nextLine();
        System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
        organismClass = String.valueOf(input.nextLine().charAt(0));

        System.out.println();
        if (organismClass.equalsIgnoreCase("H"))
            node = new OrganismNode(nameInput, true, false);
        else if (organismClass.equalsIgnoreCase("C"))
            node = new OrganismNode(nameInput, false, true);
        else if (organismClass.equalsIgnoreCase("O"))
            node = new OrganismNode(nameInput, true, true);
        tree = new OrganismTree(node);

        System.out.println("Constructing Food Pyramid...\n");
        System.out.println("Menu: \n(PC) - Create New Plant Child \n(AC) - Create New Animal Child \n(RC) - Remove Child \n(P) - Print Out Cursor's Prey \n" +
                "(C) - Print Out Food Chain \n(F) - Print Out Food Pyramid at Cursor \n(LP) - List All Plants Supporting Cursor \n(R) - Reset Cursor to Root \n(M) - Move Cursor to Child \n(Q) - Quit");
        while (true) {
            String selection;
            System.out.print("\nPlease Select an Option: ");
            selection = input.nextLine();
            System.out.println();
            selection = selection.toUpperCase();
            switch (selection) {
                case "PC":
                    if (!tree.getCursor().getPlant() && tree.getCursor().isHerbivore() || !tree.getCursor().getPlant() && tree.getCursor().isOmnivore() ) {
                        System.out.print("What is the name of the organism?: ");
                        nameInput = input.nextLine();
                        tree.addPlantChild(nameInput);
                    } else {
                        System.out.println("Error: The cursor is at a plant node. Plants cannot be predators");
                    }
                    break;
                case "AC":
                    if (tree.getCursor().isHerbivore()) {
                        System.out.println("Error: This prey cannot be added as it does not match the diet of the predator");
                    } else if (tree.getCursor().getPlant()) {
                        System.out.println("This prey cannot be added as it does not match the diet of the predator");
                    } else if (tree.getCursor().isOmnivore() || tree.getCursor().isCarnivore()) {
                        System.out.print("What is the name of the organism?: ");
                        nameInput = input.nextLine();
                        System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
                        organismClass = input.nextLine();
                        if (organismClass.equalsIgnoreCase("H"))
                            tree.addAnimalChild(nameInput, true, false);
                        else if (organismClass.equalsIgnoreCase("C"))
                            tree.addAnimalChild(nameInput, false, true);
                        else if (organismClass.equalsIgnoreCase("O"))
                            tree.addAnimalChild(nameInput, true, true);
                        else {
                            System.out.println("Error: Organism has to be either a herbivore / a carnivore / or an omnivore");
                        }
                    }
                    break;
                case "RC":
                    System.out.print("What is the name of the organism to be removed?: ");
                    nameInput = input.nextLine();
                    tree.removeChild(nameInput);
                    break;
                case "P":
                    System.out.println(tree.listPrey());
                    break;
                case "C":
                    System.out.println(tree.listFoodChain().substring(0,tree.listFoodChain().lastIndexOf("->")));
                    break;
                case "F":
                    tree.printOrganismTree();
                    break;
                case "LP":
                    tree.listAllPlants();
                    break;
                case "R":
                    tree.cursorReset();
                    break;
                case "M":
                    System.out.print("Move to?: ");
                    nameInput = input.nextLine();
                    tree.moveCursor(nameInput);
                    break;
                case "Q":
                    System.out.println("Program terminating successfully...\n");
                    System.exit(0);

                default:
                    System.out.println("Error: Not a Option");
            }
        }
    }

}
