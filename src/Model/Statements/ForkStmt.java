package Model.Statements;

import Exceptions.MyException;
import Model.ADT.*;
import Model.PrgState.PrgState;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class ForkStmt implements IStmt{

    private IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return " FORK{" + statement + "}";
    }

    public PrgState execute(PrgState state) throws MyException, IOException {


        MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<String,Value>();
        symtbl2.setContent((HashMap<String, Value>) state.getSymTable().getContent2().clone());
        MyIList<Value> out2 = state.getOut();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = state.getFileTable();
        MyIHeap<Integer, Value> heap2 = state.getHeap();

        return new PrgState(stk2, symtbl2, fileTable2, out2, heap2, statement);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException{
        statement.typecheck(typeEnv);
        return typeEnv;
    }


}
