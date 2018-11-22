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

public class kuliah {

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
    
    private String nama_dosen;
    public void setnama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
    public String getnama_dosen() {
        return nama_dosen;
    }
       
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Update_kuliah(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_id_matkul= params.get("Update_id_matkul");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update kuliah set id_matkul=?, mata_kuliah=?, nama_dosen=? where id_matkul=?");
            ps.setString(1, id_matkul);
            ps.setString(2, mata_kuliah);
            ps.setString(3, nama_dosen);
            ps.setString(4, Update_id_matkul);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_kuliah() throws Exception{
        ArrayList list_of_kuliah=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from kuliah");
            while(rs.next()){
                kuliah obj_kuliah = new kuliah();
                obj_kuliah.setid_matkul(rs.getString("id_matkul"));
                obj_kuliah.setmata_kuliah(rs.getString("mata_kuliah"));
                obj_kuliah.setnama_dosen(rs.getString("nama_dosen"));
                list_of_kuliah.add(obj_kuliah);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_kuliah;
}
    

    public kuliah() {
    }
    
}
