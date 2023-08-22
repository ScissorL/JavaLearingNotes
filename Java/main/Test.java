import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
public class Test {
    class InnerClass {

    }
    static class StaticInnerClass{}
    public static void main(String[] args) {
        System.out.println(tableSizeFor(100));
        System.out.println(tableSizeFor(129));
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
//        testReference(arrayList);
        Test test = new Test();
        InnerClass innerClass = test.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        System.out.println(max(Arrays.asList(1,2,3)));
        int head = 0;
        System.out.println((head - 1) & 7);
    }
    public static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public  void testReference(ArrayList<Integer> arrayList ) {
        arrayList.add(-1);
    }

    public static  <E extends Comparable<? super E>> E max(List<? extends E> es) {
        if (es == null) return null;
        Iterator<? extends E> iterator = es.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.compareTo(result) > 0) {
                result = next;
            }
        }
        return result;
    }
}
