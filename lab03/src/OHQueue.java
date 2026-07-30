package ohqueue;

import java.util.Iterator;

public class OHQueue implements Iterable<OHRequest>{

    private OHRequest queue;

    public OHQueue (OHRequest queue) {
        this.queue = queue;

    }

    @Override
    Iterator<OHRequest> iterator() {
        return new OHIterator(queue);
    }

}
