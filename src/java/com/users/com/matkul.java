package com.users.com;
import com.users.com.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class matkul {
    
    private String id_matkul;
    public void setid_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }
    public String getid_matkul() {
        return id_matkul;
    }

    private String mata_kuliah;
    public void setmata_kuliah(String mata_kuliah) {
        this.mata_kuliah = mata_kuliah;
    }
    public String getmata_kuliah() {
        return mata_kuliah;
    }
    
    private String nip;
    public void setnip(String nip) {
        this.nip = nip;
    }
    public String getnip() {
        return nip;
    }
    
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_matkul(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String field_id_matkul = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from matkul where id_matkul="+field_id_matkul);
          matkul obj_matkul = new matkul();
          rs.next();
          obj_matkul.setid_matkul(rs.getString("id_matkul"));
          obj_matkul.setmata_kuliah(rs.getString("mata_kuliah"));
          obj_matkul.setnip(rs.getString("nip"));
          sessionMap.put("Edit_matkul", obj_matkul);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/editmatkul.xhtml?faces-redirect=true";   
}

public String Delete_matkul(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String field_id_matkul = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from matkul where id_matkul=?");
         ps.setString(1, field_id_matkul);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "matkul.xhtml?faces-redirect=true";   
}

public String Update_matkul(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_id_matkul= params.get("Update_id_matkul");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update matkul set id_matkul=?, mata_kuliah=?, nip=? where id_matkul=?");
            ps.setString(1, id_matkul);
            ps.setString(2, mata_kuliah);
            ps.setString(3, nip);
            ps.setString(4, Update_id_matkul);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/matkul.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_matkul() throws Exception{
        ArrayList list_of_matkul=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from matkul");
            while(rs.next()){
                matkul obj_matkul = new matkul();
                obj_matkul.setid_matkul(rs.getString("id_matkul"));
                obj_matkul.setmata_kuliah(rs.getString("mata_kuliah"));
                obj_matkul.setnip(rs.getString("nip"));
                list_of_matkul.add(obj_matkul);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_matkul;
}
    
public String Tambah_matkul(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into matkul(id_matkul, mata_kuliah, nip) value('"+id_matkul+"','"+mata_kuliah+"','"+nip+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/matkul.xhtml?faces-redirect=true";
    }
    public matkul() {
    }
    
}
