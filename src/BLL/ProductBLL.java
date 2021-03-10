package BLL;

import ModelLayer.Client;
import ModelLayer.Product;

import java.sql.SQLException;
import java.util.List;

import static DAO.ProductDAO.*;

public class ProductBLL {

    public static void insereazaProductBLL(Product p){
        if(p.getDenumire()!=null & p.getIdProduct()>0)
            insereazaProduct(p);
        else System.out.println("Error");
    }

    public static void deleteProductBLL(Product p) throws SQLException {
        int id=infoProduct(p);
        if(id==-1)
            System.out.print("Error");
        else
            deleteProduct(p);
    }

    public static int updateProductBLL(Product p, int cantitateLuata){
        if(p.getDenumire()!=null & p.getIdProduct()>0){
            int ok=updateProduct(p,cantitateLuata);
            return ok;
        } else System.out.print("Error");
    return -3;
    }

    public static List<Product> findAllBllP() throws SQLException {
        List<Product> allProducts=findAllP();
        if(allProducts.size()==0) {
            System.out.println("Error");
        }
        return allProducts;
    }
}

