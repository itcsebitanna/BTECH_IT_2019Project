using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;


public partial class AsstManagerLogin : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;

    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        con.Open();
        cmd = new SqlCommand("select * from Asstb where Username='" + TextBox1.Text + "' and Password='" + TextBox2.Text + "' and Status='Approved'", con);
        SqlDataReader dr = cmd.ExecuteReader();
        if (dr.Read())
        {

            Session["Man"] = dr["Manager"].ToString();
            Session["Ass"] = TextBox1.Text;


            Response.Redirect("Ahome.aspx");

        }
        else
        {

            Response.Write("<Script> alert('Password Mismatch') </Script>");

        }
        con.Close();

    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        TextBox1.Text = "";
        TextBox2.Text = "";

    }
}