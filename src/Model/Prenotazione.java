package Model;

public class Prenotazione {

    private int idPrenotazione;
    private SchedaProdotto schedaProdotto;
    private int quantita_prenotata;
    private Cliente cliente;

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public SchedaProdotto getSchedaProdotto() {
        return schedaProdotto;
    }

    public void setSchedaProdotto(SchedaProdotto schedaProdotto) {
        this.schedaProdotto = schedaProdotto;
    }

    public int getQuantita_prenotata() {
        return quantita_prenotata;
    }

    public void setQuantita_prenotata(int quantita_prenotata) {
        this.quantita_prenotata = quantita_prenotata;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
