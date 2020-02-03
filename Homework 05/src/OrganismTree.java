/**
 * OrganismTree class will implement the ternary one way tree which contains OrganismNode objects.
 * Tree will inlcude operations such as insertion removal, printing, moving the cursor reference and more;
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class OrganismTree {
    /**
     * @param root
     *      Holds apexPredator
     * @param cursor
     *      Holds reference of cursor
     * @param result
     *      Holds string reslts
     */
    private OrganismNode root;
    private OrganismNode cursor;
    private String result;

    /**
     * Creates instance of OrganismTree with User input.
     * @param apexPredator
     */
    public OrganismTree(OrganismNode apexPredator) {
        try {
            if (!apexPredator.getPlant()) {
                this.root = apexPredator;
                this.cursor = root;
            } else {
                throw new IllegalArgumentException("Error: Plant cant be Apex Predator");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns current node pointed from the cursor
     * @return
     */
    public OrganismNode getCursor() {
        return cursor;
    }

    /**
     * Sets cursor back to apexPredator (root)
     */
    public void cursorReset() {
        cursor = root;
        System.out.println("Cursor successfully reset to root!");

    }

    /**
     * Will check childrens of the nodes and check if name matches user input. If neither name matches
     * exception is thrown if name does not exist
     * @param name
     * @throws IllegalArgumentException
     */
    public void moveCursor(String name) throws IllegalArgumentException {
        try {
            if (root == null)
                throw new IllegalArgumentException("Error: Tree is  empty");
            else if (cursor.getLeft() != null && cursor.getLeft().getName().equalsIgnoreCase(name) || cursor.getMiddle() != null && cursor.getMiddle().getName().equalsIgnoreCase(name)
                    || cursor.getRight() != null && cursor.getRight().getName().equalsIgnoreCase(name)) {
                if (cursor.getLeft().getName().equalsIgnoreCase(name))
                    cursor = cursor.getLeft();
                else if (cursor.getMiddle().getName().equalsIgnoreCase(name))
                    cursor = cursor.getMiddle();
                else if (cursor.getRight().getName().equalsIgnoreCase(name))
                    cursor = cursor.getRight();
                System.out.println("Cursor successfully moved to " + name + "!");
            } else {
                throw new IllegalArgumentException("Error: This prey does not exist for this predator.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Lists all of its children of the node pointed from the cursor. Cursor is not moved
     * @return
     * @throws IsPlantException
     */
    public String listPrey() throws IsPlantException {
        String result = "";
        try {
            if (!cursor.getPlant()) {
                result = cursor.getName();
                if (cursor.getLeft() != null)
                    result = result + " -> " + cursor.getLeft().getName();
                if (cursor.getMiddle() != null)
                    result = result + ", " + cursor.getMiddle().getName();
                if (cursor.getRight() != null)
                    result = result + ", " + cursor.getRight().getName();
            } else
                throw new IsPlantException("Error: Cursor currently references a plant object");
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * Lists the path from the root to the cursor.
     * @return
     */
    // Gives a List of Nodes Children
    public String listFoodChain() {
        this.result = "";
        printPath(root,cursor);
        return this.result;

    }

    /**
     * Prints the tree from the cursor and below. This is formed in a layered indented tree in preorder traversal
     */
    public void printOrganismTree() {
        printTree(cursor, "");
    }

    /**
     * Prints all the plants currently at the cursor and beneath cursor
     */
    public void listAllPlants() {
        this.result = "";
        printPlantTree(cursor);
        System.out.println(this.result.substring(0, this.result.lastIndexOf(",")));
    }

    /**
     * Adds animal to the next available children at the cursor. If no node is available, exception is thrown
     * @param name
     *      Holds reference of node name
     * @param isHerbivore
     *      Holds boolean value if its a herbivore
     * @param isCarnivore
     *      Holds boolean value if its a carnivore
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     * @throws IsPlantException
     * @throws DietMismatchException
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws
            IllegalArgumentException, PositionNotAvailableException, IsPlantException, DietMismatchException {
        try {
            cursor.addPrey(new OrganismNode(name, isHerbivore, isCarnivore));
            System.out.println("A(n) " + name + " has successfully been added as prey for the " + cursor.getName() + "!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Creates a new plant node with user input name and adds it as a child of the cursor node
     * @param name
     *      Holds name of node
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     */
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException {
        try {
            OrganismNode plantNode = new OrganismNode(name, false, false);
            if (cursor.getPlant())
                throw new IllegalArgumentException("Error: The cursor is at a plant node. Plants cannot be predators.");
            else {
                if (cursor.getLeft() != null && cursor.getLeft().getName().equalsIgnoreCase(name) || cursor.getMiddle() != null && cursor.getMiddle().getName().equalsIgnoreCase(name) || cursor.getRight() != null && cursor.getRight().getName().equalsIgnoreCase(name))
                    throw new IllegalArgumentException("Error: This prey already exists for this predator.");
                else {
                    if (cursor.getLeft() == null) {
                        System.out.println(name + " has successfully been added as prey for the " + cursor.getName());
                        cursor.setLeft(plantNode);
                    } else if (cursor.getMiddle() == null) {
                        System.out.println(name + " has successfully been added as prey for the " + cursor.getName());
                        cursor.setMiddle(plantNode);
                    } else if (cursor.getRight() == null) {
                        System.out.println(name + " has successfully been added as prey for the " + cursor.getName());
                        cursor.setRight(plantNode);
                    } else {
                        throw new PositionNotAvailableException("Error: Positions is not available");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Removes the child node of cursor with name name, and properly shifts the deleted node’s other siblings if necessary. If the deleted node has any descendants, those nodes are deleted as well.
     * @param name
     * @throws IllegalArgumentException
     */
    public void removeChild(String name) throws IllegalArgumentException {
        try {
            if (cursor.getLeft() != null && cursor.getLeft().getName().equalsIgnoreCase(name) || cursor.getMiddle() != null && cursor.getMiddle().getName().equalsIgnoreCase(name) || cursor.getRight() != null && cursor.getRight().getName().equalsIgnoreCase(name)) {
                if (cursor.getRight() != null && cursor.getRight().getName().equalsIgnoreCase(name)) {
                    cursor.setRight(null);
                } else if (cursor.getMiddle() != null && cursor.getMiddle().getName().equalsIgnoreCase(name)) {
                    if (cursor.getRight() != null) {
                        cursor.setMiddle(cursor.getRight());
                        cursor.setRight(null);
                    } else {
                        cursor.setMiddle(null);
                    }
                } else if (cursor.getLeft() != null && cursor.getLeft().getName().equalsIgnoreCase(name)) {
                    if (cursor.getMiddle() != null) {
                        cursor.setLeft(cursor.getMiddle());
                        if (cursor.getRight() != null) {
                            cursor.setMiddle(cursor.getRight());
                            cursor.setRight(null);
                        } else {
                            cursor.setMiddle(null);
                        }
                    } else {
                        cursor.setLeft(null);
                    }
                }
                System.out.println("A(n) " + name + " has successfully been removed as prey for the " + cursor.getName() + "!");
            } else {
                throw new IllegalArgumentException("Error: Name does not reference a direct child of the cursor");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Helper method to print out the indented tree. This is a recursive method that will go in preorder traversal
     * @param node
     *      Holds reference of node name
     * @param spaces
     *      Dependent on level, the amount of indentation
     */
    // PREORDER TRAVERSAL
    public void printTree(OrganismNode node, String spaces) {

        if (node == null)
            return;

        if (node.getPlant())
            System.out.println(spaces + "- " + node.getName());
        else
            System.out.println(spaces + "|- " + node.getName());
        spaces += "\t";
        printTree(node.getLeft(), spaces);
        printTree(node.getMiddle(), spaces);
        printTree(node.getRight(), spaces);
    }

    /**
     * Helper method to print path
     * @param node
     *      Node reference that points to root
     * @param target
     *      Node reference that points to the target cursor
     * @return
     */
    public boolean printPath(OrganismNode node, OrganismNode target) {
        if (node == null)
            return false;
        if (node == target) {
            this.result = node.getName() + " -> " + this.result;
            return true;
        } else if (printPath(node.getLeft(),target) || printPath(node.getMiddle(),target) || printPath(node.getRight(),target)) {
            this.result = node.getName() + " -> " + this.result;
            return true;
        }
        return false;
    }

    /**
     * Helper method to print all plants starting from the cursor and beneath.
     * @param node
     */
    // PREORDER TRAVERSAL
    public void printPlantTree(OrganismNode node) {
        if (node == null)
            return;
        if (node.getPlant())
            this.result += node.getName() + ", ";
        if (node.getLeft() != null)
            printPlantTree(node.getLeft());
        if (node.getMiddle() != null)
            printPlantTree(node.getMiddle());
        if (node.getRight() != null)
            printPlantTree(node.getRight());
    }
}
