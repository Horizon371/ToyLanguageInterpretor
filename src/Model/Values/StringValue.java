package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{

    private String val;

    public StringValue(String val)
    {
        this.val = val;
    }

    public Type getType(){ return (Type) new StringType();}
    public String getVal(){return this.val;}
    public String toString(){
        return val;
    }

}
