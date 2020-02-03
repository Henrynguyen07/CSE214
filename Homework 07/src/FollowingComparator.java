/**
 * FollowingComparator class will compare the following count of each User and sort them in descending order
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */


import java.util.Comparator;

public class FollowingComparator implements Comparator<User> {
    FollowGraph graph;
    public FollowingComparator(FollowGraph graph) {
        this.graph = graph;
    }
    @Override
    public int compare(User o1, User o2) {
        if (graph.getAllFollowing(o1) == graph.getAllFollowing(o2))
            return 0;
        else if (graph.getAllFollowing(o1) > graph.getAllFollowing(o2))
            return -1;
        else
            return 1;
    }
}
