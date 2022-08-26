package Model.Other;

import Model.Cliente;

public abstract class Notifica {

    String msg;
    Cliente c;

    public Notifica(String msg, Cliente c) {
        this.msg = msg;
        this.c = c;
    }

    public abstract void notificaUtente();
}
