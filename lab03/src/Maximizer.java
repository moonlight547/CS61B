public class Maximizer {
    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for(int i = 0; i < items.length; i += 1) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp >0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    Dog d4 = new Dog("Oski",200);
    Dog d5 = new Dog("Cerebus", 999999);

    Comparator<Dog> nc = Dog.getNameComparator();
    int cmp = nc.compare(d4,d5);
    if(cmp > 0) {// d4 comes later in the alphabet
        d4.bark();
    } else {// d4 comes later in the alphabet
        d5.bark;
    }
}
