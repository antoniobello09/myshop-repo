package Model;

import java.util.ArrayList;
import java.util.List;

public class Lista {

    private int idLista;
    private String nome;
    private int idCliente;
    private boolean acquistata;

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public boolean isAcquistata() {
        return acquistata;
    }

    public void setAcquistata(boolean acquistata) {
        this.acquistata = acquistata;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
