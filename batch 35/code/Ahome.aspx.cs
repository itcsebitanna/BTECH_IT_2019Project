using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;
using System.IO;
using System.Security.Cryptography;
using System.Text;

public partial class Ahome : System.Web.UI.Page
{

    SqlConnection con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=|DataDirectory|\Dataindb.mdf;Integrated Security=True;User Instance=True");
    SqlCommand cmd;


    string passw = @"myKey123";
    protected void Page_Load(object sender, EventArgs e)
    {
        Label5.Text = Session["Ass"].ToString();

    }
    //public static string ECC(int length)
    //{
    //    const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqstuuvwxyz";
    //    var random = new Random();
    //    return new string(Enumerable.Repeat(chars, length)
    //      .Select(s => s[random.Next(s.Length)]).ToArray());
    //}

    string dkey,ekey;


    string fil,ssize;

    protected void Button1_Click(object sender, EventArgs e)
    {
        



        string filename = System.IO.Path.GetFileName(FileUpload1.PostedFile.FileName);

        fil = System.IO.Path.GetFileNameWithoutExtension(FileUpload1.PostedFile.FileName);
        FileUpload1.PostedFile.SaveAs(Server.MapPath("~/Upload/" + filename));
        string filePath = Server.MapPath("~/Upload/" + filename);

        decimal size = Math.Round(((decimal)FileUpload1.PostedFile.ContentLength / (decimal)1024), 2);

        FileStream fs = new FileStream(filePath, FileMode.Open, FileAccess.Read);
        BinaryReader br = new BinaryReader(fs);
        Byte[] bytes = br.ReadBytes((Int32)fs.Length);
        br.Close();
        fs.Close();

        //Label6.Text = "Encrypt key:" + ECC(10).ToString();

        //dcrykey = "";

        int mm = Convert.ToInt32(size);

        ssize = Convert.ToString(mm);

        TextBox2.Text = StringToBinary(fil);
        TextBox3.Text = StringToBinary(ssize);




        string sr1 = TextBox2.Text;
        string sr2 = TextBox3.Text;

        int foo = Convert.ToInt32(sr1, 2);
        int bar = Convert.ToInt32(sr2, 2);

        // Perform binary styff
        int result = foo & bar;



        ekey = result.ToString();


        int result1 = foo | bar;

        dkey = result1.ToString();





        cmd = new SqlCommand("insert into filetb values(@AsstName,@FileInfo,@FileName,@FilePath,@FileData,@size,@keys,@Mangername,@keys1)", con);
        cmd.Parameters.AddWithValue("@AsstName", Label5.Text);
        cmd.Parameters.AddWithValue("@FileInfo", TextBox1.Text);
        cmd.Parameters.AddWithValue("@FileName", filename);
        cmd.Parameters.AddWithValue("@FilePath", "~/Upload/" + filename);
        cmd.Parameters.AddWithValue("@FileData", bytes);
        cmd.Parameters.AddWithValue("@size", size.ToString() + "KB");
        cmd.Parameters.AddWithValue("@keys", dkey);
        cmd.Parameters.AddWithValue("@Mangername", Session["Man"].ToString());
        cmd.Parameters.AddWithValue("@keys1", ekey);
        con.Open();
        cmd.ExecuteNonQuery();
        con.Close();

        string filePath1 = Server.MapPath("~/Encrypt/" + filename);
        ECCEncryptFile(filePath, filePath1);
        Response.Write("<Script> alert('File Encrypt and Saved') </Script>");



    }


    public static string StringToBinary(string data)
    {
        StringBuilder sb = new StringBuilder();

        foreach (char c in data.ToCharArray())
        {
            sb.Append(Convert.ToString(c, 2).PadLeft(8, '0'));
        }
        return sb.ToString();
    }

    public static string BinaryToString(string data)
    {
        List<Byte> byteList = new List<Byte>();

        for (int i = 0; i < data.Length; i += 8)
        {
            byteList.Add(Convert.ToByte(data.Substring(i, 8), 2));
        }
        return Encoding.ASCII.GetString(byteList.ToArray());
    }

    private void ECCEncryptFile(string inputFile, string outputFile)
    {


        string password = passw;

        string keys = ekey;


        UnicodeEncoding UE = new UnicodeEncoding();
        byte[] key = UE.GetBytes(password);

        string cryptFile = outputFile;
        FileStream fsCrypt = new FileStream(cryptFile, FileMode.Create);

        RijndaelManaged RMCrypto = new RijndaelManaged();

        CryptoStream cs = new CryptoStream(fsCrypt,
            RMCrypto.CreateEncryptor(key, key),
            CryptoStreamMode.Write);

        FileStream fsIn = new FileStream(inputFile, FileMode.Open);

        int data;
        while ((data = fsIn.ReadByte()) != -1)
            cs.WriteByte((byte)data);

        fsIn.Close();
        cs.Close();
        fsCrypt.Close();
        Response.Write("Encryption Sucessfully Completed");

    }








