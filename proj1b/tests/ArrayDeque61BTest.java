import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void getMethodTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertThat(deque.get(0)).isNull();
        assertThat(deque.get(-1)).isNull();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);

        assertThat(deque.get(0)).isEqualTo(3);


        assertThat(deque.get(3)).isNull();
        assertThat(deque.get(-5)).isNull();
    }

    @Test

    public void isEmptyTestBasic(){
        ArrayDeque61B<Integer> deque  = new ArrayDeque61B<>();

        assertTrue(deque .isEmpty());
        assertEquals(0, deque.size());

        deque .addFirst(1);
        assertFalse(deque .isEmpty());
        assertEquals(1, deque.size());

    }

    @Test
    public void toListTestBasic(){
        ArrayDeque61B<Integer> deque  = new ArrayDeque61B<>();

        assertTrue(deque .isEmpty());
        assertEquals(0, deque.size());

        /*deque .addFirst(1);
        assertFalse(deque .isEmpty());
        assertEquals(1, deque.size()); */

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        List<Integer> expected = List.of(3, 1, 2, 4);

        assertThat(deque.toList()).containsExactlyElementsIn(expected).inOrder();

    }

    @Test

    public void removeFirstTestBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        assertThat(deque.removeFirst()).isEqualTo(null);

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);


        assertThat(deque.removeFirst()).isEqualTo(3);
        assertThat(deque.size()).isEqualTo(2);


        assertThat(deque.get(0)).isEqualTo(2);
        assertThat(deque.get(1)).isEqualTo(1);

        assertThat(deque.removeFirst()).isEqualTo(2);
        assertThat(deque.removeFirst()).isEqualTo(1);
    }

    @Test

    public void removeLastTestBasic() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        assertThat(deque.removeLast()).isEqualTo(null);

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertThat(deque.removeLast()).isEqualTo(3);
        assertThat(deque.size()).isEqualTo(2);

        assertThat(deque.get(0)).isEqualTo(1);
        assertThat(deque.get(1)).isEqualTo(2);

        assertThat(deque.removeLast()).isEqualTo(2);
        assertThat(deque.removeLast()).isEqualTo(1);


    }

    @Test

    public void resizeDownTestBasic(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 19; i++) {
            deque.removeFirst();
        }

        assertThat(deque.get(0)).isEqualTo(19);
        assertThat(deque.size()).isEqualTo(1);



    }

    @Test

    public void resizeDownWithAddFirstTest(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 19; i++) {
            deque.removeLast();
        }

        assertThat(deque.get(0)).isEqualTo(19);
        assertThat(deque.size()).isEqualTo(1);

    }

    @Test

    public void resizeThenAddTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 19; i++) {
            deque.removeLast();
        }
        deque.addLast(20);
        assertThat(deque.get(1)).isEqualTo(20);


    }

    @Test

    public void resizeUpTestBasic(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 1000; i++) {
            deque.addLast(i);
        }

        assertThat(deque.get(999)).isEqualTo(999);
        assertThat(deque.size()).isEqualTo(1000);



    }

    @Test

    public void resizeUpTest(){
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 1001; i++) {
            deque.addLast(i);
        }

        assertThat(deque.get(1000)).isEqualTo(1000);
        assertThat(deque.size()).isEqualTo(1001);
        assertThat(deque.get(0)).isEqualTo(0);



    }


}
