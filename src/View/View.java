package View;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.ArithExp;
import Model.Expressions.VarExp;
import Model.PrgState.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import Model.Expressions.ValueExp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;


public class View {


    Controller controller;
    PrgState programState;
    Scanner keyboard = new Scanner(System.in);

    public IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

    public IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
            new CompStmt(new VarDeclStmt("b",new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                            ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), "*"), "+")),
                            new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new
                                    ValueExp(new IntValue(1)), "+")), new PrintStmt(new VarExp("b"))))));

    public IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
            new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
            IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                                                                              VarExp("v"))))));


    public View(Controller controller) {
        this.controller = controller;
    }


    private void initializeProgram(IStmt statement)
    {
        MyIStack<IStmt> stack = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTbl = new MyDictionary<String, Value>();
        MyIList<Value> out = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable= new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap = new MyHeap<Integer,Value>();
        programState = new PrgState(stack,symTbl,fileTable,out,heap,statement);
        controller.addProgram(programState);
    }

    public void selectProgram() throws MyException {
        System.out.println("1 - int v; v=2;Print(v)\n2 - int a;int b; a=2+3*5;b=a+1;Print(b) \n3 - bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v) \n ");

        String filePath = keyboard.nextLine();

        controller.addFilePath(filePath);
        int input = Integer.parseInt(keyboard.nextLine());

        if(input == 1)
            initializeProgram(ex1);
        else if(input == 2)
            initializeProgram(ex2);
        else if(input == 3)
            initializeProgram(ex3);
        else if(input == 4)
            throw  new MyException("exit");
        else
            throw new MyException("input is not valid");
    }


    public int run() throws MyException {

            try {
                selectProgram();
                controller.allStep();
            } catch (MyException | IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                if(e.getMessage().equals("exit"))
                    return 0;
            }

            return 0;
    }



}
