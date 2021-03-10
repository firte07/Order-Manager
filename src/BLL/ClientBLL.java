package BLL;

import ModelLayer.Client;

import java.sql.SQLException;
import java.util.List;

import static DAO.ClientDAO.*;

public class ClientBLL {

    public static void insereazaClientBll(Client c){
        if(c.getIdClient()>0 && c.getNume()!=null && c.getAdresa()!=null)
        insereazaClient(c);
    }

    public static void deleteClientBll(Client c) throws SQLException {
        int id=infoClient(c);
        if(id==-1)
            System.out.print("Error");
        else
            deleteClient(c);
    }

    public static List<Client> findAllBllC() throws SQLException {
        List<Client> allClients=findAllC();
        if(allClients.size()==0) {
            System.out.println("Error");
        }
        return allClients;
    }

}
