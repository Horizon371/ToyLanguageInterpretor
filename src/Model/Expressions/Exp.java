package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    Value eval(MyIDictionary<String,Value> table, MyIHeap<Integer,Value> heap) throws MyException;
}