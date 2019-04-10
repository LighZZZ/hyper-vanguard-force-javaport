import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JPanel;
import java.awt.image.ImageFilter;
import java.awt.MediaTracker;
import java.awt.Transparency;
import java.awt.image.ReplicateScaleFilter;
import java.awt.image.ImageProducer;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import sun.awt.image.ToolkitImage;
import java.util.*;

public class Images extends JPanel
{
	static int arrpos = 0;
	static ArrayList<Image> imgs = new ArrayList<Image>();
	static ArrayList<Image> resizedimgs = new ArrayList<Image>();
	static ArrayList<Integer> x = new ArrayList<Integer>();
	static ArrayList<Integer> y = new ArrayList<Integer>();
	
	////////////////////////////////////////////////////////////////
	//////////// Publicly accessable functions /////////////////////
	////////////////////////////////////////////////////////////////
	
    public Images() {}

	public static void RenderImg(String imgpath, int sizepercentage, int pos_x, int pos_y, double r_angle, int xywh[])
    {
		final Images img = new Images();
		
		arrpos = x.size();
    	img.InitPicture(imgpath, sizepercentage, r_angle, xywh);
        x.add(pos_x);
        y.add(pos_y);
        
        img.RerenderImages();
    }
    
    public static void ChangeImagePosition(int idx, int x_pos, int y_pos)
    {
    	final Images img = new Images();
    	x.set(idx, x_pos);
    	y.set(idx, y_pos);
    	img.RerenderImages();
    }
    
	////////////////////////////////////////////////////////////////
	//////////// Main picture functions ////////////////////////////
	////////////////////////////////////////////////////////////////
    
    private void InitPicture(String imgpath, int sizepercentage, double r_angle, int xywh[]) 
    {	
    	LoadPicture(imgpath, xywh);
    	ResizePicture(sizepercentage);
    	RotatePicture(r_angle);
    }
    
    private void ResizePicture(int sizepercentage)	//https://www.rgagnon.com/javadetails/java-0243.html
    {
    	Image img = imgs.get(arrpos);
    	double imgwidth_db = ((double)(img.getWidth(this)) / 100)*sizepercentage;
    	double imgheight_db = ((double)(img.getHeight(this)) / 100)*sizepercentage;
    	
    	MediaTracker media = new MediaTracker(this);
    	media.addImage(img, 0);
        try {
            media.waitForID(0);
            ImageFilter replicate = new ReplicateScaleFilter((int)(Math.round(imgwidth_db)), (int)(Math.round(imgheight_db)));
            ImageProducer prod =  new FilteredImageSource(img.getSource(),replicate);
            resizedimgs.add(createImage(prod));
            media.addImage(resizedimgs.get(arrpos),1);
            media.waitForID(1);
        } 
        catch(InterruptedException e) {}
    }
    
    private void RotatePicture(double r_angle) //https://stackoverflow.com/questions/4156518/rotate-an-image-in-java
    {
    	BufferedImage bufimg = toBufferedImage(resizedimgs.get(arrpos));
    	double angle = Math.toRadians(r_angle);
    	
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = bufimg.getWidth(), h = bufimg.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(bufimg, null);
        g.dispose();
        
        resizedimgs.set(arrpos, result);
    }

	private void LoadPicture(String imgpath, int xywh[])
	{
		ImageIcon ii = new ImageIcon(imgpath);
		Image img = ii.getImage();
		Image subimg = GetSubImage(xywh, img);
		imgs.add(subimg);
	}
	
    private Image GetSubImage(int xywh[], Image img)	//https://stackoverflow.com/questions/621835/how-to-extract-part-of-this-image-in-java
    {
    	BufferedImage bigImg = toBufferedImage(img);
    	Image SmallImg = bigImg.getSubimage(xywh[0], xywh[1], xywh[2], xywh[3]);
    	
    	return SmallImg;
    }
	
	////////////////////////////////////////////////////////////////
	//////////// Backend Functions /////////////////////////////////
	////////////////////////////////////////////////////////////////
    
    private static BufferedImage toBufferedImage(Image img)	//https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
    {
    	BufferedImage bufimg = ((ToolkitImage)img).getBufferedImage();
    	return bufimg;
    }
    
    private static GraphicsConfiguration getDefaultConfiguration() //https://stackoverflow.com/questions/4156518/rotate-an-image-in-java
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    
	private void RerenderImages()
	{
		repaint();
	}
    
    @Override	
    public void paintComponent(Graphics g)
    {
    	for (int i = 0; i < resizedimgs.size(); i++)
    	{
    		g.drawImage(resizedimgs.get(i), x.get(i), y.get(i), null);
    	}
    }
}
