/**
 * FollowGraph will be responsible of performing methods. Methods include, Adding user, Adding connections, Reading from
 * text files, Printing all users in a sorted form, Printing all loops, removing users and connections, and finding the shortest
 * path and printing all possible paths.
 *
 * Directed Graph
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.util.*;
import java.io.*;

public class FollowGraph implements Serializable {
    /**
     * @param users
     *      contains all users of the service.
     * @param MAX_USERS
     *      Maximum number of users
     * @param connections
     *      Adjacency Matrix
     * @param path
     *      path String
     */
    private ArrayList<User> users;
    private static final int MAX_USERS = 100;
    private boolean[][] connections;
    private String path = "";

    /**
     * Constructor when program is ran.
     */
    public FollowGraph() {
        users = new ArrayList<>();
        connections = new boolean[MAX_USERS][MAX_USERS];
    }

    /**
     * Adds new User objects to users List
     * @param userName
     *      User input for a new user to be added to the graph
     * @throws Exception
     *      If name already exists
     */
    public void addUser(String userName) throws Exception {
        try {
            if (users.size() < MAX_USERS) {
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equalsIgnoreCase(userName) && users.get(i) != null)
                        throw new Exception("Error: Name Already Exist");
                }
                users.add(new User(userName, users.size(), users.size() + 1));
            } else throw new Exception("Error: Reached Maximum User Capacity");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Will add a connection from and to Users. Creating a directed graph
     * @param userFrom
     *      User input from
     * @param userTo
     *      User input to
     * @throws Exception
     */
    public void addConnection(String userFrom, String userTo) throws Exception {
        try {
            User u1 = null;
            User u2 = null;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equalsIgnoreCase(userFrom))
                    u1 = users.get(i);
                if (users.get(i).getUserName().equalsIgnoreCase(userTo))
                    u2 = users.get(i);
            }
            if (u1 != null && u2 != null)
                connections[u1.getIndexPos()][u2.getIndexPos()] = true;
            else {
                throw new Exception("Error: A connection doesn't exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Will remove user from the list. In addition, approproatiely update all the user index position and user count
     * @param userName
     *      User input name
     * @throws Exception
     *      If userName doesn't exist
     */
    public void removeUser(String userName) throws Exception {
        try {
            boolean nodeRemove = false;
            User removedNode = null;
            for (User c : users) {
                if (c.getUserName().equalsIgnoreCase(userName)) {
                    removedNode = c;
                    nodeRemove = true;
                    for (int i = 0; i < users.size(); i++) {
                        for (int j = users.indexOf(c); j < users.size(); j++) {
                            if (j == users.size() - 1) {
                                connections[i][j] = false;
                                break;
                            } else
                                connections[i][j] = connections[i][j + 1];
                        }
                        for (int j = users.indexOf(c); j < users.size(); j++) {
                            if (j == users.size() - 1) {
                                connections[j][i] = false;
                                break;
                            } else
                                connections[j][i] = connections[j + 1][i];
                        }
                    }
                }
            }
            if (nodeRemove == true) {
                users.remove(removedNode);
                updateUsers();
            } else {
                throw new Exception("Error: Name doesn't Exist");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes connection from and to User
     * @param userFrom
     *      User input User from
     * @param userTo
     *      User input User to
     * @throws Exception
     *      If either userFrom or userTo doesn't exist in the list.
     */
    public void removeConnection(String userFrom, String userTo) throws Exception {
        try {
            User u1 = null;
            User u2 = null;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equalsIgnoreCase(userFrom))
                    u1 = users.get(i);
                if (users.get(i).getUserName().equalsIgnoreCase(userTo))
                    u2 = users.get(i);
            }
            if (u1 != null && u2 != null)
                connections[u1.getIndexPos()][u2.getIndexPos()] = false;
            else {
                throw new Exception("Error: Name doesn't exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param userFrom
     * @param userTo
     * @return
     *      Prints out the shortest path from user and to user
     * @throws Exception
     *      If there is no path
     */
    public String shortestPath(String userFrom, String userTo) {
        String result = "";
        try {
            List<String> path = allPaths(userFrom, userTo);
            for (int i = 1; i < path.size(); i++) {
                String temp = path.get(i);
                int j = i - 1;
                while (j >= 0 && temp.length() < path.get(j).length()) {
                    path.set(j + 1, path.get(j));
                    j--;
                }
                path.set(j + 1, temp);
            }
            result = path.get(0).substring(0, path.get(0).lastIndexOf("->"));
        } catch (Exception e) {
            System.out.print("Error: Connection is not valid");
        }
        return result;
    }

    /**
     *
     * @param userFrom
     * @param userTo
     * @return
     *      Returns all paths from a requested userFrom and userTo
     * @throws Exception
     *      If there is no paths available
     */
    public List<String> allPaths(String userFrom, String userTo) {
        List<String> allPaths;
        List<String> pathList = new ArrayList<>();
        boolean[] isVisited = new boolean[users.size()];
        pathList.add(userFrom);
        printAllPaths(userFrom, userTo, isVisited, pathList);
        allPaths = Arrays.asList(path.split("###"));
        path = "";
        return allPaths;
    }

    /**
     * Helper method for allPaths. Will perform DFS search. DFS is performed recursively
     * @param userFrom
     * @param userTo
     * @param isVisited
     *      Boolean of true and false that will represent if the node is visited
     * @param localPathList
     */
    private void printAllPaths(String userFrom, String userTo, boolean[] isVisited, List<String> localPathList) {
        User start = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equalsIgnoreCase(userFrom))
                start = users.get(i);
        }
        isVisited[start.getIndexPos()] = true;
        if (userFrom.equalsIgnoreCase(userTo)) {
            for (String c : localPathList)
                path += c + " -> ";
            path += "###";
            isVisited[start.getIndexPos()] = false;
            return;
        }
        for (int i = 0; i < connections[start.getIndexPos()].length; i++) {
            if (connections[start.getIndexPos()][i] == true) {
                if (!isVisited[users.get(i).getIndexPos()]) {
                    localPathList.add(users.get(i).getUserName());
                    printAllPaths(users.get(i).getUserName(), userTo, isVisited, localPathList);
                    localPathList.remove(users.get(i).getUserName());
                }
                isVisited[start.getIndexPos()] = false;
            }
        }
    }

    /**
     * Prints all Users in a sorted order
     * @param comp
     */
    public void printAllUsers(Comparator comp) {
        System.out.println("Users:");
        System.out.format("%-20s%-25s%-20s", "User Name", "Number of Followers", "Number Following");
        System.out.println();
        Collections.sort(users, comp);
        for (int i = 0; i < users.size(); i++) {
            System.out.format("%-30s%-25d%-20d", users.get(i).getUserName(), getAllFollowers(users.get(i)), getAllFollowing(users.get(i)));
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints all followers
     * @param userName
     */
    public void printAllFollowers(String userName) {
        int followersCounter = 0;
        int userRow = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equalsIgnoreCase(userName))
                userRow = i;
        }
        for (int i = 0; i < connections[userRow].length; i++) {
            if (connections[i][userRow] == true) {
                followersCounter++;
            }
        }
        System.out.println(followersCounter);
    }

    /**
     * @param userName
     * @return
     *      Returns all followers of userInput
     */
    public int getAllFollowers(User userName) {
        int followersCounter = 0;

        for (int i = 0; i < connections.length; i++) {
            if (connections[i][userName.getIndexPos()] == true)
                followersCounter++;
        }
        return followersCounter;
    }

    /**
     * prints all the following of user
     * @param userName
     */
    public void printAllFollowing(String userName) {
        int followingCounter = 0;
        int userColumn = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equalsIgnoreCase(userName))
                userColumn = i;
        }
        for (int i = 0; i < connections[userColumn].length; i++) {
            if (connections[userColumn][i] == true) {
                followingCounter++;
            }
        }
        System.out.println(followingCounter);
    }

    /**
     *
     * @param userName
     * @return
     *      Integer of all following of user
     */
    public int getAllFollowing(User userName) {
        int followingCounter = 0;

        for (int i = 0; i < connections.length; i++) {
            if (connections[userName.getIndexPos()][i] == true)
                followingCounter++;
        }
        return followingCounter;
    }

    /**
     * @return
     *      Will print out all distinct loops in the graph
     */
    public List<String> findAllLoop() {
        List<String> allLoops = new ArrayList<>();
        User u1 = null;
        User u2 = null;
        for (int i = 0; i < users.size() / 2; i++) {
            for (int j = users.size() / 2; j < users.size(); j++)
                if (i != j) {
                    List<String> paths = new ArrayList<>(allPaths(users.get(i).getUserName(), users.get(j).getUserName()));
                    for (String c : paths) {
                        List<String> temp;
                        temp = Arrays.asList(c.split("->"));
                        if (temp.size() > 2) {
                            String userFrom = temp.get(0);
                            String userTo = temp.get(temp.size() - 2);
                            userFrom = userFrom.strip();
                            userTo = userTo.strip();
                            for (int k = 0; k < users.size(); k++) {
                                if (users.get(k).getUserName().equalsIgnoreCase(userFrom))
                                    u1 = users.get(k);
                                if (users.get(k).getUserName().equalsIgnoreCase(userTo))
                                    u2 = users.get(k);
                            }
                            if (u1 != null && u2 != null) {
                                if (connections[u2.getIndexPos()][u1.getIndexPos()] == true) {
                                    allLoops.add(c);
                                }
                            }
                        }
                    }
                }
        }
        return allLoops;
    }

    /**
     * Method will read a user textlist and add users to users list
     * @param filename
     *      File name to read
     * @throws Exception
     *      If text is empty or user already exist in the list
     */
    public void loadAllUsers(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String name;
        try {
            while ((name = br.readLine()) != null)
                addUser(name);
        } catch (Exception e) {
            throw new Exception("Error: File not Readable");
        }
    }

    /**
     * Method will add connections to the adjacency matrix
     * @param filename
     *      File name to read
     * @throws Exception
     *      If connection already exists or one of the users dont exist
     */
    public void loadAllConnections(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String connection;
        while ((connection = br.readLine()) != null) {
            String name1 = connection.substring(0, connection.indexOf(","));
            String name2 = connection.substring(connection.indexOf(",") + 2);
            addConnection(name1, name2);
        }
    }

    /**
     * Updates user
     */
    public void updateUsers() {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setIndexPos(i);
        }
        User.setUserCount(users.size());
    }

    /**
     * Resets graph to index ascending order
     * @param comp
     */
    public void resetGraph(Comparator comp) {
        Collections.sort(users, comp);
    }
}
