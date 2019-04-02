package DB;
import java.sql.*;
import java.util.Random;

public class database {
    public static Connection con;
    public static PreparedStatement pst;
    public static ResultSet rs;
     public static String name;
    private static String email;

    public static void getConnection () {
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3308/prj","root","root");
        
    }
    catch(Exception e)
            {
             System.out.println("ERROR"+ e);   
            }
                
    }
    
    
    
     public  static String registration(String nme,String pass,String mail,String contact,String bankname,String accno,String pin,String loc)  {
        String s="";
        try
        {
//           String s1="Insert into tbl_UserRegistration(UserID,Name,UserName,Password,SecretKey,IsActive,EmailID)values(?,?,?,?,?,'1',?)";
//            PreparedStatement pst=con.prepareStatement(s1);
//          //  pst.getGeneratedKeys();
//            pst.setString(1,id);
//            pst.setString(2,name);
//            pst.setString(3,un);
//            pst.setString(4, pw);
//            pst.setString(5, key);
//            pst.setString(6, ml);
//            pst.executeUpdate();
//            pst.close();
//            con.close();
//            s="OK";
//            
            
            String s1="Insert into tbl_registration (name,password,email,contactno,bankname,account,pin,location,status)values(?,?,?,?,?,?,?,?,1)";
            PreparedStatement pst1=con.prepareStatement(s1);
            
            pst1.setString(1, nme);
            pst1.setString(2, pass);
            pst1.setString(3, mail);
            pst1.setString(4, contact);
            pst1.setString(5, bankname);
            pst1.setString(6, accno);
            pst1.setString(7, pin);
             pst1.setString(8, loc);
            pst1.executeUpdate();
            pst1.close();
            con.close();
            System.out.println("Insertion success");
            
            s="OK";
            
        }
        catch(SQLException se)
        {
            s="ERROR"+ se.getMessage();   
        }
      return s; 
  }
    
       
    
    
    
    public static String shuffleArray(String[] ar) {
        String p="";
        String tmp="";
        try{
           Random rnd = new Random();
             for (int i = ar.length - 1; i > 0; i--)
           {
              int index = rnd.nextInt(i + 1);
              // Simple swap
              String a = ar[index];
              ar[index] = ar[i];
              ar[i] = a;
           }
    
    
        }
        catch(Exception e)
        {
            p="ERROR"+e;
        }
        return p;
    }
     public static String locationArray(String[] ar) {
        String l="";
        String tmp="";
        try{
           Random rnd = new Random();
             for (int i = ar.length - 1; i > 0; i--)
           {
               int index=rnd.nextInt(i+1);
              // Simple swap
              String a = ar[index];
              ar[index] = ar[i];
              ar[i] = a;
           }
    
    
        }
        catch(Exception e)
        {
            l="ERROR"+e;
        }
        return l;
    }


public static Boolean checkLogin(String un, String pw) {
        try {
            getConnection();
            Statement st;
            st = con.createStatement();  
            
            //executes the prepared statement
            ResultSet rs = st.executeQuery("select account,password from tbl_registration where account='"+un+"' and password='"+pw+"'");
          System.out.println("select account,password from tbl_registration where account='"+un+"' and password='"+pw+"'");
            if (rs.next()) {
                //TRUE iff the query founds any corresponding data
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating" + e);
            return false;
        }
    }

public static String setname(String name)
{
    database.name=name;
    return name;
    }
public static String getname(){
    return name;
}
public static String setmail(String mail)
{
    database.email=mail;
    return email;
    }
public static String getmail(){
    return email;
}
}
