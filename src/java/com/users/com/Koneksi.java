package com.users.com;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
     public static void main(String[] args){
        Koneksi obj_koneksi = new Koneksi();
        System.out.println(obj_koneksi.get_connection());
    }
    
   public Connection get_connection(){
       Connection connection = null;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/kuliah_kel12","root","");
       } catch (Exception e) {
           System.out.println(e);
       }
       return connection;
   }
}

