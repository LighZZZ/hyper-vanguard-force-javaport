import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.image.ImageFilter;
import java.awt.MediaTracker;
import java.awt.image.ReplicateScaleFilter;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;

public class Images extends JPanel
{
	private Image img;
	private Image resizedimg;
	
    public Images(String imgpath)
    {
        InitPicture(imgpath);
    }
    
    public void InitPicture(String imgpath) 
    {
    	LoadPicture(imgpath);
    	ResizePicture();
        
        int w = resizedimg.getWidth(this);
        int h = resizedimg.getHeight(this);
        setPreferredSize(new Dimension(w, h));        
    }
    
    private void ResizePicture()	//https://www.rgagnon.com/javadetails/java-0243.html
    {
    	MediaTracker media = new MediaTracker(this);
    	media.addImage(img, 0);
        try {
            media.waitForID(0);
            // scale down, half the original size  
            ImageFilter replicate = 
            	new ReplicateScaleFilter
                 (img.getWidth(this)/2, img.getHeight(this)/2);
            ImageProducer prod = 
               new FilteredImageSource(img.getSource(),replicate);
            resizedimg = createImage(prod);
            media.addImage(resizedimg,1);
            media.waitForID(1);
        } 
        catch(InterruptedException e) {}
    }

	private void LoadPicture(String imgpath)
	{
		ImageIcon ii = new ImageIcon(imgpath);
		img = ii.getImage();
	}
	
    public void paintComponent(Graphics g) 
    {
        g.drawImage(resizedimg, 0, 0, null);
    }
}
