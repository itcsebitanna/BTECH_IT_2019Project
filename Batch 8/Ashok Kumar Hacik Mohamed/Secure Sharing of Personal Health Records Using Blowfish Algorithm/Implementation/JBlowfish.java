package SeSPHR;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import javax.xml.bind.DatatypeConverter;

public class JBlowfish 
{
       static String strkey=null;
       static String plainText=null;
       static String cipherText=null;
       static byte[] raw;
       static String IV=null;
       static Cipher cipher=null;
       static SecretKeySpec keySpec=null;
       static String enc;
    
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
        
        public static String encrypt(String key,String plaintext){
        
           try {
               raw	= key.getBytes();
               IV  	= "12345678";
               keySpec = new SecretKeySpec(raw, "Blowfish");
               cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
               plainText=plaintext;
               cipher.init(Cipher.ENCRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(IV.getBytes())); 
               byte[] encoding = cipher.doFinal(plainText.getBytes());
               
                enc=DatatypeConverter.printBase64Binary(encoding);
                System.out.println("Base64:\t " + enc);
               
           } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
               Logger.getLogger(JBlowfish.class.getName()).log(Level.SEVERE, null, ex);
           }
        
          return enc;       
        }
        
        public static String decrypt(String key,String ciphtext){
        
           try {
               raw	= key.getBytes();
               IV  	= "12345678";
               keySpec = new SecretKeySpec(raw, "Blowfish");
               cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
               byte[] ciphertext = DatatypeConverter.parseBase64Binary(ciphtext);
               cipher.init(Cipher.DECRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(IV.getBytes()));
               byte[] message = cipher.doFinal(ciphertext);
               
               
               plainText=new String(message);
               
                
                System.out.println("Decrypted Text is:\t " + plainText);
               
           } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
               Logger.getLogger(JBlowfish.class.getName()).log(Level.SEVERE, null, ex);
           }
        
          return plainText;       
        }
        
        

	public static void main(String[] args) throws Exception
	{
            
           // Scanner in=new Scanner(System.in);
           // System.out.println("Enter the Key:");
           // strkey=in.nextLine();
           // System.out.println("Enter the String to encrypt:");
           // plainText=in.nextLine();
            
           // String cipherText=encrypt(strkey,plainText);
           // System.out.println("The Encrypted text is ;"+cipherText);
           // String returned=decrypt(strkey,cipherText);
           // System.out.println("The Decrypted Text is :"+returned);
            encrypt(strkey,plainText);
            decrypt(strkey,cipherText);
         
            
	}
}