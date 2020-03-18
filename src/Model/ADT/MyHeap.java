package Model.ADT;

import Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K,V> implements  MyIHeap{

    private HashMap<Integer,V> heap;
    private int nextFreeLocation;

    public MyHeap() {
        this.heap = new HashMap<Integer, V>();
        this.nextFreeLocation = 1;
    }


    public int add(Object value)
    {
        heap.put(nextFreeLocation,(V)value);
        nextFreeLocation += 1;
        return nextFreeLocation - 1;
    }


    public boolean isDefined(Object key)
    {
        return heap.containsKey(key);
    }

    public V lookup( Object key) throws MyException
    {
        if(!heap.containsKey(key))
            throw new MyException("key doesn't exist");

        return heap.get(key);
    }

    public MyHeap(MyIHeap<Integer, V> heap) {
        this.heap = heap.getContent();
    }

    public void update(Integer key, Object value) {
        heap.put(key,(V)value);
    }

    @Override
    public HashMap<Integer, V> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map unsafeGarbageCollector) {
        heap = (HashMap<Integer, V>) unsafeGarbageCollector;
    }


    @Override
    public String toString()
    {
        String result = "";
        for(int key : heap.keySet())
            result += String.valueOf(key) + "->" +  heap.get(key).toString() + ", ";

        return result;
    }

    @Override
    public String toString2()
    {
        String result = "HEAP:\n";
        for(int key : heap.keySet())
            result += String.valueOf(key) + ": " +  heap.get(key).toString() + "\n";

        return result;
    }




}
