/**
 * BlockTracer will be the main class of the program. This class contain parameters, blockStack, finalStack, tempStack, newBlock, commands, variables
 * tempBlockVar, and var. In addition, this will read text files line by line and distinguishing each command
 *
 * @Author: Henry Nguyen
 * SBU ID: 111484010
 * Recitation 08
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class BlockTracer {
    public static void main(String args[]) throws IOException {
        /**
         *
         * @param blockStack
         *      Will hold the current block
         * @param finalStack
         *      Will hold all blocks that have ended
         * @param tempStack
         *      Is used to temporarily hold blocks when searching for a parameter
         * @param newBlock
         *      Everytiume { occurs, a new block will be made
         * @param commands
         *      Will hold all the relevant commands such as int, ;, etc..
         * @param variables
         *      Will hold all the initalization of integers
         * @param var
         *      An instance of variable
         */
        Scanner sc = new Scanner(System.in);
        Stack<Block> blockStack = new Stack<Block>();
        Stack<Block> finalStack = new Stack<Block>();
        Stack<Block> tempStack = new Stack<Block>();
        Block newBlock = null;
        ArrayList<String> commands = new ArrayList<String>();
        String[] variables;
        Variable var;
        boolean booleanFlag = false;
        while (booleanFlag == false)
            try {
                System.out.print("Enter the File Name: ");
                String fileName = sc.nextLine();
//            File fileName = new File("sample2.c");
                FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader inStream = new InputStreamReader(fis);
                BufferedReader stdin = new BufferedReader(inStream);
                String line;
                while ((line = stdin.readLine()) != null) {
                    line = line.stripLeading();
                    if (line.contains("{")) {
                        commands.add("{");
                    }
                    if (line.contains("int ") && (line.contains(",") || line.contains(";"))) {
                        String endVar = line.substring(line.indexOf("int ") + 4, line.indexOf(";"));
                        variables = endVar.split(",");
                        for (int i = 0; i < variables.length; i++) {
                            if (!(variables[i].contains("="))) {
                                variables[i] = variables[i] + " = 0";
                            }
                            variables[i] = variables[i].stripTrailing().stripLeading();
                            commands.add(variables[i]);
                        }
                    }
                    if (line.contains("/*$print ")) {
                        commands.add(line);
                    }
                    if (line.contains("}")) {
                        commands.add("}");
                    }
                }

                for (int i = 0; i < commands.size(); i++) {
                    Block assignedBlock;
                    String temp = commands.get(i);
                    // Searches for '{' instances
                    if (temp.equals("{")) {
                        newBlock = new Block();
                        blockStack.push(newBlock);
                    }
                    // Searches for '}' instances
                    if (temp.equals("}")) {
                        finalStack.push(blockStack.pop());
                    }
                    // Searches for instances of "int ="
                    if (!temp.contains("/*$print ") && !temp.contains("{") && !temp.contains("}") && (temp.contains("="))) {
                        var = new Variable();
                        assignedBlock = blockStack.pop();
                        var.setName(temp.substring(0, temp.indexOf("=")));
                        var.setInitialVariable(Integer.parseInt(temp.substring(temp.indexOf("=") + 1).strip().strip()));
                        assignedBlock.addData(var);
                        blockStack.push(assignedBlock);
                    }
                    // Searches for /*$print statements
                    if (temp.contains("/*$print LOCAL")) {
                        assignedBlock = blockStack.pop();
                        assignedBlock.printBlock();
                        blockStack.push(assignedBlock);
                    }
                    // Searches for /*$print
                    if (temp.contains("/*$print ") && (!temp.contains("/*$print LOCAL"))) {
                        String scan = temp.substring(temp.indexOf("/*$print ") + 9, temp.lastIndexOf("*/"));
                        boolean stopFlag = false;
                        try {
                            while (stopFlag == false) {
                                Block tempB = blockStack.pop();
                                tempStack.push(tempB);
                                for (int j = 0; j < tempB.getData().size(); j++) {
                                    if (tempB.getData().get(j).getName().startsWith(scan)) {
                                        System.out.println("----------- LOCAL PRINT ----------------");
                                        System.out.println(String.format("%-20s%-20s", "Variable Name", "Initial Value"));
                                        System.out.println(tempB.getData().get(j).toString());
                                        stopFlag = true;
                                        break;
                                    }
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Variable not found: " + scan);
                        } finally {
                            while (!tempStack.isEmpty()) {
                                blockStack.push(tempStack.pop());
                            }
                        }
                    }
                }
                booleanFlag = true;
            } catch (Exception e) {
                System.out.println("Error! - File not founded\n");
            }
    }
}


