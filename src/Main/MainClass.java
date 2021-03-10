package Main;

import Presentation.Parse;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        Parse parse = new Parse(args[0]);
        parse.doParse();
    }
}
