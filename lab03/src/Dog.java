import java.util.Comparator;

public class Dog implements Comparable(Dog) {
    public String name;
    private int size;
    public Dog(String n, int s) {...}
    public void bark() {System.out.println(name + "syas:bark");}

    @Override
    public int compareTo(Dog otherDog) {

        return this.size - otherDog.size;

    }

private static class NameComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.name.compareTo(o2.name);
    }
}

public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
}

}
