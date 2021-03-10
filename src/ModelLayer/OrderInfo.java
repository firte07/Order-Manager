
package ModelLayer;

/**
 * Clasa in care se face maparea corespunzatore tabelului OrderInfo din baza de date.
 */
public class OrderInfo {
    private int ID;
    private String nume;
    private String produs;
    private int cantitateTotala;
    private float pretTotal;

    /**
     * Constructorul clasei. Se face initializarea variabilelor.
     * @param ID id-ul comenzii nedublate
     * @param nume numele clientului
     * @param produs produsul comandat
     * @param cantitateTotala cantitatea totala comandata de un client
     * @param pretTotal pretul total al produselor comandate
     */
    public OrderInfo(int ID, String nume, String produs, int cantitateTotala, float pretTotal) {
        this.ID = ID;
        this.nume = nume;
        this.produs = produs;
        this.cantitateTotala = cantitateTotala;
        this.pretTotal = pretTotal;
    }

    /**
     * Metoda care retunreaza id-ul comenzii nedublate
     */
    public int getID() {
        return ID;
    }

    /**
     * Metoda care retunreaza numele clientului din comanda
     */
    public String getNume() {
        return nume;
    }

    /**
     * Metoda care retunreaza prdusul comandat
     */
    public String getProdus() {
        return produs;
    }

    /**
     * Metoda care retunreaza cantitatea totala comandata
     */
    public int getCantitateTotala() {
        return cantitateTotala;
    }

    /**
     * Metoda care retunreaza pretul total al produselor
     */
    public float getPretTotal() {
        return pretTotal;
    }

    /**
     * Metoda care seteaza cantitatea totala a  comenzii
     */
    public void setCantitateTotala(int cantitateTotala) {
        this.cantitateTotala = cantitateTotala;
    }

    /**
     * Metoda care seteaza pretul total al  comenzii
     */
    public void setPretTotal(float pretTotal) {
        this.pretTotal = pretTotal;
    }
}
