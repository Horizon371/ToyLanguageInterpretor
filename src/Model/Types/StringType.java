package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type {

    public StringValue defaultValue()
    {
        return new StringValue("");
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof StringType)
            return true;
        else
            return false;
    }

    public String toString() { return "string";}
}
