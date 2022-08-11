package View.Listener.Cliente;

import View.AppFrame;
import View.Center.Cliente.DettagliListaPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliListaListener implements ActionListener {

    private AppFrame appFrame;
    private DettagliListaPanel dettagliListaPanel;

    public DettagliListaListener(DettagliListaPanel dettagliListaPanel, AppFrame appFrame){
        this.dettagliListaPanel = dettagliListaPanel;
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pdf")){
            dettagliListaPanel.mandaPdf();
        }else if(e.getActionCommand().equals("feedback")){
            dettagliListaPanel.commenta();
        }
    }
}
