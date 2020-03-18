package Model.PrgState;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIList;
import Model.ADT.MyIStack;
import Model.Statements.IStmt;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class PrgState{
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer,Value> heap;
    public static int id = 0;
    public int idd;

    private IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIDictionary<StringValue, BufferedReader> fileTable,
                    MyIList<Value> output, MyIHeap<Integer,Value> heap, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = output;
        originalProgram= prg;//recreate the entire original prg
        exeStack.push(prg);
        this.fileTable = fileTable;
        this.heap = heap;
        this.idd = manageId();
    }

    synchronized public static int manageId()
    {
        id++;
        return id;
    }

    public MyIHeap<Integer,Value> getHeap() {
        return heap;
    }

    public MyIStack<IStmt> getStack()
    {
        return exeStack;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    @Override
    public String toString()
    {
        return Integer.toString(id) + ":: " + exeStack.toString() + " / " + symTable.toString() + " / " + out.toString() + "\n";
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, IOException {
        if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDictionary<String,Value> getSymTable() {
        return symTable;
    }

    public int getIdd() {
        return idd;
    }
}
