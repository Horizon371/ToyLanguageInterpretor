package Model;

import Model.Values.Value;

public class SymbolTableElement {
    public String name;
    public Value value;

    public SymbolTableElement(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
