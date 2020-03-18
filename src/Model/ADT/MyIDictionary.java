package Model.ADT;

import Exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<K,V> {

    String toString();
    V lookup( K key) throws MyException;
    boolean containsKey(K key);
    void update(K key, V value);
    boolean isDefined(K key);
    boolean exists(Object value);
    void add(K key, V value);
    HashMap getContent2();
    void delete(K key);
    void setContent(HashMap<K,V> newDict);
    String toString2();
    HashMap<K, V> getDict();
    String toStringFiletable();
    String toStringFiletable2();
    Collection<V> values();

    Map<K, V> getContent();
}
