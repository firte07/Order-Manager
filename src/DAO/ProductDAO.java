package DAO;

import Connect.ConnectionFactory;
import ModelLayer.Client;
import ModelLayer.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final static String insertStatementProduct = "Insert into product (idProduct, denumire, cantitate, pret) values (?,?,?,?)";
    private final static String deleteStatementProduct = "delete from product where denumire=?";
    private final static String updateStatementProduct = "UPDATE product SET cantitate = ? WHERE denumire = ?";
    private final static String infoStatementProduct = "select *from product";
    private final static String infoStatementProductDenumire = "select *from product where denumire=?";

    /**
     * Metoda care insereaza un anumit produs intr-o baza de date
     * @param p produsul care urmeaza sa fie inserat in baza de date
     */
    public static void insereazaProduct(Product p)  {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(insertStatementProduct);
            statement.setInt(1,p.getIdProduct());
            statement.setString(2,p.getDenumire());
            statement.setInt(3, p.getCantitate());
            statement.setFloat(4, p.getPret());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodata care sterge un anumit produs din baza de date
     * @param p produsul care urmeaza sa fie sters din baza de date
     */
    public static void deleteProduct(Product p)  {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(deleteStatementProduct);
            statement.setString(1,p.getDenumire());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int ok=0;

    /**
     * Metoda care face update-ul produsului. Fie adauga in la stocul curent, fie scade (s-a produs o comanda)
     * @param p produsul la care urmeaza sa i se faca update
     * @param cantitateLuata =0 in caz ca se adauga la stock, altfel, reprezinta cantitatea din order
     */
    public static int updateProduct(Product p, int cantitateLuata)  {     //daca cantitate==0 atunci avem adaugare, altfel order
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        int cantitateTotala;
        try {
            statement = conn.prepareStatement(infoStatementProductDenumire);
            statement.setString(1,p.getDenumire());
            rs=statement.executeQuery();
            if(rs.next()) {
                cantitateTotala = rs.getInt("cantitate");
                if(cantitateLuata>cantitateTotala){ return -1;                               //incercare de generare factura esuata
                }else{ if(cantitateLuata>0){
                    p.setCantitate(p.getCantitate()-cantitateLuata);
                    statement = conn.prepareStatement(updateStatementProduct);
                    statement.setInt(1, p.getCantitate());
                    statement.setString(2, p.getDenumire());
                    statement.executeUpdate();
                    return 1;           //urmeaza creearea facturii
                } else {
                    p.setCantitate(cantitateTotala + p.getCantitate());
                    statement = conn.prepareStatement(updateStatementProduct);
                    statement.setInt(1, p.getCantitate());
                    statement.setString(2, p.getDenumire());
                    statement.executeUpdate();
                    return 0;          //s-a adaugat produs
                } }
            }else { insereazaProduct(p); }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int infoProduct(Product p) throws SQLException {
        int ID=-1;
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementProduct);
        rs=statement.executeQuery();
        if(rs.next()) {
            ID = rs.getInt("idProduct");
        }
        return ID;
    }

    public static List<Product> findAllP() throws SQLException {
        int nrID=0;
        List<Product> allProducts = new ArrayList<Product>();
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementProduct);
        rs=statement.executeQuery();
        while(rs.next()){
            Product p = new Product(nrID,rs.getString("denumire"),rs.getInt("cantitate"),rs.getFloat("pret"));
            allProducts.add(p);
            nrID++;
        }
        return allProducts;
    }
}
