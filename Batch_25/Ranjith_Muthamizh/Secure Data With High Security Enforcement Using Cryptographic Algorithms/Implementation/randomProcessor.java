package com.example.faster.secure_me;

import android.os.Environment;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class randomProcessor {
    File sdcard = Environment.getExternalStorageDirectory();
    File dir = new File(sdcard.getAbsolutePath(),"Secure_me");
    File encrypt = new File(dir,"Secured");
    File decrypt = new File(dir,"Original");
    File Downloads = new File(dir.getAbsolutePath(),"Downloads");
    public void encrypt(File inputFile,String sequence,String password)
    {
        byte[] dataBytes=FileTobyte(inputFile);
        if(sequence.equals("123"))
        {
            try {
                byte[] aesEncrypted = aesEncryption(dataBytes, password);
                byte[] blowFishEncrypted=blowFishEncryption(aesEncrypted,password);
                byte[] rc4Encrypted=rc4Encryption(blowFishEncrypted,password);
                saveFile(rc4Encrypted,encrypt,inputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(sequence.equals("132"))
        {
            try {
                byte[] aesEncrypted = aesEncryption(dataBytes, password);
                byte[] rc4Encrypted=rc4Encryption(aesEncrypted,password);
                byte[] blowFishEncrypted=blowFishEncryption(rc4Encrypted,password);
                saveFile(blowFishEncrypted,encrypt,inputFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(sequence.equals("213"))
        {
            try{
                byte[] blowFishEncrypted=blowFishEncryption(dataBytes,password);
                byte[] aesEncrypted=aesEncryption(blowFishEncrypted,password);
                byte[] rc4Encrypted=rc4Encryption(aesEncrypted,password);
                saveFile(rc4Encrypted,encrypt,inputFile);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(sequence.equals("231"))
        {
            try{
                byte[] blowFishEncrypted=blowFishEncryption(dataBytes,password);
                byte[] rc4Encrypted=rc4Encryption(blowFishEncrypted,password);
                byte[] aesEncrypted=aesEncryption(rc4Encrypted,password);
                saveFile(aesEncrypted,encrypt,inputFile);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(sequence.equals("312"))
        {
            try{
                byte[] rc4Encrypted=rc4Encryption(dataBytes,password);
                byte[] aesEncrypted=aesEncryption(rc4Encrypted,password);
                byte[] blowFishEncrypted=blowFishEncryption(aesEncrypted,password);
                saveFile(blowFishEncrypted,encrypt,inputFile);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(sequence.equals("321"))
        {
            try{
                byte[] rc4Encrypted=rc4Encryption(dataBytes,password);
                byte[] blowFishEncrypted=blowFishEncryption(rc4Encrypted,password);
                byte[] aesEncrypted=aesEncryption(blowFishEncrypted,password);
                saveFile(aesEncrypted,encrypt,inputFile);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void decrypt(File encryptedFile,String sequence,String password)
    {
        byte[] securedBytes = FileTobyte(encryptedFile);
        if(sequence.equals("123"))
        {
            try{
                byte[] rc4Decrypted = rc4Decryption(securedBytes,password);
                byte[] blowFishDecrypted=blowFishDecryption(rc4Decrypted,password);
                byte[] aesDecrypted=aesDecryption(blowFishDecrypted,password);
                saveFile(aesDecrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(sequence.equals("132"))
        {
            try{
                byte[] blowFishDecrypted=blowFishDecryption(securedBytes,password);
                byte[] rc4Decrypted=rc4Decryption(blowFishDecrypted,password);
                byte[] aesDecrypted=aesDecryption(rc4Decrypted,password);
                saveFile(aesDecrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(sequence.equals("213"))
        {
            try{
                byte[] rc4Decrypted=rc4Decryption(securedBytes,password);
                byte[] aesDecrypted=aesDecryption(rc4Decrypted,password);
                byte[] blowFishDecrypted=blowFishDecryption(aesDecrypted,password);
                saveFile(blowFishDecrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(sequence.equals("231"))
        {
            try{
                byte[] aesDecrypted=aesDecryption(securedBytes,password);
                byte[] rc4Decrypted=rc4Decryption(aesDecrypted,password);
                byte[] blowFishDecrypted=blowFishDecryption(rc4Decrypted,password);
                saveFile(blowFishDecrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(sequence.equals("312"))
        {
            try{
                byte[] blowFishDecrypted=blowFishDecryption(securedBytes,password);
                byte[] aesDecrypted=aesDecryption(blowFishDecrypted,password);
                byte[] rc4Decrypted=rc4Decryption(aesDecrypted,password);
                saveFile(rc4Decrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(sequence.equals("321"))
        {
            try{
                byte[] aesDecrypted=aesDecryption(securedBytes,password);
                byte[] blowFishDecrypted=blowFishDecryption(aesDecrypted,password);
                byte[] rc4Decrypted=rc4Decryption(blowFishDecrypted,password);
                saveFile(rc4Decrypted,decrypt,encryptedFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private byte[] FileTobyte(File inputFile)
    {
        byte[] input = new byte[(int)inputFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            fileInputStream.read(input);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    private void saveFile(byte[] inputBytes,File outFile,File dataFile)
    {
        if(!dir.exists()&!encrypt.exists()&!decrypt.exists()&!Downloads.exists()){
        dir.mkdir();
        encrypt.mkdir();
        decrypt.mkdir();
        Downloads.mkdir();}else {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(outFile + "/" + dataFile.getName());
                fileOutputStream.write(inputBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}
    //AES
    private SecretKeySpec generateKey(String pwd) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pwd.getBytes();
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
    private  byte[] aesEncryption(byte[] data,String password)throws Exception{
        SecretKey key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] aes = c.doFinal(data);
        return aes;
    }
    private  byte[] aesDecryption(byte[] in,String password)throws Exception{
        SecretKey key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] aes = c.doFinal(in);
        return aes;
    }
    //changes BlowFIsh
    private SecretKeySpec generateBlowFishKey(String pwd) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pwd.getBytes();
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"BlowFish");
        return secretKeySpec;
    }
    private  byte[] blowFishEncryption(byte[] data,String password)throws Exception{
        SecretKey key = generateBlowFishKey(password);
        Cipher c = Cipher.getInstance("BlowFish");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] blowFishEncryption = c.doFinal(data);
        return blowFishEncryption;
    }
    private  byte[] blowFishDecryption(byte[] data,String password)throws Exception{
        SecretKey key = generateBlowFishKey(password);
        Cipher c = Cipher.getInstance("BlowFish");
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] blowFishDecrypted = c.doFinal(data);
        return blowFishDecrypted;
    }
    //RC4
    private SecretKeySpec generateIdeaKey(String pwd) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pwd.getBytes();
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"RC4");
        return secretKeySpec;
    }
    private  byte[] rc4Encryption(byte[] data,String password)throws Exception{
        SecretKey key = generateIdeaKey(password);
        Cipher c = Cipher.getInstance("RC4");
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] blowFishEncryption = c.doFinal(data);
        return blowFishEncryption;
    }
    private  byte[] rc4Decryption(byte[] data,String password)throws Exception{
        SecretKey key = generateIdeaKey(password);
        Cipher c = Cipher.getInstance("RC4");
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] blowFishDecrypted = c.doFinal(data);
        return blowFishDecrypted;
    }

}
