package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Lista {

    private int idLista;
    private String nome;
    private int idCliente;

    private ArrayList<Lista_has_Articolo> lista_has_articoli;

    public Lista() {
    }

    public Lista(int idLista, String nome, int idCliente) {
        this.idLista = idLista;
        this.nome = nome;
        this.idCliente = idCliente;
    }

    public Lista(String nome, int idCliente) {
        this.nome = nome;
        this.idCliente = idCliente;
    }

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


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

}
