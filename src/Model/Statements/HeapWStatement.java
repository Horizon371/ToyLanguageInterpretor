package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.ADT.MyIHeap;
import Model.Expressions.Exp;
import Model.PrgState.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapWStatement implements IStmt {

    String varName;
    Exp exp;

    public HeapWStatement(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "HeapW(" + varName + "," + exp.toString() + ") ";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, Value> table = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getHeap();

        if( table.isDefined(varName))
        {
            Value varValue = table.lookup(varName);
            if( varValue.getType() instanceof RefType)
            {
                RefValue varRValue = (RefValue)varValue;
                int address = varRValue.getAddr();
                if( heap.isDefined(address))
                {
                    RefType refType = (RefType)varRValue.getType();
                    Value expVal = exp.eval(table, heap);

                    if( expVal.getType().equals(refType.getInner()))
                    {
                        heap.update(address,expVal);
                    }else throw new MyException("types of the varName and the expression are not equal");
                }else throw new MyException("variable is not allocated in the heap");
            }else throw new MyException("variable is not of type Ref");
        }else throw new MyException("variable is not defined");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type varType = typeEnv.lookup(varName);
        Type expType = exp.typecheck(typeEnv);

        if (varType instanceof RefType) {
           if(varType.equals(new RefType(expType)))
           {
               return typeEnv;
           }else throw new MyException("the type of the variable and the type of the expression are different");
        }else throw new MyException("the variable is not of type Ref");
    }
}
