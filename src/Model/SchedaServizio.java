package Model;

public class SchedaServizio {

    private int idSchedaServizio;
    private Servizio servizio;
    private FornitoreServizi fornitoreServizi;

    public int getIdSchedaServizio() {
        return idSchedaServizio;
    }

    public void setIdSchedaServizio(int idSchedaServizio) {
        this.idSchedaServizio = idSchedaServizio;
    }

    public Servizio getServizio() {
        return servizio;
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

    public FornitoreServizi getFornitoreServizi() {
        return fornitoreServizi;
    }

    public void setFornitoreServizi(FornitoreServizi fornitoreServizi) {

        this.fornitoreServizi = fornitoreServizi;
    }
}
