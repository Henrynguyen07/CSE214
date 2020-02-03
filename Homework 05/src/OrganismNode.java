/**
 * OrganismNode will be responsible of all node attributes. Each node represents a single species and will have references
 * with left, middle, right. These references are for the children. Parameters include name,
 * isplant, isHerbivore, isCarnivore, left, middle right. When moth isHerbivore and isCarnivore
 * is true, animal is a omnivore.
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class OrganismNode {
    /**
     * @param name
     *      Reference of Node Name
     * @param isPlant
     *      True if node is a plant, false if its an animal
     * @param isHerbivore
     *      True if animal only eats plants
     * @param isCarnivore
     *      True if animal only eats animals.
     * @param left
     *      Left node reference
     * @param middle
     *      middle node reference
     * @param right
     *      Right node reference
     */
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;

    public OrganismNode() {

    }

    public OrganismNode(String name, boolean isHerbivore, boolean isCarnivore) {
        this.name = name;
        if (isHerbivore == false && isCarnivore == false)
            isPlant = true;
        else
            isPlant = false;
        this.isHerbivore = isHerbivore;
        this.isCarnivore = isCarnivore;
        left = null;
        middle = null;
        right = null;
    }

    public String getName() {
        return name;
    }

    public boolean getPlant() {
        return isPlant;
    }

    public boolean getHerbivore() {
        return isHerbivore;
    }

    public boolean getCarnivore() {
        return isCarnivore;
    }

    public OrganismNode getLeft() {
        return left;
    }

    public OrganismNode getMiddle() {
        return middle;
    }

    public OrganismNode getRight() {
        return right;
    }

    public boolean isCarnivore() {
        if (this.getHerbivore() == false)
            return true;
        return false;
    }

    public boolean isHerbivore() {
        if (this.getCarnivore() == false)
            return true;
        return false;
    }

    public boolean isOmnivore() {
        if (this.getHerbivore() == true && this.getCarnivore() == true)
            return true;
        return false;
    }

    public void setLeft(OrganismNode newLeft) {
        left = newLeft;
    }

    public void setMiddle(OrganismNode newMiddle) {
        middle = newMiddle;
    }

    public void setRight(OrganismNode newRight) {
        right = newRight;
    }


    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
        if (!preyNode.isPlant) {
            // OMNIVORE CASE
            if (isOmnivore() == true) {
                if (right != null)
                    throw new PositionNotAvailableException("Error: There is no more room for more prey for this predator.");
                if (left == null) {
                    this.setLeft(preyNode);
                } else if (middle == null) {
                    this.setMiddle(preyNode);
                } else if (right == null) {
                    this.setRight(preyNode);
                }
                // CARNIVORE CASE
            } else if (isCarnivore() == true) {
                if (right != null)
                    throw new PositionNotAvailableException("Error: There is no more room for more prey for this predator.");
                if (left == null) {
                    this.setLeft(preyNode);
                } else if (middle == null) {
                    this.setMiddle(preyNode);
                } else if (right == null) {
                    this.setRight(preyNode);
                }
            } else {
                throw new DietMismatchException("Error: This prey cannot be added as it does not match the diet of the predator.");
            }
        } else {
            throw new IsPlantException("Error: Node is a plant node. Plant nodes cannot have children");
        }
    }
}

