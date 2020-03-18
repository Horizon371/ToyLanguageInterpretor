package Model;

import Model.Values.Value;

public class HeapTableElement {

    public Integer address;
    public Value value;

    public HeapTableElement(Integer address, Value value) {
        this.address = address;
        this.value = value;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
