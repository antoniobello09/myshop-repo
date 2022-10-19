package DAO.Interfacce;

import Model.Prenotazione;

import java.util.ArrayList;

public interface IPrenotazioneDAO {

    int add(Prenotazione prenotazione);
    int update(Prenotazione prenotazione);
    int delete(Prenotazione prenotazione);
    ArrayList<Prenotazione> findAllByProduct(int idProdotto);
    Prenotazione findByProdottoAcquisto(int idProdotto, int idAcquisto);

}
