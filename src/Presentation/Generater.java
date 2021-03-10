
package Presentation;

import BLL.ClientBLL;
import BLL.ProductBLL;
import ModelLayer.Client;
import ModelLayer.Order;
import ModelLayer.OrderInfo;
import ModelLayer.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;

import BLL.OrderBLL;
import BLL.OrderInfoBLL;
import static BLL.ProductBLL.updateProductBLL;

/**
 * Clasa generatoare de pdf. Aici operatia "report" genereaza un fisier pdf cu continutul tabelului respectiv metodei, totodata aici se genereaza si
 * pdf ul pentru Bill, precum si cel de eroare, in cazul in care comanda nu se poate face.
 */
public class Generater {

    private static int nrPdfClient=0;
    private static int nrPdfProduct=0;
    private static int nrPdfOrder=0;
    private static int nrBill=0;
    private static int ok=0;

    /**
     * Metoda care genereaza in format pdf informatii referitoare despre fiecare client
     */
    public static void generatePdfClient(){
        nrPdfClient++;
        try {
            String client_file = "D:\\PT2020\\Assignment3_OrderManager\\client" + nrPdfClient + ".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(client_file));
            doc.open();
            PdfPTable tableClient = new PdfPTable(2);
            PdfPCell c1 = new PdfPCell(new Phrase("Nume"));
            tableClient.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Adresa"));
            tableClient.addCell(c2);
            tableClient.setHeaderRows(1);
            List<Client> allClients = ClientBLL.findAllBllC();
            for (Client c : allClients){
                tableClient.addCell(c.getNume());
                tableClient.addCell(c.getAdresa());
            }
            doc.add(tableClient);
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda care genereaza in format pdf informatii referitoare despre fiecare produs
     */
    public static void generatePdfProduct(){
        nrPdfProduct++;
        try {
            String client_file = "D:\\PT2020\\Assignment3_OrderManager\\product"+nrPdfProduct+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(client_file));
            doc.open();
            PdfPTable tableClient = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Denumire"));
            tableClient.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Cantitate"));
            tableClient.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Pret"));
            tableClient.addCell(c3);
            tableClient.setHeaderRows(1);
            List<Product> allProducts = ProductBLL.findAllBllP();
            for (Product p:allProducts){
                tableClient.addCell(p.getDenumire());
                tableClient.addCell(p.getCantitate()+"");
                tableClient.addCell(p.getPret()+"");
            }
            doc.add(tableClient);
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda care genereaza in format pdf informatii referitoare despre fiecare comanda
     */
    public static void generatePdfOrderInfo(){
        nrPdfOrder++;
        try {
            String client_file = "D:\\PT2020\\Assignment3_OrderManager\\orderInfo"+nrPdfOrder+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(client_file));
            doc.open();
            PdfPTable tableClient = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("NumeClient"));
            tableClient.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("NumeProduct"));
            tableClient.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Cantitate"));
            tableClient.addCell(c3);
            PdfPCell c8 = new PdfPCell(new Phrase("Pret"));
            tableClient.addCell(c8);
            tableClient.setHeaderRows(1);
            List<OrderInfo> allOrderInfo  =  OrderInfoBLL.findAllBllOi();
            for (OrderInfo oI: allOrderInfo){
                tableClient.addCell(oI.getNume());
                tableClient.addCell(oI.getProdus());
                tableClient.addCell(oI.getCantitateTotala()+"");
                tableClient.addCell(oI.getPretTotal()+"");
            }
            doc.add(tableClient);
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda care genereaza in format pdf informatii referitoare despre o comanda efectuata
     * @param o comanda efectuata
     * @param pretTotal pretul total al comenzii
     */
    public static void generatePdfBill(Order o, float pretTotal){
        nrBill++;
        try{
            String client_file = "D:\\PT2020\\Assignment3_OrderManager\\Bill"+nrBill+".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(client_file));
            doc.open();
            PdfPTable tableClient = new PdfPTable(4);
            PdfPCell c1 = new PdfPCell(new Phrase("NumeClient"));
            tableClient.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("NumeProdus"));
            tableClient.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Cantitate"));
            tableClient.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Pret Total"));
            tableClient.addCell(c4);
            tableClient.setHeaderRows(1);
            Order oNew=OrderBLL.findAllBllO(o);
                tableClient.addCell(oNew.getName());
                tableClient.addCell(oNew.getProd());
                tableClient.addCell(oNew.getCantitate()+"");
                PdfPCell c5 = new PdfPCell(new Phrase(pretTotal + ""));
                tableClient.addCell(c5);
            doc.add(tableClient);
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda care genereaza un fisier pdf care contine un mesaj de eroare, in cazul in care
     * nu s-a putut face o comanda
     */
    public static void generatePdfError(){
        String client_file = "D:\\PT2020\\Assignment3_OrderManager\\UnderStock.pdf";
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(client_file));
            doc.open();
            Paragraph p = new Paragraph("Error. Insufficient products");
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc.close();
    }

    /**
     * Se genereaza factura unei comenzi. Totodata se actualizeaza orderInfo
     * @param o  comanda care s-a facut
     * @param p     produsul din comanda
     * @param oI    comanda fara dubluri
     */
    public static void generareBill(Order o, Product p, OrderInfo oI){
        ok= updateProductBLL(p,o.getCantitate());
        if(ok==-1){
            Generater.generatePdfError();
        }else if(ok==1){
            OrderBLL.insereazaOrderBll(o);
            float pretTotal = 0;
            pretTotal= o.getCantitate()*p.getPret();
            Generater.generatePdfBill(o,pretTotal);
            OrderInfoBLL.insereazaOderInfoBll(oI,o);
        }
    }
}
