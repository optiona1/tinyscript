package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class PeekIterator<T> implements Iterator<T> {
    private Iterator<T> it;
    private LinkedList<T> queueCache = new LinkedList<>();
    private LinkedList<T> statckPutBacks = new LinkedList<>();
    private final static int CACHE_SIZE = 10;
    private T _endToken;

    public PeekIterator(Stream<T> stream, T endToken) {
        it = stream.iterator();
        _endToken = endToken;
    }

    public T peek() {
        if(this.statckPutBacks.size() > 0) {
            return this.statckPutBacks.getFirst();
        }
        if(!it.hasNext()) {
            return _endToken;
        }
        T val = next();
        this.putBack();
        return val;
    }

    public void putBack() {
        if(this.queueCache.size() > 0) {
        
            this.statckPutBacks.push(this.queueCache.pollLast());
        }
    }

    @Override
    public boolean hasNext() {
        return _endToken != null || this.statckPutBacks.size() > 0 || it.hasNext()   ;
    }
    @Override
    public T next() {
        T val = null;
        if(this.statckPutBacks.size() > 0) {
            val = this.statckPutBacks.pop();
        } else {
            if(!this.it.hasNext()) {
                T tmp = _endToken;
                _endToken = null;
                return tmp;
            }
            val = it.next();
        }

        while(queueCache.size() > CACHE_SIZE - 1) {
            queueCache.poll();
        }
        queueCache.add(val);
        return val;
    }
}