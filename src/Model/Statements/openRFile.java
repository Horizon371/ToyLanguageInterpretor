package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.Exp;
import Model.PrgState.PrgState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {

    private Exp exp;

    public openRFile(Exp exp) {
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIDictionary<String,Value> symtable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        Value val = exp.eval(symtable, heap);
        if(val.getType().equals(new StringType()))
        {
            StringValue stringValue=(StringValue)val;
            if(fileTable.isDefined(stringValue))
                throw new MyException("key already exists");
            else
            {
                BufferedReader bufferedReader = new BufferedReader( new FileReader(stringValue.getVal()));
                fileTable.add(stringValue,bufferedReader);
                return null;
            }

        }
        else throw new MyException("type is not StringType");

    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type type = exp.typecheck(typeEnv);
        if( type.equals(new StringType()))
        {
            return typeEnv;
        }else throw new MyException("type is not StringType");
    }


    public String toString(){
        return "openRFile( "+exp.toString() + " )";
    }
}
