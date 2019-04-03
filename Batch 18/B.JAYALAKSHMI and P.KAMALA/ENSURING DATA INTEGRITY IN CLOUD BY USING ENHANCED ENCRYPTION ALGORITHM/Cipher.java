import java.math.BigInteger;

public class Cipher {

 
    public CipherText(){
        c1= BigInteger.ZERO;
        c2 = BigInteger.ZERO;
    }
    
    public CipherText(BigInteger ct1,BigInteger ct2){
        c1 = ct1;
        c2 = ct2;
    }
    
    
    public BigInteger getCipher1(){
        return c1;
    }
    
  
    public BigInteger getCipher2(){
        return c2;
    }
    
    public String toHex(){
        String str1= c1.toString(16);
        String str2= c2.toString(16);
        return str1+str2;        
    }
    
    private final BigInteger c1;
    private final BigInteger c2;
}
