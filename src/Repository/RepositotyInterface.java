package Repository;

import Exceptions.MyException;
import Model.PrgState.PrgState;

import java.io.IOException;
import java.util.List;

public interface RepositotyInterface {

    public void addProgram(PrgState programState);
   // PrgState getCrtPrg();
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newList);
    void addFilePath(String filePath);
    List<PrgState> getPrgStates();
}
