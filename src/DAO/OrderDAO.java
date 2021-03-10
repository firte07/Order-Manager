package DAO;

import Connect.ConnectionFactory;
import ModelLayer.Client;
import ModelLayer.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final static String insertStatementOrder = "INSERT INTO `schooldb`.`order` (`idorder`, `name`, `prod`, `cantitate`) VALUES (?, ?, ?, ?);";
    private final static String infoStatementBill = "select *from `schooldb`.`order` where idorder=?";

    /**
     * Metoda care insereaza o comanda in baza de date
     * @param o comanda care urmeaza sa fie inserata in baza de date
     */
    public static void insereazaOrder(Order o)  {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statemento;
        try {
            statemento = conn.prepareStatement(insertStatementOrder);
            statemento.setInt(1,o.getIdOrder());
            statemento.setString(2,o.getName());
            statemento.setString(3, o.getProd());
            statemento.setInt(4, o.getCantitate());
            statemento.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Order findAllO(Order o) throws SQLException {
        Order oo = null;
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementBill);
        statement.setInt(1,o.getIdOrder());
        rs=statement.executeQuery();
        if(rs.next()){
            oo = new Order(0,rs.getString("name"),rs.getString("prod"),rs.getInt("cantitate"));
        }
        return oo;

    }


}
