package View;

import Controller.Controller;
import Exceptions.MyException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller controller;
    public RunExample(String key, String desc,Controller controller){
        super(key, desc);
        this.controller=controller;
    }
    @Override
    public void execute() throws IOException, MyException {
        try{
            controller.allStep(); }
        catch (MyException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
       }
    }
}
