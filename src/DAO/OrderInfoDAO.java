package DAO;

import Connect.ConnectionFactory;
import ModelLayer.Client;
import ModelLayer.Order;
import ModelLayer.OrderInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoDAO {
    private final static String insertStatementOrderInfo = "INSERT INTO orderinfo (idOrderInfo, nume, denumire, cantitateTotala, pretTotal) values (?,?,?,?,?)";
    private final static String infoStatementOrderInfo = "select *from `schooldb`.`orderinfo` where denumire=? and nume=?";
    private final static String updateStatementOrderInfo = "UPDATE orderinfo SET cantitateTotala = ?, pretTotal=? WHERE idorderInfo = ?";
    private static int ID=-1;

    private final static String infoStatementOrderInfoI = "select *from `schooldb`.`orderinfo` ";


    /**
     * Metoda care returneaza informatii despre o anumita comanda din baza de date
     * @param o comanda
     */
    public static void infoOrderInfo(Order o ){
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement;
        ResultSet rs;
        try{
            statement = conn.prepareStatement(infoStatementOrderInfo);
            statement.setString(1,o.getProd());
            statement.setString(2,o.getName());
            rs=statement.executeQuery();
            if(rs.next()){
                ID=rs.getInt("idorderInfo");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metoda care insereaza o anumita comanda fara dubluri
     * @param oI comanda fara dubluri
     * @param o comanda curenta
     */
    public static void insereazaOrderInfo(OrderInfo oI,Order o){
        infoOrderInfo(o);                                                               //verific daca s-a mai facut o comanda pe acest nume si acest produs
        if(ID!=-1) {                                                                    //daca da, atunci apelez metoda de update a cantitatii si pretului total
            updateOrderInfo(oI,o,ID);
        }else{                                                                          //daca nu, inserez actuala comanda
            ConnectionFactory connFactory = new ConnectionFactory();
            connFactory.conecteaza();
            Connection conn = connFactory.getConn();
            PreparedStatement statement;
            try {
                statement = conn.prepareStatement(insertStatementOrderInfo);
                statement.setInt(1,oI.getID());
                statement.setString(2,oI.getNume());
                statement.setString(3, oI.getProdus());
                statement.setInt(4, oI.getCantitateTotala());
                statement.setFloat(5, oI.getPretTotal());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda in care se face update asupra tabelului de comenzi fara dubluri.
     * @param oI comanda fara dubluri
     * @param o comanda care s-a facut
     * @param ID  ID-ul comenzii fara dubluri
     */
    public static void updateOrderInfo(OrderInfo oI, Order o,int ID){
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        try{
            statement = conn.prepareStatement(updateStatementOrderInfo);
            statement.setInt(1, oI.getCantitateTotala());
            statement.setFloat(2, oI.getPretTotal());
            statement.setInt(3,ID);
            statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<OrderInfo> findAllOi() throws SQLException {
        int nrID=0;
        List<OrderInfo> allOrderInfo = new ArrayList<OrderInfo>();
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementOrderInfoI);
        rs=statement.executeQuery();
        while(rs.next()){
            OrderInfo oI = new OrderInfo(nrID,rs.getString("nume"),rs.getString("denumire"),rs.getInt("cantitateTotala"),rs.getFloat("pretTotal"));
            allOrderInfo.add(oI);
            nrID++;
        }
        return allOrderInfo;
    }
}
