using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;


public partial class NewAsst : System.Web.UI.Page
{

    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;

    protected void Page_Load(object sender, EventArgs e)
    {
        con.Open();
        cmd = new SqlCommand("select * from managertb  ", con);
        SqlDataReader dr = cmd.ExecuteReader();
        while  (dr.Read())
        {

            DropDownList1.Items.Add(dr["UserName"].ToString());
        }
       
        con.Close();
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        con.Open();
        cmd = new SqlCommand(" select * from Asstb where UserName='" + TextBox8.Text + "' ", con);
        SqlDataReader dr = cmd.ExecuteReader();
        if (dr.Read())
        {

            Response.Write("<Script> alert('Already Register This Asst.Manager') </Script>");
        }
        else
        {

            dr.Close();

            cmd = new SqlCommand("insert into Asstb values('" + TextBox1.Text + "','" + TextBox2.Text + "','" + RadioButtonList1.Text + "','" +
               TextBox3.Text + "','" + TextBox4.Text + "','" + TextBox5.Text + "','" + TextBox6.Text + "','" + DropDownList1.Text + "','" + TextBox8.Text + "','" + TextBox9.Text + "','Waiting','','')", con);
          //  con.Open();
            cmd.ExecuteNonQuery();
            //con.Close();
            Response.Write("<Script> alert('Record Saved!') </Script>");
        }

        con.Close();

    }


    protected void Button2_Click(object sender, EventArgs e)
    {
        TextBox1.Text = "";
        TextBox2.Text = "";
        TextBox3.Text = "";
        TextBox4.Text = "";
        TextBox5.Text = "";
        TextBox6.Text = "";
       
        TextBox8.Text = "";
        TextBox9.Text = "";
    }
    protected void DropDownList3_SelectedIndexChanged(object sender, EventArgs e)
    {
      

    }
}