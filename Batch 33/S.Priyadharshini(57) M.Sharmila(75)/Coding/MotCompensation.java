/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priya.sharmi;

/**
 *
 * @author research5
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.sun.image.codec.jpeg.*;
 class MotCompensation
{
	static int height,width;
	public static void main(String args[]) throws Exception
	{
		//Reading the current_frame
		Image image = Toolkit.getDefaultToolkit().getImage("F:\\New folder\\scene00001.jpg");
		PixelGrabber grabber = new PixelGrabber(image, 0, 0, -1, -1, false); 
		grabber.grabPixels();
		width = grabber.getWidth();
		height = grabber.getHeight();
		int[] data = (int[]) grabber.getPixels();
		int pixel[][] = new int[height][width];
		int G1[][] = new int[height][width];
		int G2[][][][]=new int[height/2][width/2][2][2];
		int G4[][][][]=new int[height/4][width/4][4][4];
		int R2[][][][]=new int[height/2][width/2][2][2]; 
		int R4[][][][] = new int[height/4][width/4][4][4];
		//Reading the next_frame
		Image imagenext = Toolkit.getDefaultToolkit().getImage("F:\\New folder\\scene00002.jpg");
		PixelGrabber grabbernext = new PixelGrabber(imagenext, 0, 0, -1, -1, false);
		grabbernext.grabPixels();
		int width_next = grabbernext.getWidth();
		int height_next = grabbernext.getHeight();
		int[] data_next = (int[]) grabbernext.getPixels();
		int pixel_next[][] = new int[height][width];
		int G1_next[][] = new int[height][width];
		int G2_next[][][][]=new int[height/2][width/2][2][2];
		int G4_next[][][][]=new int[height/4][width/4][4][4];
		int R2_next[][][][]=new int[height/2][width/2][2][2]; 
		int R4_next[][][][] = new int[height/4][width/4][4][4];
		for(int i=0; i<height;i++)
		{
			for(int j=0;j<width;j++) 
			{
				pixel[i][j] = data[(i*width) + j];
				pixel_next[i][j] = data_next[(i*width) + j];
			}
		}
		//converting into gray scale
		int alpha, red, green, blue= 0;
        int q=0;
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                alpha = (data[q] >> 24) & 0xff;
                red = (data[q] >> 16) & 0xff;
                green = (data[q] >> 8) & 0xff;
                blue = (data[q++]) & 0xff;
                G1[i][j] = (red + green+ blue) / 3;
            }
        }
        int alpha_next,red_next, green_next, blue_next= 0;
        q=0;
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                alpha_next = (data_next[q] >> 24) & 0xff;
                red_next = (data_next[q] >> 16) & 0xff;
                green_next = (data_next[q] >> 8) & 0xff;
                blue_next = (data_next[q++]) & 0xff;
                G1_next[i][j] = (red_next + green_next+ blue_next) / 3;
            }
        }
        int m=0,n=0;
		for(int i=0,row=0;i<height;i=i+2)
        {
        	for(int j=0,col=0;j<width;j=j+2)
        	{
        		for (int x = i;x<i+2;x++)
        		{
        			for(int y = j;y<j+2;y++)
        			{
        				G2[row][col][m][n] = G1[x][y];
        				G2_next[row][col][m][n] = G1_next[x][y];
        				R2[row][col][m][n] = pixel[x][y];
        				R2_next[row][col][m][n] = pixel_next[x][y];
        				n = n+1;
	        		}
        			n=0;
        			m=m+1;
        		}
        		m = 0;
        		col++;
        	}
        	row++;
        }
		m=0;
		n=0;
		for(int i=0,row=0;i<height;i=i+4)
        {
        	for(int j=0,col=0;j<width;j=j+4)
        	{
        		for (int x = i;x<i+4;x++)
        		{
        			for(int y = j;y<j+4;y++)
        			{
        				G4[row][col][m][n] = G1[x][y];
        				G4_next[row][col][m][n] = G1_next[x][y];
        				R4[row][col][m][n] = pixel[x][y];
        				R4_next[row][col][m][n] = pixel_next[x][y];
        				n = n+1;
	        		}
        			n=0;
        			m=m+1;
        		}
        		m = 0;
        		col++;
        	}
        	row++;
        }
		//opt for 4*4 block
		int F1[][][][] = new int[height/4][width/4][4][4];
        int F2[][][][] = new int[height/4][width/4][4][4];
        int F1_next[][][][] = new int[height/4][width/4][4][4];
        int F2_next[][][][] = new int[height/4][width/4][4][4];
		int OPT_forward_operator_four[][] = {{1,-3,1,-1},{1,-1,-1,3},{1,1,-1,-3},{1,3,1,1}};
        int OPT_forward_operator_transpose_four[][] = {{1,1,1,1}, {-3,-1,1,3},{1,-1,-1,1},{-1,3,-3,1}};
        for (int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{
        		for (int x = 0; x < 4; x++) 
        		{
        			for (int y = 0; y < 4; y++) 
        			{
        				for (int k = 0; k < 4; k++) 
        				{
        					F1[row][col][x][y] += (OPT_forward_operator_transpose_four[x][k] * G4[row][col][k][y]);
        					F1_next[row][col][x][y] += (OPT_forward_operator_transpose_four[x][k] * G4_next[row][col][k][y]);
        				}
        			}
        		}
        	}
        }
 
        for ( int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{
        		for (int x = 0; x < 4; x++)
        		{
        			for (int y = 0; y < 4; y++)
        			{
        				for (int k = 0; k < 4; k++) 
        				{
        					F2[row][col][x][y] += (F1[row][col][x][k] * OPT_forward_operator_four[k][y]);
        					F2_next[row][col][x][y] += (F1_next[row][col][x][k] * OPT_forward_operator_four[k][y]);
        				}
        			}
        		}
        	}
        }
        //opt for 2*2 block
        int M1[][][][] = new int[height/2][width/2][2][2];
        int M2[][][][] = new int[height/2][width/2][2][2];
        int M1_next[][][][] = new int[height/2][width/2][2][2];
        int M2_next[][][][] = new int[height/2][width/2][2][2];
        int OPT_forward_operator_two[][] = {{1,-1},{1,1}};
        int OPT_forward_operator_transpose_two[][] = {{1,1}, {-1,1}};
        for (int row = 0; row < height/2; row++) 
        {
        	for (int col = 0; col <width/2; col++) 
        	{
        		for (int x = 0; x < 2; x++) 
        		{
        			for (int y = 0; y < 2; y++) 
        			{
        				for (int k = 0; k < 2; k++) 
        				{
        					M1[row][col][x][y] += (OPT_forward_operator_transpose_two[x][k] * G2[row][col][k][y]);
        					M1_next[row][col][x][y] += (OPT_forward_operator_transpose_two[x][k] * G2_next[row][col][k][y]);
        				}
        			}
        		}
        	}
        }

        for (int row = 0; row < height/2; row++) 
        {
        	for (int col = 0; col < width/2; col++) 
        	{
        		for (int x = 0; x < 2; x++)
        		{
        			for (int y = 0; y < 2; y++)
        			{
        				for (int k = 0; k < 2; k++) 
        				{
        					M2[row][col][x][y] += (M1[row][col][x][k] * OPT_forward_operator_two[k][y]);
        					M2_next[row][col][x][y] += (M1_next[row][col][x][k] * OPT_forward_operator_two[k][y]);
        				}
        			}
        		}
        	}
        }
        //calculation of opt difference
        int opt_diff[][][][] = new int[height/4][width/4][4][4];
        int mot_vec[][][][]=new int[height/2][width/2][1][2];
        int sum=0;
        int r,c;
        int mad[][]=new int[height/4][width/4];
        for (int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{
        		for (int i = 0; i < 4; i++)
        		{
        			for (int j = 0; j < 4; j++)
        			{
        				opt_diff[row][col][i][j]=F2[row][col][i][j]-F2_next[row][col][i][j];
        			
        			}
        		}
        	}
        }
        for (int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{
        		for (int i = 0; i < 4; i++)
        		{
        			for (int j = 0; j < 4; j++)
        			{
        				sum+=Math.abs(opt_diff[row][col][i][j]);
        			}
        		}
        		mad[row][col]=sum/16;
        		sum=0;
        		System.out.println(mad[row][col]);
        	}
        	
        }int count=0;
        for (int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{
        		if(mad[row][col]>20)
        		{
        			count++;
        		}
        	}
        }
        System.out.println("count"+count);
        int opt_diff_two[][][][]=new int[2][2][2][2];
        for (int row = 0; row < height/4; row++) 
        {
        	for (int col = 0; col < width/4; col++) 
        	{ 
        		if((mad[row][col])>64)
        		{
        			r=row*2;
        			c=col*2;
        			//Calculating opt_diff for different blocks
        			for(int x=r,l=0;x<r+2;x++)
        			{
        				for(int y=c,k=0;y<c+2;y++)
        				{
        					for(int i=0;i<2;i++)
        					{
        						for(int j=0;j<2;j++)
        						{
        							opt_diff_two[l][k][i][j]=M2[x][y][i][j]-M2_next[x][y][i][j];
        						}
        					}
        					k++;
	        			}
        				l++;
        			}
        			//Calculation of MAD for 2*2 block
        			sum=0;
        			int mad_two[][]=new int[2][2];
        			for(int x=r,l=0;x<r+2;x++)
        			{
        				for(int y=c,k=0;y<c+2;y++)
        				{
        					for(int i=0;i<2;i++)
        					{
        						for(int j=0;j<2;j++)
        						{
        							sum+=Math.abs(opt_diff_two[l][k][i][j]);
        						}
        					}
        					mad_two[l][k]=sum/4;
        					sum=0;
        					k++;
	        			}
        				l++;
        			}
        			//Motion vector calculation
        			for(int x=r,l=0;x<r+2;x++)
        			{
        				for(int y=c,k=0;y<c+2;y++)
        				{
        					if(mad_two[l][k]!=0)
        					{
        						int rc_cal[]=index_cal(x,y,M2,M2_next);
        						mot_vec[x][y][0][0]=rc_cal[0]-x;
        						mot_vec[x][y][0][1]=rc_cal[1]-y;
        					}
        					k++;
        				}
        				l++;
	        		}
        		}
        	}
        }
        //Displaying motion vectors
        for(int x=0;x<height/2;x++) 
        {
        	for(int y=0;y<width/2;y++)
        	{
        		System.out.println(x+" "+y+" mot_vec_row "+mot_vec[x][y][0][0]+" mot_vec_col "+mot_vec[x][y][0][1])	;
        	}
        }
        //Motion compensation
        int Mot_comp[][][][]=new int[height/2][width/2][2][2];
        for(int x=0;x<height/2;x++)
        {        
       		for(int y=0;y<width/2;y++)
       		{
       			for(int i=0;i<2;i++)
			    {        
			       	for(int j=0;j<2;j++)
			       	{
			       		if( (x + (mot_vec[x][y][0][0]) >=0 )&&(y +(mot_vec[x][y][0][1]) >=0)&&(x + (mot_vec[x][y][0][0]) <height/2 )&& (y+(mot_vec[x][y][0][1]) <width/2) )
			       		{
			       			Mot_comp[x][y][i][j]=R2[x+(mot_vec[x][y][0][0])][y+(mot_vec[x][y][0][1])][i][j];
			       		}
			       	}
			    }  
       		}
        }
        //Writing the predicted image
        int[] mot=new int[height*width];
        int in=0;
        for(int x=0;x<height/2;x++)
        {        
       		for(int i=0;i<2;i++)
			{ 
       			for(int y=0;y<width/2;y++)
       	       	{
			       	for(int j=0;j<2;j++)
			       	{
			       		mot[in++]= Mot_comp[x][y][i][j];
			       	}
			    }  
       		}
        }
        writeImage("F:\\Project\\Sample\\opt_output4_2_foreman_reversemad.jpg",mot,width,height,imagenext);
        
	}//end_of_main
	//diamond search
			public static int[] index_cal(int row, int col,int[][][][] opt1,int[][][][] opt2 )
        	{   
        		int r=row;
        		int c=col;
        		int stepsize=1;
        		while(stepsize<=4)//step size
        		{
        			int sub_block[][][]=new int[9][2][2];
        			int diff[][][]=new int[9][2][2];
        			int row1=row-stepsize;
        			int row2=row;
        			int row3=row+stepsize;
        			int col1=col-stepsize;
        			int col2=col;
        			int col3=col+stepsize;
        			//sub block
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row1>=0&&col1>=0&&row1<(height/2)&& col1<(width/2))
        					{
        					   sub_block[0][i][j]=opt2[row1][col1][i][j];
        					}
        				}
        			} 
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row1>=0&&col2>=0&&row1<(height/2)&&col2<(width/2))
        					{
        						sub_block[1][i][j]=opt2[row1][col2][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row1>=0&&col3<(width/2)&&row1<(height/2)&&col3>=0)
        					{
        						sub_block[2][i][j]=opt2[row1][col3][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row2>=0&&col1>=0&&row2<(height/2)&&col1<(width/2))
        					{
        						sub_block[3][i][j]=opt2[row2][col1][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row2>=0&&col2>=0&&row2<(height/2)&&col2<(width/2))
        					{
        						sub_block[4][i][j]=opt2[row2][col2][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row2>=0&&col3<(width/2)&&col3>=0&&row2<(height/2))
        					{
        						sub_block[5][i][j]=opt2[row2][col3][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row3<(height/2)&&col1>=0&&row3>=0&&col1<(width/2))
        					{
        						sub_block[6][i][j]=opt2[row3][col1][i][j];
        					}
        				}
        			} 
        			for(int i=0;i<2;i++)
        			{
        				for(int j=0;j<2;j++)
        				{
        					if(row3<(height/2)&&col2>=0&&row3>=0&&col2<(width/2))
        					{
        						sub_block[7][i][j]=opt2[row3][col2][i][j];
        					}
        				}
        			}
        			for(int i=0;i<2;i++)
        			{	
        				for(int j=0;j<2;j++)
        				{
        					if(row3<(height/2)&&col3<(width/2)&&row3>=0&&col3>=0)
        					{
        						sub_block[8][i][j]=opt2[row3][col3][i][j];
        					}
        				}
        			} 
        			for(int k=0;k<9;k++)
        			{
        				for(int i=0;i<2;i++)
        				{
        					for(int j=0;j<2;j++)
        					{
        						//System.out.print(sub_block[k][i][j]+" ");
        					}
        					//System.out.println();
        				}
        				//System.out.println();
        				//System.out.println();
        			}
        			//System.out.println();
        			//difference
        			for(int k=0;k<9;k++)
        			{
        				for(int i=0;i<2;i++)
        				{
        					for(int j=0;j<2;j++)
        					{
        						diff[k][i][j]=sub_block[k][i][j] - opt1[r][c][i][j];
        					}
        				}
        			}
        			for(int k=0;k<9;k++)
        			{
        				for(int i=0;i<2;i++)
        				{
        					for(int j=0;j<2;j++)
        					{
        						//System.out.print(diff[k][i][j]+" ");
        					}//System.out.println();
        				}//System.out.println();
        				//System.out.println();
        			}
        			int mad[]=new int[9];
        			for(int k=0;k<9;k++)
        			{
        				int sum=0;
        				for(int i=0;i<2;i++)
        				{
        					for(int j=0;j<2;j++)
        					{
        						sum+=Math.abs(diff[k][i][j]);
        					}
        				}sum=sum/4;
        				mad[k]=sum;	//mad calculation
        			}
        			for(int j=0;j<9;j++)
        			{
        				//System.out.println("mad "+mad[j]);
        			}
        			 int index = 0;
        			 int min = mad[index];
        			 for (int i=1; i<mad.length; i++)
        			 {
        				 if(mad[i] < min )
        				 {
        					 min = mad[i];
        			         index = i;
        			     }
        			 }//System.out.println("index "+ index);
        			stepsize=stepsize*2;
        			if (index==0||index==1||index==2)
        			{
        				row=row1;
        			}
        			if (index==3||index==4||index==5)
        			{
        				row=row2;
        			}
        			if (index==6||index==7||index==8)
        			{
        				row=row3;
        			}
        			if (index==0||index==3||index==6)
        			{
        				col=col1;
        			}
        			if (index==1||index==4||index==7)
        			{
        				col=col2;
        			}
        			if (index==2||index==5||index==8)
        			{
        				col=col3;
        			}
        		}return new int[] {row,col};
        	}//end of index_cal
        	public static  void writeImage(String name,int[] data,int imgwidth,int imgheight,Image img) 
        	{
        		try
        		{
        			PixelGrabber pgr = new PixelGrabber(img,0,0,1,1,false);
        			pgr.grabPixels();
        			ColorModel cm = pgr.getColorModel();
        			MemoryImageSource m = new MemoryImageSource(imgwidth,imgheight,cm,data,0,imgwidth);
        			Image img1 = Toolkit.getDefaultToolkit().createImage(m);
        			BufferedImage bi = new BufferedImage(imgwidth,imgheight,BufferedImage.TYPE_INT_RGB);
        			Graphics2D g2d = bi.createGraphics();
        			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        			g2d.drawImage(img1,0,0,imgwidth,imgheight,null);
        			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(name));
        			JPEGImageEncoder jiee = JPEGCodec.createJPEGEncoder(out);
        			JPEGEncodeParam par = jiee.getDefaultJPEGEncodeParam(bi);
        			par.setQuality(100/100.0f,false);
        			jiee.setJPEGEncodeParam(par);
        			jiee.encode(bi);
        			out.close();
        		}
        		catch(Exception e)
        		{
        			System.out.println(e);
        		}
        	}//end of writeImage
}//end_of_class
