package Model.ADT;


import Exceptions.MyException;

import java.util.ArrayList;

public interface MyIList<T>{

    void add(T element);
    void remove(T element) throws MyException;
    String toString();
    int size();
    T getElemAtPos(int pos) throws MyException;
    String toString2();
    ArrayList<T> getList();
}
