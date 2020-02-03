/**
 * FollowersComparator class will compare the follower count of each User and sort them in descending order
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.util.Comparator;

public class FollowersComparator implements Comparator<User> {
    FollowGraph graph;

    public FollowersComparator(FollowGraph graph) {
        this.graph = graph;
    }

    @Override
    public int compare(User o1, User o2) {
        if (graph.getAllFollowers(o1) == graph.getAllFollowers(o2))
            return 0;
        else if (graph.getAllFollowers(o1) > graph.getAllFollowers(o2))
            return -1;
        else
            return 1;
    }
}

