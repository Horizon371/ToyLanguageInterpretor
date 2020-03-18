package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExp implements Exp{

    private Exp exp1,exp2;
    private String op;

    public RelationalExp(Exp exp1, Exp exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }


    @Override
    public String toString() {
        return  exp1.toString() + " " + op + " " + exp2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException {

        Value val = exp1.eval(table, heap);
        Value val2 = exp2.eval(table, heap);

        if(val.getType().equals(new IntType()))
        {
            if(val2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)val;
                IntValue i2 = (IntValue)val2;

                int v1 = i1.getVal();
                int v2 = i2.getVal();

                if(op.equals("<"))
                    return new BoolValue(v1<v2);
                if(op.equals("<="))
                    return new BoolValue(v1<=v2);
                if(op.equals(">"))
                    return new BoolValue(v1>v2);
                if(op.equals(">="))
                    return new BoolValue(v1>=v2);
                if(op.equals("=="))
                    return new BoolValue(v1==v2);
                if(op.equals("!="))
                    return new BoolValue(v1!=v2);

            }else throw new MyException("second operand is not int");

        }else throw new MyException("first operand is not int");

        return null;
    }


    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=exp1.typecheck(typeEnv);
        typ2=exp2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }
}
