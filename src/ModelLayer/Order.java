
package ModelLayer;

/**
 * Clasa in care se face maparea corespunzatore tabelului Order din baza de date.
 */
public class Order {
    private int idOrder;
    private String name;
    private String prod;
    private int cantitate;

    /**
     * Constructorul clasei. Aici se initializeaza valorile.
     * @param idOrder id-ul comenzii
     * @param numeClient numele clientului care a facut comanda
     * @param numeProdus  produsul comandat de catre persoana
     * @param cantitate cantitatea comandata
     */
    public Order(int idOrder, String numeClient, String numeProdus,int cantitate) {
        this.idOrder = idOrder;
        this.name = numeClient;
        this.prod=numeProdus;
        this.cantitate=cantitate;

    }

    /**
     * Metoda care retunreaza id-ul comenzii
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * Metoda care retunreaza numele clientului din comanda
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda care retunreaza cantitatea comenzii
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * Metoda care retunreaza numele produsului din comanda
     */
    public String getProd() {
        return prod;
    }
}
