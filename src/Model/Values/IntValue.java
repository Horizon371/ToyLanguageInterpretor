package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value{
    int val;

    public IntValue(int v){val=v;}
    public int getVal() {return val;}
    public String toString() { return String.valueOf(val); }
    public Type getType() { return new IntType();}
}
