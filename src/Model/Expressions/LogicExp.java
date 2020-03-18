package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public LogicExp(Exp e1, Exp e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException {

        Value v1,v2;

        v1 = e1.eval(table,heap);
        if( v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(table,heap);
            if( v2.getType().equals(new BoolType()))
            {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;

                boolean n1 = b1.getVal();
                boolean n2 = b2.getVal();

                switch(op)
                {
                    case "and":
                        return new BoolValue(n1 && n2);
                    case "or":
                        return new BoolValue(n1 || n2);
                    default:
                        throw new MyException("invalid operand");
                }

            }
            else throw new MyException("second operand is invalid");
        }else throw new MyException("first operand is invalid");
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type type1 = e1.typecheck(typeEnv);
        Type type2 = e2.typecheck(typeEnv);
        if( type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not boolean");
        } else
            throw new MyException("first operand is not boolean");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}

