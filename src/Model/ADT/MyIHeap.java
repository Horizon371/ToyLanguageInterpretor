package Model.ADT;

import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<K,V> {
    String toString2();
    String toString();
    boolean isDefined(Object key);
    public int add(Object value);
    V lookup( Object key) throws MyException;
    void update(Integer key, Object value);
    HashMap<Integer, V> getContent();

    void setContent(Map<Integer, V> unsafeGarbageCollector);
}
