package Controller;

import Exceptions.MyException;
import Model.ADT.*;
import Model.PrgState.PrgState;
import Model.Statements.IStmt;
import Model.SymbolTableElement;
import Model.Types.RefType;
import Model.HeapTableElement;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.RepositotyInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class ControllerView {

    PrgState prg;
    RepositotyInterface repo;
    ExecutorService executor;
    ObservableList<SymbolTableElement> symTableElements = FXCollections.observableArrayList();
    ObservableList<HeapTableElement> heapElements = FXCollections.observableArrayList();
    ObservableList<Integer> prgStateIds = FXCollections.observableArrayList();
    int currentProgramState;



    @FXML
    private TextField nrOfPrgStates;

    @FXML
    private ListView<Integer> programStatesView;

    @FXML
    private ListView<String>  fileTableView;

    @FXML
    private ListView<String>  outputView;

    @FXML
    private ListView<String>  exeStackView;

    @FXML
    private TableView<HeapTableElement> heapTableView;

    @FXML
    private TableView<SymbolTableElement> symbolTableView;

    @FXML
    private TableColumn<String,String> symVarNameCol;

    @FXML
    private TableColumn<String,String> symValCol;

    @FXML
    public TableColumn<String, String> heapAddress;

    @FXML
    public TableColumn<String, String> heapValue;

    public void initialize()
    {
        symVarNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        symValCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        heapAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public void initData(IStmt statement)
    {
        //        MyIStack<IStmt> stk = new MyStack<IStmt>();
//        MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
//        MyIList<Value> out = new MyList<Value>();
//        MyIDictionary<StringValue, BufferedReader> fileTable= new MyDictionary<StringValue,BufferedReader>();
//        MyIHeap<Integer,Value> heap = new MyHeap<Integer,Value>();
//

    }

    public void setRepo(RepositotyInterface repo) {
        this.repo = repo;
    }

    public void buttonProgramSelectionHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ViewSelect.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    public void buttonClickHandler(ActionEvent event) throws InterruptedException, IOException, MyException {
        allStep();
        updateContainers();
    }

    public void prgStateListViewClickHandler() throws InterruptedException, IOException, MyException {
        currentProgramState = programStatesView.getSelectionModel().getSelectedItem();
        updatePrgState();
        updateSymAndExecTable();
    }


    //public void updateEx

    public void updatePrgState(){
        List<PrgState> prgStates = repo.getPrgStates();
        for( PrgState potentialPrgState : prgStates)
        {
            if(potentialPrgState.idd == currentProgramState) {
                prg = potentialPrgState;
                break;
            }
        }
    }

    public void updateSymAndExecTable()
    {
        List<PrgState> prgStates = repo.getPrgStates();
        if(prgStates.size() == 1)
            prg = prgStates.get(0);

        MyIStack<IStmt> exeStack = prg.getStack();
        MyIDictionary<String, Value> symTable = prg.getSymTable();
        MyIList<Value> out = prg.getOut();
        MyIDictionary<StringValue, BufferedReader> fileTable = prg.getFileTable();
        MyIHeap<Integer,Value> heap = prg.getHeap();


        exeStackView.getItems().clear();
        exeStackView.getItems().addAll(exeStack.toArray());

        symTableElements.clear();
        for(Map.Entry<String, Value> entry : symTable.getContent().entrySet())
        {
            SymbolTableElement element = new SymbolTableElement(entry.getKey(),entry.getValue());
            symTableElements.add(element);
        }
        symbolTableView.setItems(symTableElements);
    }

    public void updateContainers() throws InterruptedException, IOException, MyException {

        List<PrgState> prgStates = repo.getPrgStates();
        if(prgStates.size() == 1)
            prg = prgStates.get(0);

        MyIStack<IStmt> exeStack = prg.getStack();
        MyIDictionary<String, Value> symTable = prg.getSymTable();
        MyIList<Value> out = prg.getOut();
        MyIDictionary<StringValue, BufferedReader> fileTable = prg.getFileTable();
        MyIHeap<Integer,Value> heap = prg.getHeap();


        exeStackView.getItems().clear();
        exeStackView.getItems().addAll(exeStack.toArray());

        outputView.getItems().clear();
        outputView.getItems().addAll(out.toString().split(","));

        fileTableView.getItems().clear();
        fileTableView.getItems().addAll(fileTable.toStringFiletable2().split(";"));


        symTableElements.clear();
        for(Map.Entry<String, Value> entry : symTable.getContent().entrySet())
        {
            SymbolTableElement element = new SymbolTableElement(entry.getKey(),entry.getValue());
            symTableElements.add(element);
        }
        symbolTableView.setItems(symTableElements);


        heapTableView.getItems().clear();
        heapElements.clear();
        for(Map.Entry<Integer, Value> entry : heap.getContent().entrySet())
        {
            HeapTableElement element = new HeapTableElement(entry.getKey(),entry.getValue());
            heapElements.add(element);
        }
        heapTableView.setItems(heapElements);


        nrOfPrgStates.setText(String.valueOf(prgStates.size()));

        programStatesView.getItems().clear();
        for(PrgState prgState  :  prgStates  )
        {
            prgStateIds.add(prgState.idd);
        }
        programStatesView.setItems(prgStateIds);

    }

//    public void addProgram(IStmt stmt)
//    {
//        MyIStack<IStmt> stk = new MyStack<IStmt>();
//        MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
//        MyIList<Value> out = new MyList<Value>();
//        MyIDictionary<StringValue, BufferedReader> fileTable= new MyDictionary<StringValue,BufferedReader>();
//        MyIHeap<Integer,Value> heap = new MyHeap<Integer,Value>();
//
//        PrgState prg = new PrgState(stk,symtbl,fileTable,out,heap,stmt);
//        repo.addProgram(prg);
//        updatePrgState();
//    }
    public void addProgram(PrgState program)
    {
        repo.addProgram(program);
        currentProgramState = 1;
        updatePrgState();
    }

    public boolean checkElement(Map.Entry<Integer, Value> e, HashMap<Integer, Value> heap, List<Integer> symTableAddr) {
        if( symTableAddr.contains(e.getKey()))
            return true;


        Integer key = e.getKey();

        for( Map.Entry<Integer, Value> el : heap.entrySet()) {

            Value elVal = el.getValue();
            if (elVal.getType() instanceof RefType) {
                RefValue refValue = (RefValue) elVal;
                while (heap.get(refValue.getAddr()) instanceof RefValue) {
                    refValue = (RefValue) heap.get(refValue.getAddr());
                }

                if( refValue.getAddr() == key)
                    return true;
            }
        }

        return false;
    }

    Map<Integer,  Value> unsafeGarbageCollector(List<Integer> symTableAddr, HashMap<Integer, Value> heap){
        return heap.entrySet().stream().filter(e->checkElement(e, heap, symTableAddr))
                .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void runOneStepForAll(List<PrgState> prgList ) throws InterruptedException{
       // List<PrgState> prgList = repo.getPrgStates();
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(p -> p!=null).collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        repo.setPrgList(prgList);
    }

    public void allStep() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        prgList.forEach(p -> p.getHeap().setContent(unsafeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values()), p.getHeap().getContent())));
        runOneStepForAll(prgList);
        prgList = removeCompletedPrg(repo.getPrgList());

        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

} 
