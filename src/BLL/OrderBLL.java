package BLL;

import DAO.OrderDAO;
import ModelLayer.Order;

import java.sql.SQLException;
import java.util.List;
import static DAO.OrderDAO.insereazaOrder;

public class OrderBLL {

    public static void insereazaOrderBll(Order o){
        System.out.println("am: " +o.getIdOrder() +"   "+o.getName()+"  "+ o.getCantitate());
        if(o.getIdOrder()>-1 && o.getCantitate()>0)
            insereazaOrder(o);
        else
            System.out.println("Error");
    }

    public static Order findAllBllO(Order o) throws SQLException {
        Order allOrders= OrderDAO.findAllO(o);
        if(allOrders!=null) {
            System.out.println("Error");
        }
        return allOrders;
    }


}
