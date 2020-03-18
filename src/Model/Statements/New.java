package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.Exp;
import Model.PrgState.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.IOException;

public class New implements IStmt{

    String varName;
    Exp exp;

    public New(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }


    @Override
    public String toString() {
        return "New " + varName + "(" + exp + ")";

    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symtable = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getHeap();

        if( symtable.isDefined(varName))
        {
            RefValue v = (RefValue)symtable.lookup(varName);
            if( v.getType() instanceof RefType)
            {
                RefType ref = (RefType)v.getType();
                Value v2 = exp.eval(symtable,heap);

                if( v2.getType().equals(ref.getInner()))
                {
                    int address = heap.add(v2);
                    symtable.update(varName,new RefValue(address, v2.getType()));
                }
                else throw new MyException("types are not equal");


            }else throw new MyException("type is not RefType");

        } else throw new MyException("there aren't any variables with the name: " + varName);


        return null;
    }


    public MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

}
