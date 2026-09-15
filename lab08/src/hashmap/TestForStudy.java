package hashmap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestForStudy {
    public static void main(String[] args) {
        Set<Integer> a = new HashSet<>(List.of(3,5,7,8));
        Set<Integer> b = new HashSet<>(List.of(1,2,5,7));
        a.retainAll(b);
        System.out.println(a);
    }

}
