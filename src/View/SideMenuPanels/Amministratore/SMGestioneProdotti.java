package View.SideMenuPanels.Amministratore;

import View.AppFrame;
import View.Listener.SideMenuListeners.Amministratore.SMGestioneProdottiListener;

import javax.swing.*;
import java.awt.*;

public class SMGestioneProdotti extends JPanel {

    JButton aggiungiProdotto = new JButton("Aggiungi prodotto");
    JButton modificaProdotto = new JButton("Modifica/Elimina prodotto");
    JButton aggiungiProdottoComposito = new JButton("Aggiungi un prodotto composito");
    JButton modificaProdottoComposito = new JButton("Modifica/Elimina un prodotto composito");
    JButton aggiungiCategoriaP = new JButton("Aggiungi una categoria prodotto");
    JButton modificaCategoriaP = new JButton("Modifica/Elimina una categoria prodotto");
    JButton aggiungiProduttore = new JButton("Aggiungi un produttore");
    JButton modificaProduttore = new JButton("Modifica/Elimina un produttore");
    JButton indietro = new JButton("Indietro");
    SMGestioneProdottiListener smGestioneProdottiListener;


    public SMGestioneProdotti(AppFrame appFrame){
        setLayout(new GridLayout(20,1,0,15));
        aggiungiProdotto.setPreferredSize(new Dimension(255,40));
        smGestioneProdottiListener = new SMGestioneProdottiListener(appFrame);

        aggiungiProdotto.addActionListener(smGestioneProdottiListener);
        modificaProdotto.addActionListener(smGestioneProdottiListener);
        aggiungiProdottoComposito.addActionListener(smGestioneProdottiListener);
        modificaProdottoComposito.addActionListener(smGestioneProdottiListener);
        aggiungiCategoriaP.addActionListener(smGestioneProdottiListener);
        modificaCategoriaP.addActionListener(smGestioneProdottiListener);
        aggiungiProduttore.addActionListener(smGestioneProdottiListener);
        modificaProduttore.addActionListener(smGestioneProdottiListener);
        indietro.addActionListener(smGestioneProdottiListener);

        aggiungiProdotto.setActionCommand("addProduct");
        modificaProdotto.setActionCommand("modifyProduct");
        aggiungiProdottoComposito.setActionCommand("addProductC");
        modificaProdottoComposito.setActionCommand("modifyProductC");
        aggiungiCategoriaP.setActionCommand("addCategoryP");
        modificaCategoriaP.setActionCommand("modifyCategoryP");
        aggiungiProduttore.setActionCommand("addProduttore");
        modificaProduttore.setActionCommand("modifyProduttore");
        indietro.setActionCommand("indietro");

        add(aggiungiProdotto);
        add(modificaProdotto);
        add(aggiungiProdottoComposito);
        add(modificaProdottoComposito);
        add(aggiungiCategoriaP);
        add(modificaCategoriaP);
        add(aggiungiProduttore);
        add(modificaProduttore);
        add(indietro);

    }

}
