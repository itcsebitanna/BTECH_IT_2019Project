using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Net.Mail;
using System.Net;

public partial class MUserApproved : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;

    protected void Page_Load(object sender, EventArgs e)
    {
        bind();
    }

    string altermail;

    protected void lnkView_Click(object sender, EventArgs e)
    {
        GridViewRow grdrow = (GridViewRow)((LinkButton)sender).NamingContainer;
        string id = grdrow.Cells[0].Text;


        con.Open();
        cmd = new SqlCommand("select * from usertb where id='"+id+"' ", con);
        SqlDataReader dr = cmd.ExecuteReader();
        if (dr.Read())
        {
            altermail = dr["Email"].ToString();
        }
        con.Close();



       Random rr=new Random ();
        int key=rr.Next (1111,9999);

        string to = altermail;
        string from = "sampletest685@gmail.com";
        // string subject = "Key";
        // string body = TextBox1.Text;
        string password = "mailtest2";
        using (MailMessage mm = new MailMessage(from, to))
        {
            mm.Subject = "Login Key";
            mm.Body = "Your Login  Keys " + key;
            //if (fuAttachment.HasFile)
            //{
            //string FileName = Server.MapPath(ff);
            //mm.Attachments.Add(new Attachment(FileName));
            //}
            mm.IsBodyHtml = false;
            SmtpClient smtp = new SmtpClient();
            smtp.Host = "smtp.gmail.com";
            smtp.EnableSsl = true;
            NetworkCredential NetworkCred = new NetworkCredential(from, password);
            smtp.UseDefaultCredentials = true;
            smtp.Credentials = NetworkCred;
            smtp.Port = 587;
            smtp.Send(mm);
            ClientScript.RegisterStartupScript(GetType(), "alert", "alert('Email has sent.');", true);



        }



      
        con.Open();
        cmd = new SqlCommand("update usertb  set Status='" + RadioButtonList1.SelectedItem.Text + "',keys='" + key + "' where id='" + id + "' ", con);
        cmd.ExecuteNonQuery();
        con.Close();
        bind();
    }

    public static string Publickey(int length)
    {
        const string chars = "1234567890";
        var random = new Random();
        return new string(Enumerable.Repeat(chars, length)
          .Select(s => s[random.Next(s.Length)]).ToArray());
    }

    protected void lnkView_Click1(object sender, EventArgs e)
    {
        GridViewRow grdrow = (GridViewRow)((LinkButton)sender).NamingContainer;
        string id = grdrow.Cells[0].Text;


       



        con.Open();
        cmd = new SqlCommand("update usertb  set Status='Inactive'  where id='" + id + "' ", con);
        cmd.ExecuteNonQuery();
        con.Close();


        //Delete

        con.Open();
        cmd = new SqlCommand("Delete from  usertb   where id='" + id + "' ", con);
        cmd.ExecuteNonQuery();
        con.Close();


        bind();
    }
  

    private void bind()
    {
        cmd = new SqlCommand("select * from usertb Where Status='Waiting' ", con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataTable dt = new DataTable();
        da.Fill(dt);
        GridView1.DataSource = dt;
        GridView1.DataBind();

        cmd = new SqlCommand("select * from usertb where Status !='Waiting'", con);
        SqlDataAdapter da1 = new SqlDataAdapter(cmd);
        DataTable dt1 = new DataTable();
        da1.Fill(dt1);
        GridView2.DataSource = dt1;
        GridView2.DataBind();


        cmd = new SqlCommand("select * from usertb where Status !='Waiting'", con);
        SqlDataAdapter da11 = new SqlDataAdapter(cmd);
        DataTable dt11 = new DataTable();
        da11.Fill(dt11);
        GridView3.DataSource = dt11;
        GridView3.DataBind();

    }
    protected void RadioButtonList1_SelectedIndexChanged(object sender, EventArgs e)
    {

    }
}