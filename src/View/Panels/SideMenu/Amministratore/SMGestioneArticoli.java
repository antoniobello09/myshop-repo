package View.Panels.SideMenu.Amministratore;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestioneArticoliListener;

import javax.swing.*;
import java.awt.*;

//Pannello che contiene i pulsanti per la gestione degli articoli
public class SMGestioneArticoli extends JPanel {

    JButton btnCreaProdotto = new JButton("Crea Prodotto");
    JButton btnCreaProdottoComposito = new JButton("Crea un prodotto composito");
    JButton btnCreaServizio = new JButton("Crea Servizio");
    JButton btnEliminaArticolo = new JButton("Elimina un articolo");
    JButton btnModificaArticolo = new JButton("Modifica un Articolo");
    JButton btnCreaFornitore = new JButton("Crea un fornitore");
    JButton btnCreaCategoria = new JButton("Crea una categoria");
    JButton indietro = new JButton("Indietro");
    SMGestioneArticoliListener smGestioneArticoliListener;


    public SMGestioneArticoli(AppFrame appFrame){
        setLayout(new GridLayout(20,1,0,15));
        btnCreaProdotto.setPreferredSize(new Dimension(255,40));
        smGestioneArticoliListener = new SMGestioneArticoliListener(appFrame);

        btnCreaProdotto.addActionListener(smGestioneArticoliListener);
        btnCreaProdottoComposito.addActionListener(smGestioneArticoliListener);
        btnCreaServizio.addActionListener(smGestioneArticoliListener);
        btnEliminaArticolo.addActionListener(smGestioneArticoliListener);
        btnModificaArticolo.addActionListener(smGestioneArticoliListener);
        btnCreaFornitore.addActionListener(smGestioneArticoliListener);
        btnCreaCategoria.addActionListener(smGestioneArticoliListener);
        indietro.addActionListener(smGestioneArticoliListener);

        btnCreaProdotto.setActionCommand("createProduct");
        btnCreaProdottoComposito.setActionCommand("createCompositeProduct");
        btnCreaServizio.setActionCommand("createService");
        btnEliminaArticolo.setActionCommand("deleteArticle");
        btnModificaArticolo.setActionCommand("modifyArticle");
        btnCreaFornitore.setActionCommand("createSupplier");
        btnCreaCategoria.setActionCommand("createCategory");
        indietro.setActionCommand("indietro");

        add(btnCreaProdotto);
        add(btnCreaProdottoComposito);
        add(btnCreaServizio);
        add(btnEliminaArticolo);
        add(btnModificaArticolo);
        add(btnCreaFornitore);
        add(btnCreaCategoria);
        add(indietro);

    }

}
