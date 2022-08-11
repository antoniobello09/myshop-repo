package Model;

public class NotificaSMS extends Notifica {

    public NotificaSMS(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    public void notificaUtente() {
        System.out.println("Invio una notifica SMS all'utente");
    }
}
