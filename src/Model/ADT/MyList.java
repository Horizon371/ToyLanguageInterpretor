package Model.ADT;
import Exceptions.MyException;

import java.util.ArrayList;

public class MyList<T> implements MyIList {

    private ArrayList<T> list;

    public MyList() {
       list = new ArrayList<T>();
    }

    public MyList(MyIList list) {
        this.list = list.getList();
    }

    public void add(Object element)
    {
        list.add((T)element);
    }

    public ArrayList<T> getList() {
        return list;
    }

    public  void remove(Object element) throws MyException
    {
        if(!list.contains(element))
            throw new MyException("Object doesn't exist");

        list.remove(element);
    }

    @Override
    public String toString()
    {
        String result = "";

        for( T elem : list)
        {
            result += elem.toString();
            result += ",";
        }

        return result;
    }


    public int size()
    {
        return list.size();
    }

    public T getElemAtPos(int pos) throws MyException {
        if (list.size() <= pos || pos < 0)
            throw new MyException(" position is invalid");

        return list.get(pos);
    }

    @Override
    public String toString2() {
        String result = "OUT:\n";

        for( T elem : list)
        {
            result += elem.toString();
            result += "\n";
        }

        return result;
    }

}
