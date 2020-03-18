package Model.Types;

import Model.Values.IntValue;

public class IntType implements Type{

    @Override
    public boolean equals(Object another){
        if (another instanceof IntType)
            return true;
        else
            return false;
    }

    public IntValue defaultValue()
    {
        return new IntValue(0);
    }

    public String toString() { return "int";}
}
