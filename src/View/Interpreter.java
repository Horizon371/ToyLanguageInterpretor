package View;

import Controller.Controller;
import Exceptions.MyException;
import Model.ADT.*;
import Model.Expressions.*;
import Model.PrgState.PrgState;
import Model.Statements.*;
import Model.Types.*;
import Model.Values.*;
import Repository.Repository;
import Repository.RepositotyInterface;

import java.io.BufferedReader;
import java.io.IOException;

class Interpreter {
    public static void main(String[] args) throws IOException, MyException {



            MyIStack<IStmt> stk = new MyStack<IStmt>();
            MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
            MyIList<Value> out = new MyList<Value>();
            MyIDictionary<StringValue, BufferedReader> fileTable= new MyDictionary<StringValue,BufferedReader>();
            MyIHeap<Integer,Value> heap = new MyHeap<Integer,Value>();

            IStmt ex1 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt( new VarDeclStmt("b",new IntType()),
                    new CompStmt(new AssignStmt("a",new ValueExp(new IntValue(2))),
                            new CompStmt(new AssignStmt("b",new ValueExp(new IntValue(5))),
                                    new CompStmt( new WhileStmt( new RelationalExp( new VarExp("a"), new VarExp("b"), "<"),
                                            new AssignStmt("a",new ArithExp(new VarExp("a"), new
                                                    ValueExp(new IntValue(1)), "+"))), new PrintStmt(new VarExp("a")))))));

            MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();
            ex1.typecheck(typeEnv1);
            PrgState prg1 = new PrgState(stk,symtbl,fileTable,out,heap,ex1);
            RepositotyInterface repo1 = new Repository("log1.txt");
            Controller ctr1 = new Controller(repo1);
            ctr1.addProgram(prg1);









            MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<String,Value>();
        MyIList<Value> out2 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable2= new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap2 = new MyHeap<Integer,Value>();


        IStmt ex2 = new CompStmt( new VarDeclStmt("v", new RefType( new IntType())),
                new CompStmt( new New("v", new ValueExp( new IntValue(20))),
                        new CompStmt(  new PrintStmt(new HeapRExp( new VarExp("v"))),
                                        new CompStmt( new HeapWStatement("v", new ValueExp( new IntValue(30))),
                                                new PrintStmt( new ArithExp(new HeapRExp( new VarExp("v")),new ValueExp( new IntValue(5)),"+"))))));

        MyIDictionary<String, Type> typeEnv2 = new MyDictionary<>();
        ex2.typecheck(typeEnv2);
        PrgState prg2 = new PrgState(stk2,symtbl2,fileTable2,out2,heap2,ex2);
        RepositotyInterface repo2 = new Repository("log2.txt");
        Controller ctr2 = new Controller(repo2);
        ctr2.addProgram(prg2);








        MyIStack<IStmt> stk3 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl3 = new MyDictionary<String,Value>();
        MyIList<Value> out3 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap3 = new MyHeap<Integer,Value>();

        IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new StringValue("this.txt"))), new CompStmt(new openRFile(
                new VarExp("varf")), new CompStmt(new VarDeclStmt("varc",
                new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new readFile(new VarExp("varf"), "varc"),
                                new CompStmt(new PrintStmt(new VarExp("varc")),new closeRFile(new VarExp("varf"))
                                ))))))));


        MyIDictionary<String, Type> typeEnv3 = new MyDictionary<>();
        ex3.typecheck(typeEnv3);
        PrgState prg3 = new PrgState(stk3,symtbl3,fileTable3,out3,heap3,ex3);
        RepositotyInterface repo3 = new Repository("log3.txt");
        Controller ctr3 = new Controller(repo3);
        ctr3.addProgram(prg3);








        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl4 = new MyDictionary<String,Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap4 = new MyHeap<Integer,Value>();


        IStmt ex4 = new CompStmt( new VarDeclStmt("v", new RefType( new RefType(new IntType()))),
                new CompStmt( new VarDeclStmt( "a", new RefType(new IntType())),
                        new CompStmt( new New("a", new ValueExp( new IntValue(10))),
                                new CompStmt( new New("v", new VarExp("a")),
                                        new CompStmt( new New("a", new ValueExp( new IntValue(30))),
                                            new CompStmt( new VarDeclStmt( "c", new RefType(new IntType())),
                                                new CompStmt( new New("c", new ValueExp( new IntValue(10))),
                                                        new CompStmt( new New("c", new ValueExp( new IntValue(20))),
                                                        new PrintStmt(new VarExp("v"))))))))));

        MyIDictionary<String, Type> typeEnv4 = new MyDictionary<>();
        ex4.typecheck(typeEnv4);
        PrgState prg4 = new PrgState(stk4,symtbl4,fileTable4,out4,heap4,ex4);
        RepositotyInterface repo4 = new Repository("log4.txt");
        Controller ctr4= new Controller(repo4);
        ctr4.addProgram(prg4);




        MyIStack<IStmt> stk5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl5 = new MyDictionary<String,Value>();
        MyIList<Value> out5 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap5 = new MyHeap<Integer,Value>();


        IStmt ex5 = new CompStmt( new VarDeclStmt("v", new RefType( new IntType())),
                            new CompStmt( new New("v",new ValueExp( new IntValue(20))),
                                    new CompStmt( new VarDeclStmt("a", new RefType(new RefType( new IntType()))),
                                         new CompStmt( new New("a",new VarExp( "v")),
                                                 new CompStmt( new New("v",new ValueExp( new BoolValue(true))),
                                                         new PrintStmt( new HeapRExp(new HeapRExp(new VarExp( "a")))))))));


        MyIDictionary<String, Type> typeEnv5 = new MyDictionary<>();
        ex5.typecheck(typeEnv5);
        PrgState prg5 = new PrgState(stk5,symtbl5,fileTable5,out5,heap5,ex5);
        RepositotyInterface repo5 = new Repository("log5.txt");
        Controller ctr5= new Controller(repo5);
        ctr5.addProgram(prg5);


        MyIStack<IStmt> stk6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl6 = new MyDictionary<String,Value>();
        MyIList<Value> out6 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap6 = new MyHeap<Integer,Value>();


        IStmt ex66=new CompStmt( new VarDeclStmt("c", new IntType()),
                new CompStmt(new HeapWStatement("a", new ValueExp(new IntValue(30))),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                new CompStmt( new PrintStmt(new VarExp("v")),
                                        new PrintStmt( new HeapRExp( new VarExp("a")))))));

        IStmt ex6 = new CompStmt( new VarDeclStmt("v", new IntType()),
                new CompStmt( new VarDeclStmt("a", new RefType( new IntType())),
                    new CompStmt(new AssignStmt("v", new ValueExp( new IntValue(10))),
                        new CompStmt( new New("a", new ValueExp(new IntValue(22))),
                            new CompStmt(new ForkStmt(ex66),
                                    new CompStmt( new PrintStmt(new VarExp("v")),
                                            new PrintStmt( new HeapRExp( new VarExp("a")))))))));


        MyIDictionary<String, Type> typeEnv6 = new MyDictionary<>();
        ex6.typecheck(typeEnv6);
        MyIDictionary<String, Type> typeEnv66 = new MyDictionary<>();
        ex66.typecheck(typeEnv6);

        PrgState prg6 = new PrgState(stk6,symtbl6,fileTable6,out6,heap6,ex6);
        RepositotyInterface repo6 = new Repository("log6.txt");
        Controller ctr6= new Controller(repo6);
        ctr6.addProgram(prg6);





        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.show();
    }
}
