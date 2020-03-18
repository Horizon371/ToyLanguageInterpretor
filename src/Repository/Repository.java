package Repository;

import Exceptions.MyException;
import Model.ADT.*;
import Model.PrgState.PrgState;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Repository implements RepositotyInterface {

    private List<PrgState> programStates;
    private int currentIndex;
    private String logFilePath;

    public Repository( String path){
        this.programStates = new ArrayList<>();
        this.currentIndex = 0;
        logFilePath = path;
    }

//    public PrgState getCrtPrg(){
//        return programStates.get(currentIndex);
//    }


    public List<PrgState> getPrgStates() {
        return programStates;
    }

    public void addProgram(PrgState programState)
    {
        programStates.add(programState);
    }

    public void addFilePath(String filePath)
    {
        logFilePath = filePath;
    }
    public void logPrgStateExec(PrgState state) throws MyException, IOException {
        PrintWriter logFile = new PrintWriter(new FileOutputStream(new File(this.logFilePath), true));

        MyIStack<IStmt> stack = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIList<Value> out = state.getOut();
        MyIDictionary<StringValue,BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        int idd = state.getIdd();


        logFile.println("id = " + String.valueOf(idd) );
        logFile.println(stack.toString2());
        logFile.println(symTbl.toString2());
        logFile.println(out.toString2() );
        logFile.println(fileTable.toStringFiletable() );
        logFile.println(heap.toString2());
        logFile.println("------------------------------------------------------------------------");


        logFile.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.programStates;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        programStates = newList;
    }
}
