package Business.FactoryMethod;

import Model.Cliente;

public abstract class SendNotificationClient {

    public SendNotificationClient(String msg, Cliente c) {
        Notifica n = creaNotifica(msg, c);
        n.notificaUtente();
    }

    abstract protected Notifica creaNotifica(String msg, Cliente c);

}
