package Model.Statements;

import Exceptions.MyException;
import Model.ADT.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Types.Type;

class NopStmt implements IStmt{

 public PrgState execute(PrgState state) throws MyException {
   return null;
 }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return null;
    }


}
