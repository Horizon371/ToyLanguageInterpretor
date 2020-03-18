package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val){
        this.val = val;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException {return val;}

    @Override
    public String toString() {
        return  val.toString();
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return val.getType();
    }
}
