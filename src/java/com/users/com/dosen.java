package com.users.com;
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
public class dosen {
    
    private String nip;
    public void setnip(String nip) {
        this.nip = nip;
    }
    public String getnip() {
        return nip;
    }

    private String nama_dosen;
    public void setnama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
    public String getnama_dosen() {
        return nama_dosen;
    }
    
    private String alamat;
    public void setalamat(String alamat) {
        this.alamat = alamat;
    }
    public String getalamat() {
        return alamat;
    }
    
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_dosen(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String field_nip = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from dosen where nip="+field_nip);
          dosen obj_dosen = new dosen();
          rs.next();
          obj_dosen.setnip(rs.getString("nip"));
          obj_dosen.setnama_dosen(rs.getString("nama_dosen"));
          obj_dosen.setalamat(rs.getString("alamat"));
          sessionMap.put("Edit_dosen", obj_dosen);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/Editdosen.xhtml?faces-redirect=true";   
}

public String Delete_dosen(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String field_nip = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from dosen where nip=?");
         ps.setString(1, field_nip);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/dosen.xhtml?faces-redirect=true";   
}

public String Update_dosen(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_nip= params.get("Update_nip");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update dosen set nip=?, nama_dosen=?, alamat=? where nip=?");
            ps.setString(1, nip);
            ps.setString(2, nama_dosen);
            ps.setString(3, alamat);
            ps.setString(4, Update_nip);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/dosen.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_dosen() throws Exception{
        ArrayList list_of_dosen=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from dosen");
            while(rs.next()){
                dosen obj_dosen = new dosen();
                obj_dosen.setnip(rs.getString("nip"));
                obj_dosen.setnama_dosen(rs.getString("nama_dosen"));
                obj_dosen.setalamat(rs.getString("alamat"));
                list_of_dosen.add(obj_dosen);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_dosen;
}
    
public String Tambah_dosen(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into dosen(nip, nama_dosen, alamat) value('"+nip+"','"+nama_dosen+"','"+alamat+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/dosen.xhtml?faces-redirect=true";
    }
    public dosen() {
    }
    
}
