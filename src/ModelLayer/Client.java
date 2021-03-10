package ModelLayer;

/**
 * Clasa in care se face maparea corespunzatore tabelului Client din baza de date.
 */
public class Client {
    private int idClient;
    private String nume;
    private String adresa;

    /**
     * Constructorul clasei. Aici se initializeaza valorile
     * @param idClient id-ul clientului
     * @param nume numele clientului
     * @param adresa adresa clientului
     */
    public Client(int idClient, String nume, String adresa) {
        this.idClient = idClient;
        this.nume=nume;
        this.adresa=adresa;
    }

    /**
     * Metoda care retunreaza id-ul clientului
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Metoda care retunreaza numele clientului
     */
    public String getNume() {
        return nume;
    }

    /**
     * Metoda care retunreaza adresa clientului
     */
    public String getAdresa() {
        return adresa;
    }
}
