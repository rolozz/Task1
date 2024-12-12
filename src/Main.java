import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        ArrayList_MihailKrasheninnikov<Integer> list = new ArrayList_MihailKrasheninnikov<>();
        ArrayList_MihailKrasheninnikov<String> list2 = new ArrayList_MihailKrasheninnikov<>();
        list.add(1);
        list.add(6);
        list.add(3);
        list.add(47);
        list.add(-3);
        list.add(4);
        list.add(18);
        list.add(2);
        list.add(2);
        list.add(34);
        list.set(0,15);
        list.remove(1);
        list.quickSort(Integer::compareTo);
        System.out.println(list);
        System.out.println(list.isSorted());
        list2.add("asd");
        list2.add("ghj");
        list2.add("xcv");
        list2.add("bgh");
        list2.add("ert");
        list2.quickSort(Comparator.naturalOrder());
        System.out.println(list2);
    }
}
