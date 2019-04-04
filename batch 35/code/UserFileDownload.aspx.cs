using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;
using System.Security.Cryptography;
using System.IO;
using System.Net;
using System.Text;

public partial class UserFileDownload : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;
    string filename;


    string passs = @"myKey123";

    protected void Page_Load(object sender, EventArgs e)
    {
        bind();


    }
    protected void lnkView_Click(object sender, EventArgs e)
    {
        GridViewRow grdrow = (GridViewRow)((LinkButton)sender).NamingContainer;
        string id = grdrow.Cells[0].Text;


        if (TextBox1.Text == "")
        {
            Response.Write("<Script> alert('Please Enter Key') </Script>");
        }
        else
        {
            con.Open();
            cmd = new SqlCommand("select * from filetb where id='" + id + "' and Keys='" + TextBox1.Text + "'", con);
            SqlDataReader dr1 = cmd.ExecuteReader();
            if (dr1.Read())
            {

                //string aaa = dr1["FilePath"].ToString();

                filename = dr1["FileName"].ToString();
                string filePath1 = Server.MapPath("~/Encrypt/" + filename);
                string filePath2 = Server.MapPath("~/Decrypt/" + filename);
                DecryptFile(filePath1, filePath2);

                string aaa = "~/Decrypt/" + filename;



                if (aaa != string.Empty)
                {
                    string filePath = aaa;
                    Response.ContentType = "doc/docx";
                    Response.AddHeader("Content-Disposition", "attachment;filename=\"" + aaa + "\"");
                    Response.TransmitFile(Server.MapPath(filePath));
                    Response.End();
                }

            }

            else
            {
                Response.Write("<Script> alert('File Key Mismatch') </Script>");
            }
            con.Close();

            Response.Write("<Script> alert('" + id + "') </Script>");
        }

        bind();
    }


    private void DecryptFile(string inputFile, string outputFile)
    {

        {
            string keyss = TextBox1.Text;
            string password = passs;


            UnicodeEncoding UE = new UnicodeEncoding();
            byte[] key = UE.GetBytes(password);

            FileStream fsCrypt = new FileStream(inputFile, FileMode.Open);

            RijndaelManaged RMCrypto = new RijndaelManaged();

            CryptoStream cs = new CryptoStream(fsCrypt,
                RMCrypto.CreateDecryptor(key, key),
                CryptoStreamMode.Read);

            FileStream fsOut = new FileStream(outputFile, FileMode.Create);

            int data;
            while ((data = cs.ReadByte()) != -1)
                fsOut.WriteByte((byte)data);

            fsOut.Close();
            cs.Close();
            fsCrypt.Close();

        }
    }

    private void bind()
    {
        cmd = new SqlCommand("select * from userfiletb where Status='waiting' and UserName='" + Session["uname"].ToString() + "'", con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataTable dt = new DataTable();
        da.Fill(dt);
        GridView1.DataSource = dt;
        GridView1.DataBind();

        cmd = new SqlCommand("select * from userfiletb where Status='Approved' and UserName='" + Session["uname"].ToString() + "' ", con);
        SqlDataAdapter da1 = new SqlDataAdapter(cmd);
        DataTable dt1 = new DataTable();
        da1.Fill(dt1);
        GridView2.DataSource = dt1;
        GridView2.DataBind();

    }
}