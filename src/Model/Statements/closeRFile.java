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
import java.io.IOException;

public class closeRFile implements IStmt{


    Exp exp;

    public closeRFile(Exp exp) {
        this.exp = exp;
    }


    @Override
    public String toString() {
        return "closeRFile(" + exp.toString()  + ") " ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> symtable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();
        Value val = exp.eval(symtable,heap);

        if( val.getType().equals( new StringType()))
        {
            StringValue stringValue=(StringValue)val;
            if( fileTable.isDefined(stringValue))
            {
                BufferedReader bufferedReader = fileTable.lookup(stringValue);
                bufferedReader.close();
                fileTable.delete(stringValue);
                return null;
            }else throw new MyException("value is not in the file table");
        }
        else throw new MyException("type is not a string");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typecheck(typeEnv);
        if (type.equals(new StringType())) {
            return typeEnv;
        } else throw new MyException("exp is not a string");
    }
}
