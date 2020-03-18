package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException
    {return table.lookup(id);}

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv.lookup(id);
    }

    public String toString()
    {
        return id;
    }
 }

