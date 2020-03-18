package Model;

import Model.PrgState.PrgState;
import Model.Statements.IStmt;
import Repository.RepositotyInterface;

public class ListViewProgram {

    RepositotyInterface repo;
    PrgState prgState;
    IStmt stmt;

    public ListViewProgram(RepositotyInterface repo, PrgState prgState, IStmt stmt) {
        this.repo = repo;
        this.prgState = prgState;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return stmt.toString();
    }


    public RepositotyInterface getRepo() {
        return repo;
    }

    public PrgState getPrgState() {
        return prgState;
    }
}
