package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;

import Model.Values.Value;

public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    String op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp e1, Exp e2,  String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }



    public String toString()
    {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException {
        Value v1, v2;
        v1 = e1.eval(table, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(table, heap);
            if (v2.getType().equals(new IntType())) {

                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int n1 = i1.getVal();
                int n2 = i2.getVal();

                switch (op)
                {
                    case "+":
                        return new IntValue(n1 + n2);
                    case "-":
                        return new IntValue(n1 - n2);
                    case "*":
                        return new IntValue(n1 * n2);
                    case "/":
                        if (n2 == 0) throw new MyException("division by zero");
                        else return new IntValue(n1 / n2);
                    default:
                        throw new MyException("invalid operand");
                }

            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }

    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
            throw new MyException("second operand is not an integer");
        } else
        throw new MyException("first operand is not an integer");
    }
}
