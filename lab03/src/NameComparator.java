import java.util.Comparator;

public class NameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.name.compareTo(o2.name);
    }
}
