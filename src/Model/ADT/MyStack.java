package Model.ADT;
import Exceptions.MyException;

import java.util.ArrayList;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;


    public MyStack()
    {
        stack = new Stack<T>();
    }

    public Stack<T>getStack()
    {
        return stack;
    }
    public MyStack(MyIStack another)
    {
        this.stack = another.getStack();
    }

    public T pop()
    {
        return stack.pop();
    }

    @Override
    public void push(T element) {
        stack.push(element);
    }

    public boolean isEmpty()
    {
        return  stack.empty();
    }



    @Override
    public String toString()
    {
        return stack.toString();
    }

    @Override
    public String toString2()
    {
        String stackToPrint="EXE STACK:\n";
        for(T element:this.stack){
            stackToPrint += element.toString()+"\n";
        }

        return stackToPrint.toString();
    }


    public ArrayList<String> toArray()
    {
        String exeStackString = "";
        ArrayList<String> stackToPrint = new ArrayList<>();
//        for(T element:this.stack) {
//            exeStackString += element.toString() + ";";
//        }
//
//        for(String statement : exeStackString.split(";"))
//        {
//            stackToPrint.add(0,statement);
//        }
        for(T element:this.stack) {
            stackToPrint.add(0,element.toString());
        }


        return stackToPrint;
    }

}