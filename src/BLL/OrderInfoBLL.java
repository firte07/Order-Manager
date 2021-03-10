package BLL;

import ModelLayer.Client;
import ModelLayer.Order;
import ModelLayer.OrderInfo;

import java.sql.SQLException;
import java.util.List;


import DAO.OrderInfoDAO;

public class OrderInfoBLL {

    public static void insereazaOderInfoBll(OrderInfo oI, Order o){
        if(oI.getID()>0 && oI.getCantitateTotala()>0 && oI.getNume()!=null)
            OrderInfoDAO.insereazaOrderInfo(oI, o);
        else
            System.out.println("Error");
    }

    public static List<OrderInfo> findAllBllOi() throws SQLException {
        List<OrderInfo> allOrderInfo=OrderInfoDAO.findAllOi();
        if(allOrderInfo.size()==0) {
            System.out.println("Error");
        }
        return allOrderInfo;
    }
}
