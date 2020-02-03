/**
 * Block will be responsible of holding Variable data and holding Block values. Parameters
 *      consist of an arrayList of variables.
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class Block {
    /**
     * @param variables
     *      List of Variables
     */
    private ArrayList<Variable> variables;

    public Block() {
        variables = new ArrayList<Variable>();
    }

    /**
     *
     * @return Variables
     *      List of the initalized variables
     */
    public ArrayList<Variable> getData() {
        return variables;
    }

    /**
     * Adds more variables to the list
     * @param data
     */
    public void addData(Variable data) {
        this.variables.add(data);
    }

    /**
     * Prints out all local variables in the block
     *
     * @exception NullPointerException
     *      Indicates that no local variables to print
     */
    public void printBlock() {
        try {
            if (variables.size() == 0) {
                throw new Exception();
            } else {
                System.out.println("----------- BLOCK PRINT ----------------");
                System.out.println(String.format("%-20s%-20s", "Variable Name", "Initial Value"));
                for (int i = 0; i < variables.size(); i++) {
                    if (variables.get(i) != null) {
                        System.out.println(variables.get(i).toString());
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("No local variables to print.\n");
        }
    }
}