    private void ECC_Encrypt(string inputFile, string password)
    {

        //generate random salt
        byte[] salt = GenerateRandomSalt();

        //create output file name
        FileStream fsCrypt = new FileStream(inputFile + ".ecc", FileMode.Create);
        //+ ".aes"
        //convert password string to byte arrray
        byte[] passwordBytes = System.Text.Encoding.UTF8.GetBytes(password);

        //Set Rijndael symmetric encryption algorithm
        RijndaelManaged ECC = new RijndaelManaged();
        ECC.KeySize = 256;
        ECC.BlockSize = 128;
        ECC.Padding = PaddingMode.PKCS7;

        //"What it does is repeatedly hash the user password along with the salt." High iteration counts.
        var key = new Rfc2898DeriveBytes(passwordBytes, salt, 50000);
        ECC.Key = key.GetBytes(ECC.KeySize / 8);
        ECC.IV = key.GetBytes(ECC.BlockSize / 8);

        ECC.Mode = CipherMode.CFB;

        //write salt to the begining of the output file, so in this case can be random every time
        fsCrypt.Write(salt, 0, salt.Length);

        CryptoStream cs = new CryptoStream(fsCrypt, ECC.CreateEncryptor(), CryptoStreamMode.Write);

        FileStream fsIn = new FileStream(inputFile, FileMode.Open);

        //create a buffer (1mb) so only this amount will allocate in the memory and not the whole file
        byte[] buffer = new byte[1048576];
        int read;

        try
        {
            while ((read = fsIn.Read(buffer, 0, buffer.Length)) > 0)
            {

                cs.Write(buffer, 0, read);
            }

            //close up
            fsIn.Close();

        }
        catch (Exception ex)
        {
            // Debug.WriteLine("Error: " + ex.Message);
        }
        finally
        {
            cs.Close();
            fsCrypt.Close();
        }
    }

    private void ECC_Decrypt(string inputFile, string password)
    {
        //todo:
        // - create error message on wrong password
        // - on cancel: close and delete file
        // - on wrong password: close and delete file!
        // - create a better filen name
        // - could be check md5 hash on the files but it make this slow

        byte[] passwordBytes = System.Text.Encoding.UTF8.GetBytes(password);
        byte[] salt = new byte[32];

        FileStream fsCrypt = new FileStream(inputFile, FileMode.Open);
        fsCrypt.Read(salt, 0, salt.Length);

        RijndaelManaged ECC = new RijndaelManaged();
        ECC.KeySize = 256;
        ECC.BlockSize = 128;
        var key = new Rfc2898DeriveBytes(passwordBytes, salt, 50000);
        ECC.Key = key.GetBytes(ECC.KeySize / 8);
        ECC.IV = key.GetBytes(ECC.BlockSize / 8);
        ECC.Padding = PaddingMode.PKCS7;
        ECC.Mode = CipherMode.CFB;

        CryptoStream cs = new CryptoStream(fsCrypt, ECC.CreateDecryptor(), CryptoStreamMode.Read);

        FileStream fsOut = new FileStream(inputFile + ".decrypted", FileMode.Create);

        int read;
        byte[] buffer = new byte[1048576];

        try
        {
            while ((read = cs.Read(buffer, 0, buffer.Length)) > 0)
            {
                //Application.DoEvents();
                fsOut.Write(buffer, 0, read);
            }
        }
        catch (System.Security.Cryptography.CryptographicException ex_CryptographicException)
        {
            // Debug.WriteLine("CryptographicException error: " + ex_CryptographicException.Message);
        }
        catch (Exception ex)
        {
            //  Debug.WriteLine("Error: " + ex.Message);
        }

        try
        {
            cs.Close();
        }
        catch (Exception ex)
        {
            //  Debug.WriteLine("Error by closing CryptoStream: " + ex.Message);
        }
        finally
        {
            fsOut.Close();
            fsCrypt.Close();
        }
    }

    public static byte[] GenerateRandomSalt()
    {

        byte[] data = new byte[32];

        using (RNGCryptoServiceProvider rng = new RNGCryptoServiceProvider())
        {
            // Ten iterations.
            for (int i = 0; i < 10; i++)
            {
                // Fill buffer.
                rng.GetBytes(data);
            }
        }
        return data;
    }

    protected void Button2_Click(object sender, EventArgs e)
    {
        TextBox1.Text = "";
    }
}