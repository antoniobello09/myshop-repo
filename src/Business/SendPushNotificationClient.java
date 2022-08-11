package Business;

import Model.Cliente;
import Model.Notifica;
import Model.NotificaPush;

public class SendPushNotificationClient extends SendNotificationClient {

    public SendPushNotificationClient(String msg, Cliente c) {
        super(msg, c);
    }

    @Override
    protected Notifica creaNotifica(String msg, Cliente c) {
        return new NotificaPush(msg, c);
    }
}
