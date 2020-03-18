package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value{
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public String toString() {
        return locationType.toString() + "->" + String.valueOf(address);

    }

    public int getAddr() {return address;}
    public Type getType() { return new RefType(locationType);}

}
