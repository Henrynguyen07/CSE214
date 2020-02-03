


import java.util.Comparator;

public class IndexComparator implements Comparator<User>{

    FollowGraph graph;

    public IndexComparator(FollowGraph graph) {
        this.graph = graph;
    }

    @Override
    public int compare(User o1, User o2) {
        if (o1.getIndexPos() == o2.getIndexPos())
            return 0;
        else if (o1.getIndexPos() < o2.getIndexPos())
            return -1;
        else
            return 1;
    }
}

