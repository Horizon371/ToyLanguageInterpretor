package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.PrgState.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;


    public VarDeclStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if( symTbl.isDefined(name) )
            throw new MyException("variable is already declared");
        else {
            symTbl.update(name,type.defaultValue());
        }


        return null;

    }

    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        typeEnv.add(name,type);
        return typeEnv;
    }


    @Override
    public String toString() {
        return type + " " + name;
    }
}