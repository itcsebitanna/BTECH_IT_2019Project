/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class ImageLogTransformation {
	
   static public int BinaryToDecimal(String binaryNumb){
 
        int binaryNumber = Integer.parseInt(binaryNumb);
        int decimal = 0;
        int p = 0;
        while(true){
        if(binaryNumber == 0){
        break;
        } else {
            int temp = binaryNumber%10;
            decimal += temp*Math.pow(2, p);
            binaryNumber = binaryNumber/10;
            p++;
       }
    }
    return decimal;
  }
 static int decToBinary(int n) 
    { 
        // array to store binary number 
        int[] binaryNum = new int[1000]; 
   
        System.out.println(n);
        int ans =0;
        String bin = "";
        // counter for binary array 
        int i = 0; 
        while (n > 0)  
        { 
            // storing remainder in binary array 
            binaryNum[i] = n % 2; 
            n = n / 2; 
            bin =  binaryNum[i]+bin;
            i++;
        } 
         bin = "0"+bin.substring(1,bin.length());
       
        // printing binary array in reverse order 
        for (int j = i - 1; j >= 0; j--) 
        {
            
            System.out.print(binaryNum[j]); 
        }
        System.out.println();
        ans = BinaryToDecimal(bin);
        System.out.println(bin);
        System.out.println(ans);
        return ans;
    }
	public static void main(String[] args) throws Exception{
		File f = new File("F:\\res.jpg");
	Image imagei = Toolkit.getDefaultToolkit().getImage("F:\\f2.jpg");
	PixelGrabber grabber = new PixelGrabber(imagei, 0, 0, -1, -1, false); 
	grabber.grabPixels();
	int[] data = (int[]) grabber.getPixels();
    int imgwidth = grabber.getWidth();
	int imgheight = grabber.getHeight();
      int siz = imgwidth*imgheight;
      int[] y1 = new int[siz];
      int[] y2 = new int[siz];
      int[] cb = new int[siz];
      int[] cr = new int[siz];
      
  int qf =1;
  int alpha=255, red=0, green=0, blue= 0;
  int q=0;
  int sum=0;
  for (int i = 0; i < data.length; i++)
      {
       
          alpha = (data[q] >> 24) & 0xff;
          red = (data[q] >> 16) & 0xff;
          green = (data[q] >> 8) & 0xff;
          blue = (data[q]) & 0xff;
          y1[q] = (int)((0.257 * red) + (0.504 * green) + (0.098 * blue) + 16);
          y2[q] = (int)((0.257 * red) + (0.504 * green) + (0.098 * blue) + 16);
         cb[q]= (int) (128 - (0.169*red) - (0.331* green) + 0.500 * blue);
          cr[q]= (int) (128+ (0.500*red)-(0.419*green)-(0.081*blue));
         sum = sum +y1[q];
          q++;
  }
  int avg =  sum/q;
  System.out.println(avg+" "+q+" "+imgheight*imgwidth);
		int a1 = 200;
                decToBinary(a1);
     		BufferedImage img = new BufferedImage(imgwidth,imgheight,BufferedImage.TYPE_INT_RGB);
			int e=0;
                       int temp = 0;
			int a=255;
                        int p = 0;
                        int count =65536;
                        while(avg-temp >35)
                        {
                            sum =0;
                            count =1;
                            e=0;
			for(int y=0; y<imgheight;y++) {
				for(int x=0;x<imgwidth;x++) {
                                  if(y1[e]>0)
                                  {                   
					p = (a<<24) | (y1[e]<<16) |(y1[e]<<8) | y1[e];
                                        count++;
                                  }
                                  else 
                                  {
                                     y1[e]=0;
                                      p = 0;
                                  }
                    e++;
					//img.setRGB(x, y, p);
				}
                             // System.out.println();
			}
                        for(int i=0;i<data.length;i++)
                        {
                            sum = sum +y1[i];
                        }
                      
                        temp = avg;
                        avg = sum/count;
                        System.out.println(avg+" "+count+" "+temp);
                        if(temp == avg)
                            break;
                        } 
                        e=0;
                       for(int y=0; y<imgheight;y++) {
				for(int x=0;x<imgwidth;x++) {
                                if(y1[e]>0)
                                {
                                  y2[e] = decToBinary(y2[e]);  
                                }
                                p = (a<<24) | (y2[e]<<16) |(y2[e]<<8) | y2[e];
                                 int qq = (y2[e] << 24) | (cb[e] << 16) | (cr[e]<<8);
                                img.setRGB(x, y,p);
                                e++;
                                }
                                }
                        /*for(int y=0; y<imgheight;y++) {
				for(int x=0;x<imgwidth;x++) {
                                if(y2[e]>temp)
                                    y2[e]=temp;
                              
                                red = (int) ((y2[e]-((0.504 * green) + (0.098 * blue) + 16))/0.257);
                                green = (int) ((y2[e]-((0.257 * red)+(0.098 * blue) + 16))/0.504);
                                blue = (int) ((y2[e]-((0.257 * red) + (0.504 * green)+16))/0.098);
                                int qq = (a<<24) | (red<<16) | (green<<8) | blue;
                                img.setRGB(x, y,qq);
                                e++;
                                }
                                }*/
                       
			ImageIO.write(img, "jpg" ,f);
                        
	}
}