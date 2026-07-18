public class removeDuplicates {


    Node ref = sentinel.next;
    Node checker;
while (ref != sentinel) {
        checker = ref.next;
        while (checker != sentinel) {
            if (ref.item == checker.item) {
                Node checkerPrev = checker.prev;
                Node checkerNext = checker.next;

                checkerPrev.next = checker.next;
                checkerNext.prev = checker.prev;
                checker = checkerNext;
            } else {
                checker = checker.next;
            }
        }
        ref = ref.next;
    }

}
