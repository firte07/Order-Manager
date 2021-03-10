package DAO;
import Connect.ConnectionFactory;
import ModelLayer.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO {
    private final static String deleteStatementClient = "delete from client where nume=?";
    private final static String insertStatementClient = "Insert into client (idClient, nume, adresa) values (?,?,?)";
    private final static String infoStatementClient = "select * from client";




    /**
     * Metoda care sterge un anumit client din baza de date
     * @param c clientul care urmeaza sa fie sters din baza de date
     */
    public static void deleteClient(Client c)  {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(deleteStatementClient);
            statement.setString(1,c.getNume());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda care insereaza un client specific in baza de date
     * @param c clientul care urmeaza sa fie inserat
     */
    public static void insereazaClient(Client c)  {
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        try {
            statement = conn.prepareStatement(insertStatementClient);
            statement.setInt(1,c.getIdClient());
            statement.setString(2,c.getNume());
            statement.setString(3, c.getAdresa());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int infoClient(Client c) throws SQLException {
        int ID=-1;
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementClient);
        rs=statement.executeQuery();
        if(rs.next()) {
            ID = rs.getInt("idClient");
        }
        return ID;
    }

    public static List<Client> findAllC() throws SQLException {
        int nrID=0;
        List<Client> allClients = new ArrayList<Client>();
        ConnectionFactory connFactory = new ConnectionFactory();
        connFactory.conecteaza();
        Connection conn = connFactory.getConn();
        PreparedStatement statement = null;
        ResultSet rs =null;
        statement = conn.prepareStatement(infoStatementClient);
        rs=statement.executeQuery();
        while(rs.next()){
            Client c = new Client(nrID,rs.getString("nume"),rs.getString("adresa"));
            allClients.add(c);
            nrID++;
        }
        return allClients;
    }




}
