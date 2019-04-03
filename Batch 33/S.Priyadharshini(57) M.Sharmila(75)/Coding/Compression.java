/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priya.akka;

/**
 *
 * @author research5
 */

import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Toolkit;
import java.awt.Image;
class AkkaCompression 
{
	public static void main(String[] args) throws Exception 
               
	{
             String f=new String();
             String NF=new String();
            for(int fi=100;fi<=300;fi++)
             {
                f="F:\\New folder\\scene00"+fi+".jpg"; 
           // f="F:\\b.jpg";
     		File input = new File(f);
     		BufferedImage image = ImageIO.read(input);
		Image imagei = Toolkit.getDefaultToolkit().getImage(f);
		PixelGrabber grabber = new PixelGrabber(imagei, 0, 0, -1, -1, false); 
		grabber.grabPixels();
		int[] data = (int[]) grabber.getPixels();
		int imgwidth = grabber.getWidth();
		int imgheight = grabber.getHeight();
	      
		int pixel[][] = new int[imgheight][imgwidth];
		for(int i=0; i<imgheight;i++)
		{
			for(int j=0;j<imgwidth;j++) 
			{
				pixel[i][j] = data[(i*imgwidth) + j];
			}
		}
                NF="F:\\256x256 compression\\output_00"+fi+".jpg";
		File compressedImageFile = new File(NF);
      		OutputStream os =new FileOutputStream(compressedImageFile);
		Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
     		ImageWriter writer = (ImageWriter) writers.next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
      		writer.setOutput(ios);
		int qf;
      		float qq;
//      		System.out.println("Enter the Quality factor");
//      		Scanner sc=new Scanner(System.in);
      		qf=10;
      		qq=(float)1/qf;
		ImageWriteParam param = writer.getDefaultWriteParam();
      		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      		param.setCompressionQuality(qq);
      		writer.write(null, new IIOImage(image, null, null), param);
      		os.close();
      		ios.close();
      		writer.dispose();
            }
		Image image1 = Toolkit.getDefaultToolkit().getImage("F:\\dct\\output3.jpg");
		PixelGrabber grabber1 = new PixelGrabber(image1, 0, 0, -1, -1, false); 
		grabber1.grabPixels();
		int[] data1 = (int[]) grabber1.getPixels();
		int pixel1[][] = new int[imgheight][imgwidth];
		for(int i=0; i<imgheight;i++)
		{
			for(int j=0;j<imgwidth;j++) 
			{
				pixel1[i][j] = data1[(i*imgwidth) + j];
			}
		}
		double mse=0;
		double psnr;
		for(int i=0;i<imgheight;i++)
		{
			for(int j=0;j<imgwidth;j++)
			{
				mse+=Math.pow((pixel[i][j]-pixel1[i][j]),2);
			}
		}
		//mse=mse/(imgheight*imgwidth);
		System.out.println("MSE="+mse);
		int rr=16777216;
		psnr=Math.abs(20*Math.log10(rr/Math.sqrt(mse)));
		System.out.println("PSNR = "+psnr);
   	}
}

