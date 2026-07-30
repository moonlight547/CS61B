import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void maxNaturalOrderTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(3);
        m.addFirst(10);
        m.addFirst(5);

        assertThat(m.max()).isEqualTo(10);
    }

    @Test
    public void maxReverseOrderTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(3);
        m.addFirst(10);
        m.addFirst(5);

        assertThat(m.max(Comparator.reverseOrder())).isEqualTo(3);
    }

    @Test
    public void maxEmptyTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());

        assertThat(m.max()).isEqualTo(null);
    }


}
