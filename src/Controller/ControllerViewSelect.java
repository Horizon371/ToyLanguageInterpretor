package Controller;

import Model.ADT.*;
import Model.Expressions.*;
import Model.ListViewProgram;
import Model.PrgState.PrgState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.Repository;
import Repository.RepositotyInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;

public class ControllerViewSelect {

    public ListView<ListViewProgram> programListView;
    PrgState prg1,prg2,prg3,prg4,prg5,prg6;

    @FXML
    Button runButton;

    public void initialize()
    {
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

        prg1 = new PrgState(stk,symtbl,fileTable,out,heap,ex1);
        RepositotyInterface repo1 = new Repository("log1.txt");
        ListViewProgram p1 = new ListViewProgram(repo1,prg1,ex1);



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

        prg4 = new PrgState(stk4,symtbl4,fileTable4,out4,heap4,ex4);
        RepositotyInterface repo4 = new Repository("log4.txt");
        ListViewProgram p4 = new ListViewProgram(repo4,prg4,ex4);


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

        prg3 = new PrgState(stk3,symtbl3,fileTable3,out3,heap3,ex3);
        RepositotyInterface repo3 = new Repository("log3.txt");
        ListViewProgram p3 = new ListViewProgram(repo3,prg3,ex3);

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

        prg2 = new PrgState(stk2,symtbl2,fileTable2,out2,heap2,ex2);
        RepositotyInterface repo2 = new Repository("log2.txt");
        ListViewProgram p2 = new ListViewProgram(repo2,prg2,ex2);

        MyIStack<IStmt> stk5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl5 = new MyDictionary<String,Value>();
        MyIList<Value> out5 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap5 = new MyHeap<Integer,Value>();


        IStmt ex5 = new CompStmt( new VarDeclStmt("v", new RefType( new IntType())),
                new CompStmt( new New("v",new ValueExp( new IntValue(20))),
                        new CompStmt( new VarDeclStmt("a", new RefType(new RefType( new IntType()))),
                                new CompStmt( new New("a",new VarExp( "v")),
                                        new CompStmt( new New("v",new ValueExp( new IntValue(30))),
                                                new PrintStmt( new HeapRExp(new HeapRExp(new VarExp( "a")))))))));

        prg5 = new PrgState(stk5,symtbl5,fileTable5,out5,heap5,ex5);
        RepositotyInterface repo5 = new Repository("log5.txt");
        ListViewProgram p5 = new ListViewProgram(repo5,prg5,ex5);




        MyIStack<IStmt> stk6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl6 = new MyDictionary<String,Value>();
        MyIList<Value> out6 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<StringValue,BufferedReader>();
        MyIHeap<Integer,Value> heap6 = new MyHeap<Integer,Value>();

        IStmt ex667= new CompStmt( new VarDeclStmt("c", new IntType()),
                new CompStmt(new HeapWStatement("a", new ValueExp(new IntValue(30))),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                new CompStmt( new PrintStmt(new VarExp("v")),
                                        new PrintStmt( new HeapRExp( new VarExp("a")))))));

        IStmt ex6 = new CompStmt( new VarDeclStmt("v", new IntType()),
                new CompStmt( new VarDeclStmt("a", new RefType( new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp( new IntValue(10))),
                                new CompStmt( new New("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(ex667),
                                                new CompStmt( new PrintStmt(new VarExp("v")),
                                                        new PrintStmt( new HeapRExp( new VarExp("a")))))))));



        prg6 = new PrgState(stk6,symtbl6,fileTable6,out6,heap6,ex6);
        RepositotyInterface repo6 = new Repository("log6.txt");
        ListViewProgram p6 = new ListViewProgram(repo6,prg6,ex6);


        programListView.getItems().addAll(p1,p2,p3,p4,p5,p6);

    }


    public void handleRunButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View.fxml"));
        Parent mainView = loader.load();

        ControllerView viewController = loader.getController();


        viewController.setRepo(programListView.getSelectionModel().getSelectedItem().getRepo());
        viewController.addProgram(programListView.getSelectionModel().getSelectedItem().getPrgState());


        Scene mainViewScene = new Scene(mainView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainViewScene);
        window.show();
    }

}
