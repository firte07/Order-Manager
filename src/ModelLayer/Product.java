
package ModelLayer;

/**
 * Clasa in care se face maparea corespunzatore tabelului Product din baza de date.
 */
public class Product {
    private int idProduct;
    private String denumire;
    private int cantitate;
    private float pret;

    /**
     * Constructorul clasei. Initializeaza variabilele.
     * @param idProduct id-ulprodusului
     * @param denumire denumirea produsului
     * @param cantitate cantitatea produsului
     * @param pret  pretul produsului
     */
    public Product(int idProduct, String denumire, int cantitate, float pret) {
        this.idProduct = idProduct;
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    /**
     * Metoda care retunreaza id-ul produsului
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Metoda care retunreaza denumirea produsului
     */
    public String getDenumire() {
        return denumire;
    }

    /**
     * Metoda care retunreaza cantitatea produsului
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * Metoda care retunreaza pretul produsului
     */
    public float getPret() {
        return pret;
    }

    /**
     * Metoda care seteaza cantitatea produsului
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
