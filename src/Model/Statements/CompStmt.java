package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIStack;
import Model.PrgState.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt( IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }

    public String toString() {
        return ""+first.toString() + "; " + second.toString()+"";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStack();
        stk.push(second);
        stk.push(first);
        return null;
    }


    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return second.typecheck(first.typecheck(typeEnv));
    }


}