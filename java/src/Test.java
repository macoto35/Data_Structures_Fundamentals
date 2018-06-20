import java.util.*;

public class Test {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<String>();
        ListIterator<String> iter = list.listIterator();

        iter.add("first");
        iter.add("second");
        iter.add("third");

        iter.previous();
        iter.remove();


        while(iter.hasPrevious()) {
            System.out.println(iter.previous());
        }
        System.out.println("-------------------------------");

        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
