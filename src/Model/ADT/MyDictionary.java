package Model.ADT;
import Exceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class MyDictionary<K,V> implements MyIDictionary {

    private HashMap<K,V> dict;

    public MyDictionary() {
        dict = new HashMap<K,V>();
    }


    public MyDictionary(MyIDictionary<K, V> dict) {
        this.dict = dict.getDict();
    }

    public HashMap<K, V> getDict() {
        return dict;
    }

    @Override
    public String toString()
    {
        String result = "";
        for(K key : dict.keySet())
            result += key.toString() + "->" +  dict.get(key).toString() + ", ";

        return result;
    }

    @Override
    public String toString2()
    {
        String result = "SYM TABLE:\n";
        for(K key : dict.keySet())
            result += key.toString() + ": " +  dict.get(key).toString() + "\n";

        return result;
    }

    @Override
    public String toStringFiletable() {
        String result = "FILE TABLE:\n";
        for(K key : dict.keySet())
            result += key.toString() + "\n";

        return result;
    }

    @Override
    public String toStringFiletable2() {
        String result = "";
        for(K key : dict.keySet())
            result += key.toString() + ";";

        return result;
    }

    @Override
    public Collection values() {
        return dict.values();
    }

    @Override
    public Map getContent() {
        return dict;
    }


    @Override
    public HashMap getContent2() {
        return dict;
    }

    public Object lookup( Object key) throws MyException
    {
        if(!dict.containsKey(key))
            throw new MyException("key doesn't exist");

        return dict.get(key);
    }



    public boolean isDefined(Object key)
    {
        return dict.containsKey(key);
    }

    public boolean exists(Object value)
    {
        return dict.containsValue(value);
    }

    public void add(Object key, Object value) {

        dict.put((K)key,(V)value);
    }

    @Override
    public void delete(Object key) {
        dict.remove(key);
    }

    @Override
    public void setContent(HashMap newDict) {
        dict = newDict;
    }

    public boolean containsKey(Object key)
    {
        return dict.containsKey(key);
    }


    public void update(Object key, Object value)
    {
        dict.put((K)key,(V)value);
    }

}
