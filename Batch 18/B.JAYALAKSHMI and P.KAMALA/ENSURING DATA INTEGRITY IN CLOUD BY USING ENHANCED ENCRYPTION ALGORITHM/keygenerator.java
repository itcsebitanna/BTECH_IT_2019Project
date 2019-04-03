 import java.math.BigInteger;


public class keygenerator {
    
    public KeyGeneratorD(String s){
        strKey = s;
        binKey = new BigInteger(s, 16).toString(2);
        //add padding to begining
        while(binKey.length()%64!=0){
            binKey = '0' + binKey;
        }
        //System.out.println(binKey);
    }
    
    public String[] generateEKeys(){
        char[] PC1= new char[PC_1.length+1];
        char[] key = binKey.toCharArray();
        for(int i=0;i<PC_1.length;i++){            
            //System.out.println("i="+ i +" PC POS=" + PC_1[i] +" Value="+key[PC_1[i]-1]);
            PC1[i] = key[PC_1[i]-1];
        }  
        doEShifts(PC1);
        generateESubKeys();        
        return encKeys;
    }    
   
    private void generateESubKeys(){
        for(int j=0; j<17; j++){      
            String s = CE[j]+DE[j];
            char[] PC2= new char[PC_2.length+1];
            char[] key = s.toCharArray();
            for(int i=0; i<PC_2.length; i++){            
                PC2[i] = key[PC_2[i]-1];
            }
            encKeys[j]=new String(PC2);
            //System.out.println(keys[j]);
        }          
    }  
   
    private void doEShifts(char[] PC1){
        String sPC1 = new String(PC1);        
        String ci = sPC1.substring(0, (sPC1.length() / 2));
        String di = sPC1.substring((sPC1.length() / 2));
        di = di.trim();
        CE[0] = ci;
        DE[0] = di;
        for(int i =1; i < 17; i++){
            if(i==1 || i==2 || i==9 || i==16){
                String s1 = CE[i-1].substring(1);
                s1 = s1 + CE[i-1].substring(0,1);
                String s2 = DE[i-1].substring(1);
                s2 = s2 + DE[i-1].substring(0,1);
                CE[i]= s1;
                DE[i]= s2;
            }
            else{
                String s1 = CE[i-1].substring(2);
                s1 = s1 + CE[i-1].substring(0,2);
                String s2 = DE[i-1].substring(2);
                s2 = s2 + DE[i-1].substring(0,2);
                CE[i]= s1;
                DE[i]= s2;
            }
            //System.out.println(C[i]+" for " + i + " D "+ D[i] );
        }
    }
   
    public String[] generateDKeys() {
        char[] PC1 = new char[PC_1.length + 1];
        char[] key = binKey.toCharArray();
        for (int i = 0; i < PC_1.length; i++) {
            //System.out.println("i="+ i +" PC POS=" + PC_1[i] +" Value="+key[PC_1[i]-1]);
            PC1[i] = key[PC_1[i] - 1];
        }
        doDShifts(PC1);
        generateDSubKeys();
        return decKeys;
    }
  
    private void generateDSubKeys(){
        for(int j=16; j > 0; j--){      
            String s = CD[j]+DD[j];
            char[] PC2= new char[PC_2.length+1];
            char[] key = s.toCharArray();
            for(int i=0; i<PC_2.length; i++){            
                PC2[i] = key[PC_2[i]-1];
            }
            decKeys[j]=new String(PC2);
            //System.out.println(decKeys[j]+"= D Keys ="+encKeys[j]);
        }
          
    }
   
    private void doDShifts(char[] PC1){
        String sPC1 = new String(PC1);        
        String ci = sPC1.substring(0, (sPC1.length() / 2));
        String di = sPC1.substring((sPC1.length() / 2));
        di = di.trim();
        CD[16] = ci;
        DD[16] = di;
        for(int i = 16; i > 1; i--){
            if(i==2 || i==9 || i==16){
                String s1 = CD[i].substring(CD[i].length()-1);
                String temp1 = CD[i].substring(0,(CD[i].length()-1));
                s1 = s1 + temp1;
                String s2 = DD[i].substring(DD[i].length()-1);
                String temp2 = DD[i].substring(0,(DD[i].length()-1));
                s2 = s2 + temp2;
                CD[i-1]= s1;
                DD[i-1]= s2;
            }
            else{
                String s1 = CD[i].substring(CD[i].length()-2);
                String temp1 = CD[i].substring(0,(CD[i].length()-2));
                s1 = s1 + temp1;
                String s2 = DD[i].substring(DD[i].length()-2);
                String temp2 = DD[i].substring(0,(DD[i].length()-2));
                s2 = s2 + temp2;
                CD[i-1]= s1;
                DD[i-1]= s2;
            }
            //System.out.println(CD[i]+" for " + i + " D "+ CE[i] );
        }
    }
    //class variables
    private final String strKey;
    private String binKey;
    private final int[] PC_1 = {57,49,41,33,25,15,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
    private final int[] PC_2 = {14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
    private String[] CE = new String[17];
    private String[] DE = new String[17];
    private String[] encKeys = new String[17];
    private String[] CD = new String[17];
    private String[] DD = new String[17];
    private String[] decKeys = new String[17];
}
