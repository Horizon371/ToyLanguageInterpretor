package Controller;

import Exceptions.MyException;
import Model.ADT.MyIStack;
import Model.PrgState.PrgState;
import Model.Statements.IStmt;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.RepositotyInterface;

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

import static java.util.Locale.filter;

public class Controller {

    RepositotyInterface repo;
    ExecutorService executor;

    public Controller(RepositotyInterface repo) {
        this.repo = repo;
    }


    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
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

    public void addProgram(PrgState program)
    {
        repo.addProgram(program);
    }

    public boolean checkElement( Map.Entry<Integer, Value> e, HashMap<Integer, Value> heap, List<Integer> symTableAddr)
    {
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

//        return heap.entrySet().stream()
//                .filter(e->symTableAddr.contains(e.getKey())
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return heap.entrySet().stream().filter(e->checkElement(e, heap, symTableAddr))
                   .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }



    public void allStep() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){

            prgList.forEach(p -> p.getHeap().setContent(unsafeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values()), p.getHeap().getContent())));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }


    public void addFilePath(String filePath) {

        repo.addFilePath(filePath);
    }
}
