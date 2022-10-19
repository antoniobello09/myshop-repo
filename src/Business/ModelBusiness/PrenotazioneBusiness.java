package Business.ModelBusiness;

import DAO.Classi.ArticoloDAO;
import DAO.Classi.Lista_has_ArticoloDAO;
import DAO.Classi.PrenotazioneDAO;
import DAO.Classi.SchedaProdottoDAO;
import Model.Acquisto;
import Model.Lista_has_Articolo;
import Model.Prenotazione;
import Model.SchedaProdotto;

import java.util.ArrayList;
import java.util.Iterator;

public class PrenotazioneBusiness {

    private static PrenotazioneBusiness instance;


    public static synchronized PrenotazioneBusiness getInstance() {
        if(instance == null) instance = new PrenotazioneBusiness();
        return instance;
    }

    private PrenotazioneBusiness() {}

    public void nuovoAcquisto(Acquisto acquisto){
        int idPuntoVendita = acquisto.getIdPuntoVendita();
        int idAcquisto = acquisto.getIdAcquisto();
        int idLista = acquisto.getIdLista();

        ArrayList<Lista_has_Articolo> articoliDaPrenotare = Lista_has_ArticoloDAO.getInstance().findAllListArticles(idLista);
        Iterator<Lista_has_Articolo> iteratorArticoli = articoliDaPrenotare.iterator();
        while(iteratorArticoli.hasNext()){
            Lista_has_Articolo articolo = iteratorArticoli.next();

            if(ArticoloDAO.getInstance().isProdotto(ArticoloDAO.getInstance().findById(articolo.getIdArticolo()))) {
                int idProdotto = articolo.getIdArticolo();
                int quantita = articolo.getQuantita();

                ArrayList<SchedaProdotto> schedeProdotto = SchedaProdottoDAO.getInstance().findAllByProduct(idProdotto);

                SchedaProdotto schedaProdottoScelto = PrenotazioneBusiness.getInstance().ricercaDisponibilitaTramitePuntoVendita(schedeProdotto, idPuntoVendita);
                if(schedaProdottoScelto != null) {
                    int differenza = schedaProdottoScelto.getDisponibilita() - quantita;
                    if (differenza >= 0) {
                        quantita = 0;
                        schedaProdottoScelto.setDisponibilita(differenza);
                        SchedaProdottoDAO.getInstance().update(schedaProdottoScelto);
                    } else {
                        schedaProdottoScelto.setDisponibilita(0);
                        SchedaProdottoDAO.getInstance().update(schedaProdottoScelto);
                        quantita = Math.abs(differenza);
                    }
                }

                if(quantita != 0) {
                    Iterator<SchedaProdotto> iteratorSchedeProdotto = schedeProdotto.iterator();
                    while (iteratorSchedeProdotto.hasNext()) {
                        SchedaProdotto schedaProdotto = iteratorSchedeProdotto.next();
                        int differenza = schedaProdotto.getDisponibilita() - quantita;
                        if (differenza >= 0) {
                            quantita = 0;
                            schedaProdotto.setDisponibilita(differenza);
                            SchedaProdottoDAO.getInstance().update(schedaProdotto);
                        } else {
                            schedaProdotto.setDisponibilita(0);
                            SchedaProdottoDAO.getInstance().update(schedaProdotto);
                            quantita = Math.abs(differenza);
                        }
                    }
                }

                if (quantita > 0){
                    Prenotazione prenotazione = new Prenotazione(idProdotto, quantita, idAcquisto);
                    PrenotazioneDAO.getInstance().add(prenotazione);
                }

            }
        }

    }


    public SchedaProdotto ricercaDisponibilitaTramitePuntoVendita(ArrayList<SchedaProdotto> schedeProdotto, int idPuntoVendita){
        SchedaProdotto schedaProdotto;

        Iterator<SchedaProdotto> iteratorSchedaProdotto = schedeProdotto.iterator();
        while(iteratorSchedaProdotto.hasNext()){
            schedaProdotto = iteratorSchedaProdotto.next();
            if(schedaProdotto.getIdPuntoVendita() == idPuntoVendita){
                return schedaProdotto;
            }
        }
        return null;
    }


    public void nuovoRifornimento(SchedaProdotto schedaProdotto){

        int idProdotto = schedaProdotto.getIdProdotto();
        int disponibilita = schedaProdotto.getDisponibilita();

        ArrayList<Prenotazione> prenotazioniProdotti = PrenotazioneDAO.getInstance().findAllByProduct(idProdotto);

        Iterator<Prenotazione> iteratorPrenotazione = prenotazioniProdotti.iterator();
        while(iteratorPrenotazione.hasNext()  && disponibilita > 0){

            Prenotazione prenotazione = iteratorPrenotazione.next();
            int differenza = disponibilita - prenotazione.getQuantita_prenotata();
            if( differenza >= 0 ){
                disponibilita = differenza;
                schedaProdotto.setDisponibilita(disponibilita);
                SchedaProdottoDAO.getInstance().update(schedaProdotto);
                PrenotazioneDAO.getInstance().delete(prenotazione);
            }else{
                disponibilita = 0;
                schedaProdotto.setDisponibilita(disponibilita);
                SchedaProdottoDAO.getInstance().update(schedaProdotto);
                prenotazione.setQuantita_prenotata(Math.abs(differenza));
                PrenotazioneDAO.getInstance().update(prenotazione);
            }

        }

    }

}
