/**
 * Variable will hold all data of Object Variable that has parameter name and initalVariable.
 *      Getter and setter methods are available and a toString method
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.lang.*;

public class Variable {
    /**
     * @param name
     *      Name of the integer
     * @param initalVariable
     *      Value of the name
     */
    private String name;
    private int initialVariable;

    public Variable(String name, int initialVariable) {
        this.name = name;
        this.initialVariable = initialVariable;
    }
    public Variable() {

    }

    /**
     * Gets Name
     * @return
     *      Name of the integer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name
     * @param name
     *      Value of variable
     */
    public void setName(String name) {
        this.name = name;
    }


    public int getInitialVariable() {
        return initialVariable;
    }

    /**
     * Sets the InitalVariable value
     * @param initialVariable
     *      Value of Variable
     */
    public void setInitialVariable(int initialVariable) {
        this.initialVariable = initialVariable;
    }

    /**
     * To String of name and initialvalue as prompted in the manual
     * @return string
     *      Returns String of name and inital variable
     */
    public String toString() {
        return (String.format("%-20s%-20s",name,initialVariable));
    }
}

