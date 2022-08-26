package Model.Other;

import Model.Cliente;
import Model.Other.Notifica;

public class NotificaEMail extends Notifica {

    public NotificaEMail(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica email all'utente");
    }
}
