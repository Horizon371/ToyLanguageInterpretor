package Model.Types;

import Model.Values.BoolValue;

public class  BoolType implements Type{

    public BoolValue defaultValue()
    {
        return new BoolValue(false);
    }

    @Override
    public boolean equals(Object another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    public String toString() { return "bool";}
}
