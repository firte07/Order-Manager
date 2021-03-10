package Presentation;
import ModelLayer.Client;
import ModelLayer.Order;
import ModelLayer.OrderInfo;
import ModelLayer.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static BLL.ClientBLL.deleteClientBll;
import static BLL.ClientBLL.insereazaClientBll;
import static BLL.ProductBLL.*;
import static DAO.ClientDAO.deleteClient;
import static DAO.ClientDAO.insereazaClient;
import static DAO.ProductDAO.*;
import static Presentation.Generater.generareBill;

/**
 * Clasa in care se face parsarea textului din fisierul .txt. Metoda principala "doParse()" stabileste ce caz regasim, pentru executie, iar
 * dupa aceea apeleaza metoda specifica pentru a face restul parsarii si a executa comanda.
 */
public class Parse {
    private String commandName;
    private String numeClient;
    private String adresaClient;
    private String numeProdus;
    private int cantitateProdus;
    private float pretProdus;

    private File myObjFile;
    private Scanner myReader;

    private List<Client> clienti;
    private List<Product> produse;
    private List<OrderInfo> orderItems;

    private static int  nrClienti =0;
    private static int nrProduse=0;
    private static int nrOrders=0;

    /**
     * Constructorul clasei. Aici se instantiaza listele specifice, precum si obiectul fisierului din care se citeste si reader-ul
     * @param path locatia fisierului din care se face citirea
     * @throws FileNotFoundException eroare in cazul in care fisierul nu este gasit
     */
    public Parse(String path) throws FileNotFoundException {
        clienti = new ArrayList<Client>();
        produse = new ArrayList<Product>();
        orderItems= new ArrayList<OrderInfo>();
        //  myObjFile= new File("D:\\PT2020\\Assignment3_OrderManager\\Commands.txt");
        myObjFile= new File(path);
        myReader = new Scanner(myObjFile);
    }

    /**
     * Metoda care executa comanda insert client
     */
    public void doParseInsertClient(){
        nrClienti++;
        numeClient=myReader.next();
        String prenume = myReader.next();
        prenume=prenume.replace(",","");
        numeClient=numeClient+" "+prenume;
        adresaClient=myReader.next();
        Client c = new Client(nrClienti,numeClient,adresaClient);
        insereazaClientBll(c);
        clienti.add(c);
    }

    /**
     * Metoda care executa comanda insert product
     */
    public void doParseInsertProduct(){
        int ok=0;
        numeProdus=myReader.next();
        numeProdus=numeProdus.replace(",","");
        String cantitate = myReader.next();
        cantitate=cantitate.replace(",","");
        cantitateProdus=Integer.parseInt(cantitate);
        String pretProdusString=myReader.next();
        pretProdus=Float.parseFloat(pretProdusString);
        for(Product prod:produse){
            if(prod.getDenumire().startsWith(numeProdus)){
                updateProductBLL(prod,0);
                ok=-1;
            }
        }
        if(ok==0){
            nrProduse++;
            Product p = new Product(nrProduse,numeProdus,cantitateProdus,pretProdus);
            produse.add(p);
            insereazaProductBLL(p);
        }
    }

    /**
     * Metoda care executa comanda delete client
     */
    public void doParseDeleteClient() throws SQLException {
        numeClient=myReader.next();
        String prenume = myReader.next();
        prenume=prenume.replace(",","");
        numeClient=numeClient+" "+prenume;
        adresaClient=myReader.next();
        Client cc = new Client(-1,"","");                                         //copie pentru c, pentru a-l putea elimina din lista
        for(Client c:clienti){
            if(c.getNume().startsWith(numeClient)){
                deleteClientBll(c);
                cc=c;
            }
        }
        clienti.remove(cc);
    }

    /**
     * Metoda care executa comanda delete product
     */
    public void doParseDeleteProduct() throws SQLException {
        numeProdus=myReader.next();
        Product pp = new Product(-1,"numeProdus",-1,-1);
        for(Product p:produse){
            if(p.getDenumire().startsWith(numeProdus)){
                deleteProductBLL(p);
                pp=p;
            }
        }
        produse.remove(pp);
    }

    /**
     * Metoda care executa comanda report client
     */
    public void doParseReportClient(){
        Generater.generatePdfClient();
    }

    /**
     * Metoda care executa comanda report product
     */
    public void doParseReportProduct(){
        Generater.generatePdfProduct();
    }

    /**
     * Metoda care executa comanda report order
     */
    public void doParseReportOrder(){
        Generater.generatePdfOrderInfo();
    }


    /**
     * Metoda in care se face actualizarea comenzilor nedublate, precum executia generarii unei facturi
     */
    public void doParseOrder(){
        int contor =0;
        nrOrders++;
        numeClient=myReader.next();
        String prenume = myReader.next();
        prenume=prenume.replace(",","");
        numeClient=numeClient+" "+prenume;
        numeProdus=myReader.next();
        numeProdus=numeProdus.replace(",","");
        int cantitate = myReader.nextInt();
        Order o = new Order(nrOrders,numeClient,numeProdus,cantitate);
        for(Product p:produse){
            if(p.getDenumire().startsWith(numeProdus)){
                if(orderItems.size()!=0) {
                    for (OrderInfo orderI : orderItems) {
                        if(orderI.getNume().startsWith(numeClient) && orderI.getProdus().startsWith(numeProdus)){
                            orderI.setCantitateTotala(orderI.getCantitateTotala()+cantitate);
                            orderI.setPretTotal(orderI.getCantitateTotala()*p.getPret());
                            generareBill(o,p,orderI);
                            contor++;
                        }
                    }
                    if(contor==0){
                        OrderInfo oI= new OrderInfo(nrOrders,numeClient,numeProdus,cantitate,cantitate*p.getPret());
                        generareBill(o,p,oI);
                        orderItems.add(oI);
                    }
                }else{
                    OrderInfo oI= new OrderInfo(nrOrders,numeClient,numeProdus,cantitate,cantitate*p.getPret());
                    generareBill(o,p,oI);
                    orderItems.add(oI);
                }
            }
        }
    }

    /**
     * Metoda principala in care se alege executia comenzii curente
     */
    public void doParse() throws SQLException {
        //argumentul
        while(myReader.hasNext()){
            String s = myReader.next();
            if(s.startsWith("Insert")){
                String s2 = myReader.next();
                if(s2.endsWith("lient:"))
                    doParseInsertClient();
                else doParseInsertProduct();
            }else if(s.startsWith("Delete")){
                String s2 = myReader.next();
                if(s2.endsWith("lient:"))
                    doParseDeleteClient();
                else doParseDeleteProduct();
            }else if(s.startsWith("Report")){
                String s2 = myReader.next();
                if(s2.endsWith("lient"))
                    doParseReportClient();
                else if(s2.endsWith("roduct")){
                    doParseReportProduct();
                }else {doParseReportOrder();}
            }else{
                doParseOrder();
            }
        }
    }
}
