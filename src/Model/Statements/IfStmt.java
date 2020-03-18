package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.ADT.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;


    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}
    public String toString(){ return " IF("+ exp.toString()+") THEN{" +thenS.toString()
            +"} ELSE{"+elseS.toString()+"} ";}


    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stack = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getHeap();

        Value v1 = exp.eval(symTbl,heap);
        if(((BoolValue)v1).getVal())
            thenS.execute(state);
        else
            elseS.execute(state);

        return null;
    }

    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv);
            elseS.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
}
