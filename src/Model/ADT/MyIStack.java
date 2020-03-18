package Model.ADT;

import java.util.ArrayList;
import java.util.Stack;

public interface MyIStack<T>{
    T pop();
    void push(T v);
    public boolean isEmpty();
    public String toString2();
    public Stack<T> getStack();
    ArrayList<String> toArray();
}
