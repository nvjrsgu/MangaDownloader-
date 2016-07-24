import java.util.Comparator;

/**
 * Created by cjvnj on 24.07.2016.
 */

public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}