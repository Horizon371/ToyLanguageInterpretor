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
import java.time.chrono.IsoChronology;

public class WhileStmt implements IStmt{

    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }


    @Override
    public String toString() {
        return "while(" + exp.toString()  + ") do (" + stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIHeap<Integer,Value> heap = state.getHeap();
        MyIDictionary<String, Value> symtable = state.getSymTable();
        MyIStack<IStmt> stack = state.getStack();

        Value val = exp.eval(symtable, heap);

        if( val instanceof BoolValue)
        {
            BoolValue bolVal = (BoolValue)val;
            if( bolVal.getVal())
            {
                stack.push(new WhileStmt(exp,stmt));
                stmt.execute(state);
            }
        }else throw new MyException("expresion is not of boolean type");

        return null;
    }


    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }
}
