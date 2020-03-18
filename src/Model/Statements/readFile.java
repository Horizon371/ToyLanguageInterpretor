package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.Exp;
import Model.PrgState.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {

    Exp exp;
    String varName;

    public readFile(Exp exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> symtable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getHeap();

        if(symtable.isDefined(varName))
        {

            Type type = symtable.lookup(varName).getType();
            if( !type.equals(new IntType()))
                throw new MyException("type is not int");
            StringValue val = (StringValue)exp.eval(symtable,heap);

            if(fileTable.isDefined(val))
            {
                BufferedReader bufferedReader = fileTable.lookup(val);
                String line = bufferedReader.readLine();

                IntValue value;
                if(line == null)
                    value = new IntValue(0);
                else
                {
                    value = new IntValue(Integer.parseInt(line));
                }

                symtable.update(varName,value);

            }
            else throw new MyException("value doesn't exist");

        }
        else
            throw new MyException("the key doesn't exist");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = typeEnv.lookup(varName);
        Type type2 = exp.typecheck(typeEnv);
        if (type.equals(new IntType())) {
            if (type2.equals(new StringType())) {
                return typeEnv;
            } else throw new MyException("exp is not a string");
        } else throw new MyException("type is not int");
    }

    @Override
    public String toString() {
        return "readFile( " + exp + ", " + varName + " ) ";
    }
}
