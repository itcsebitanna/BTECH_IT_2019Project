using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

public partial class Userfile : System.Web.UI.Page
{

    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;

    protected void Page_Load(object sender, EventArgs e)
    {

        cmd = new SqlCommand("select * from filetb", con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataTable dt = new DataTable();
        da.Fill(dt);
        GridView1.DataSource = dt;
        GridView1.DataBind();

    }
    string s1, s2, s3, s4, s5, s6, s7, s8;
    protected void lnkView_Click(object sender, EventArgs e)
    {
        GridViewRow grdrow = (GridViewRow)((LinkButton)sender).NamingContainer;
        string id = grdrow.Cells[0].Text;



       
        con.Open();
        cmd = new SqlCommand("select * from filetb where id ='" + id + "'", con);
        SqlDataReader dr;
        dr = cmd.ExecuteReader();
        if (dr.Read())
        {
            s1 = dr["id"].ToString();
            s2 = dr["AsstName"].ToString();
            s3 = dr["FileName"].ToString();
            s4 = dr["Size"].ToString();
            s5 = dr["Keys"].ToString();
           s6 = dr["Mangername"].ToString();

        }
        con.Close();



        if (s6 == Session["uman"].ToString())
        {

            cmd = new SqlCommand("insert into userfiletb values('" + id + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + Session["uname"].ToString() + "','Waiting')", con);
            con.Open();
            cmd.ExecuteNonQuery();
            con.Close();

            Response.Write("<Script> alert('Request Send') </Script>");

        }
        else
        {
            Response.Write("<Script> alert('u Cannot Access this File') </Script>");
        }


        
    }


    
   
}