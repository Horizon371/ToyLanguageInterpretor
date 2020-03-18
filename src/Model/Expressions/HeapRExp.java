package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapRExp implements Exp{

    Exp exp;

    public HeapRExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "heapR(" + exp + ")";
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Integer,Value> heap) throws MyException {

        Value val = exp.eval(table, heap);

        if(val.getType() instanceof RefType)
        {
            RefValue refValue = (RefValue)val;
            int address = refValue.getAddr();

            if(heap.isDefined(address))
            {
                return heap.lookup(address);

            }else throw new MyException("address doesn't exist in the heap");

        }else throw new MyException("expression is not a RefValue");
    }


    public Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type type=exp.typecheck(typeEnv);
        if (type instanceof RefType) {
            RefType refType =(RefType) type;
            return refType.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
