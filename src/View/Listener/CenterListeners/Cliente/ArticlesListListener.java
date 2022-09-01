package View.Listener.CenterListeners.Cliente;

import View.AppFrame;
import View.Panels.Center.Cliente.ArticlesListPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticlesListListener implements ActionListener {

    private AppFrame appFrame;
    private ArticlesListPanel articlesListPanel;

    public ArticlesListListener(ArticlesListPanel articlesListPanel, AppFrame appFrame){
        this.appFrame = appFrame;
        this.articlesListPanel = articlesListPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("aggiungi")){
            articlesListPanel.aggiungi();
        }else if(e.getActionCommand().equals("elimina")){
            articlesListPanel.elimina();
        }else if(e.getActionCommand().equals("feedback")){
            articlesListPanel.modificaFeedback();
        }else if(e.getActionCommand().equals("termina")){
            articlesListPanel.termina();
        }
    }
}
