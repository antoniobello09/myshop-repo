package Model.Other;

import Model.Cliente;
import Model.Other.Notifica;

public class NotificaPush extends Notifica {

    public NotificaPush(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica push all'utente");
    }
}
