/**
 * FollowGraphDriver will be the main driver of this program. It will be responsible of handling all the requested methods
 * inputted by the user
 *
 * @Author: Henry Nguyen
 * SBU ID: 111484010
 * Recitation 08
 */

import java.io.Serializable;
import java.util.*;
import java.io.*;

public class FollowGraphDriver implements Serializable {

    public static void main(String[] args) throws Exception {
        FollowGraph followGraph;
        Scanner input = new Scanner(System.in);
        followGraph = Deserialize();
        if (followGraph == null) {
            followGraph = new FollowGraph();
        }
        String inputReader;
        String fromReader;
        String toReader;

        System.out.println("(U) Add User \n" +
                "(C) Add Connection \n" +
                "(AU) Load All Users \n" +
                "(AC) Load All Connections \n" +
                "(P) Print All Users \n" +
                "(L) Print All Loops \n" +
                "(RU) Remove User \n" +
                "(RC) Remove Connection \n" +
                "(SP) Find Shortest Path \n" +
                "(AP) Find All Paths \n" +
                "(Q) Quit");
        while (true) {
            System.out.println();
            System.out.print("Enter A Selection: ");
            String selection = input.nextLine().toUpperCase();
            System.out.println();
            switch (selection) {
                case "U":
                    System.out.print("Please enter the name of the user: ");
                    inputReader = input.nextLine();
                    followGraph.addUser(inputReader);
                    break;
                case "C":
                    System.out.print("Please enter the source of the connection to add: ");
                    fromReader = input.nextLine();
                    System.out.print("Please enter the dest of the connection to add: ");
                    toReader = input.nextLine();
                    followGraph.addConnection(fromReader, toReader);
                    break;
                case "AU":
                    try {
                        System.out.print("Enter the file name: ");
                        inputReader = input.nextLine();
                        followGraph.loadAllUsers(inputReader);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "AC":
                    try {
                        System.out.print("Enter the file name: ");
                        inputReader = input.nextLine();
                        followGraph.loadAllConnections(inputReader);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "P":
                    boolean menuFlag = false;
                    System.out.println("(SA) Sort By User Name \n(SB) Sort By User Followers \n(SC) Sort By User Following\n(Q) Quit\n");
                    while (menuFlag == false) {
                        System.out.print("Enter A Selection: ");
                        selection = input.nextLine().toUpperCase();
                        System.out.println();
                        switch (selection) {
                            case "SA":
                                followGraph.printAllUsers(new NameComparator());
                                break;
                            case "SB":
                                followGraph.printAllUsers(new FollowersComparator(followGraph));
                                break;
                            case "SC":
                                followGraph.printAllUsers(new FollowingComparator(followGraph));
                                break;
                            case "Q":
                                menuFlag = true;
                                followGraph.resetGraph(new IndexComparator(followGraph));
                                System.out.println("Returning to Main Menu...");
                                break;
                            default:
                                System.out.println("Selection is not a option\n");
                                break;
                        }
                    }
                    break;
                case "L":
                    if (followGraph.findAllLoop().size() > 0) {
                        System.out.println("There are " + followGraph.findAllLoop().size() + " loops:");
                        for (String c : followGraph.findAllLoop()) {
                            System.out.println(c.substring(0, c.lastIndexOf("->")));
                        }
                    } else {
                        System.out.println("There are no Loops");
                    }

                    break;
                case "RU":
                    System.out.print("Please enter the user to remove: ");
                    inputReader = input.nextLine();
                    followGraph.removeUser(inputReader);
                    break;
                case "RC":
                    System.out.print("Please enter the source of the connection remove: ");
                    fromReader = input.nextLine();
                    System.out.print("Please enter the dest of the connection to remove: ");
                    toReader = input.nextLine();
                    followGraph.removeConnection(fromReader, toReader);
                    break;
                case "SP":
                    System.out.print("Please enter the desired source: ");
                    fromReader = input.nextLine();
                    System.out.print("Please enter the desired destination: ");
                    toReader = input.nextLine();
                    System.out.println(followGraph.shortestPath(fromReader, toReader));
                    break;
                case "AP":
                    System.out.print("Please enter the desired source: ");
                    fromReader = input.nextLine();
                    System.out.print("Please enter the desired destination: ");
                    toReader = input.nextLine();
                    try {
                        for (String c : followGraph.allPaths(fromReader, toReader))
                            System.out.println(c.substring(0, c.lastIndexOf("->")));
                    } catch (Exception e) {
                        System.out.println("Error: Path doesn't exist");
                    }
                    break;
                case "Q":
                    Serialize(followGraph);
                    System.out.println("FollowGraph object saved into file FollowGraph.obj");
                    System.out.println("Program Terminating...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Selection is not a option ");
                    break;
            }
        }
    }

    static void Serialize(FollowGraph user) {
        try {
            FileOutputStream fos = new FileOutputStream("FollowGraph.obj");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    static FollowGraph Deserialize() {
        FollowGraph savedUser = null;
        try {
            FileInputStream fis = new FileInputStream("FollowGraph.obj");
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            savedUser = (FollowGraph) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Creating an instance of 'FollowGraph.obj'");
        }
        return savedUser;
    }
}

