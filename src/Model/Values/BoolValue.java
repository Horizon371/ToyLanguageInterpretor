package Model.Values;

import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;


public class BoolValue implements Value{

    private boolean boolVal;
    public BoolValue(boolean val){ boolVal = val;}

    public Type getType(){ return (Type) new BoolType();}
    public boolean getVal(){return boolVal;}
    public String toString(){
        return String.valueOf(this.boolVal);
    }
}

