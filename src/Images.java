import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.AlphaComposite;
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

public class Images extends JPanel
{
    public Images() {}

	public static Image GetImage(String imgpath, int sizepercentage, double r_angle, int xywh[])
    {
		final Images imgs = new Images();
		Image img = null;
		
		img = imgs.LoadPicture(imgpath, xywh);
		img = imgs.ResizePicture(img, sizepercentage);
		img = imgs.RotatePicture(img, r_angle);
		
		return img;
    }
	
	public static Image GetImage(Image img, int sizepercentage, double r_angle, int xywh[])
    {
		final Images imgs = new Images();
		
		img = imgs.ResizePicture(img, sizepercentage);
		img = imgs.RotatePicture(img, r_angle);
		
		return img;
    }
	
    public Image MergePictures(Image img1, Image img2, int img2_pos_x, int img2_pos_y) //https://stackoverflow.com/questions/2318020/merging-two-images
    {		
    	int w = Math.max(img1.getWidth(this), img2.getWidth(this));
    	int h = Math.max(img1.getHeight(this), img2.getHeight(this));
    	
    	if (w < img2_pos_x + img2.getWidth(this))
    		w = img2_pos_x + img2.getWidth(this);
    	if (h < img2_pos_y + img2.getHeight(this))
    		h = img2_pos_y + img2.getHeight(this);
    	
    	int img1_x = 0;
    	int img1_y = 0;
    	
    	if (img2_pos_x < 0)
    	{
    		img1_x = (img2_pos_x * -1);
    		w = w + (img2_pos_x * -1);
    		img2_pos_x = 0;
    	}
    	if (img2_pos_y < 0)
    	{
    		img1_y = (img2_pos_y * -1);
    		h = h + (img2_pos_y * -1);
    		img2_pos_y = 0;
    	}
    	
    	BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    	Graphics g = combined.getGraphics();
    	
    	g.drawImage(img1, img1_x, img1_y, null);
    	g.drawImage(img2, img2_pos_x, img2_pos_y, null);

    	return combined;
    }
    
    public Image SetImageTransperancy(Image img, int percentage) //https://stackoverflow.com/questions/11552092/changing-image-opacity
    {
    	float alpha = 0.01f * (float)percentage;
    	
    	BufferedImage transperent = new BufferedImage(img.getWidth(this), img.getHeight(this), Transparency.TRANSLUCENT);
    	Graphics g = transperent.getGraphics();
    	Graphics2D g2d = (Graphics2D)g.create();
    	
    	g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
    	g2d.drawImage(img, 0, 0, this);
    	g2d.dispose();
    	
    	return transperent;
    }
    
	public boolean isPixelTransperent(gameobject go1, int pos_x, int pos_y)
	{	
		Image im = go1.img;
		
        BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        
		int x = pos_x - go1.GetX();
		int y = pos_y - go1.GetY();
		
		int pixel = bi.getRGB(x, y);
		
		  if((pixel >> 24) == 0x00)
			  return true;
		
		return false;
	}
	
    public Image RotatePicture(Image img, double r_angle) //https://stackoverflow.com/questions/4156518/rotate-an-image-in-java
    {
    	double angle = Math.toRadians(r_angle);
    	
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = img.getWidth(this), h = img.getHeight(this);
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawImage(img, 0, 0, null);
        g.dispose();
        
        return result;
    }
   
    private Image ResizePicture(Image img, int sizepercentage)	//https://www.rgagnon.com/javadetails/java-0243.html
    {
    	Image result = null;
    	
    	double imgwidth_db = ((double)(img.getWidth(this)) / 100)*sizepercentage;
    	double imgheight_db = ((double)(img.getHeight(this)) / 100)*sizepercentage;
    	
    	MediaTracker media = new MediaTracker(this);
    	media.addImage(img, 0);
        try {
            media.waitForID(0);
            ImageFilter replicate = new ReplicateScaleFilter((int)(Math.round(imgwidth_db)), (int)(Math.round(imgheight_db)));
            ImageProducer prod =  new FilteredImageSource(img.getSource(),replicate);
            result = createImage(prod);
            media.addImage(result, 1);
            media.waitForID(1);
        } 
        catch(InterruptedException e) {}
        
        return result;
    }

	private Image LoadPicture(String imgpath, int xywh[])
	{
		ImageIcon ii = new ImageIcon(imgpath);
		Image img = ii.getImage();
		Image subimg = GetSubImage(xywh, img);

		return subimg;
	}
	
    private Image GetSubImage(int xywh[], Image img)	//https://stackoverflow.com/questions/621835/how-to-extract-part-of-this-image-in-java
    {
    	BufferedImage bigImg = toBufferedImage(img);
    	Image SmallImg = bigImg.getSubimage(xywh[0], xywh[1], xywh[2], xywh[3]);
    	
    	return SmallImg;
    }
    
	private static BufferedImage toBufferedImage(Image img) //https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
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
}
