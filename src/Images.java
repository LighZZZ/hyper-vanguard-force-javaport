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
import java.util.*;

public class Images extends JPanel
{
	static int arrpos = 0;
	static ArrayList<Image> imgs = new ArrayList<>();
	static ArrayList<Image> resizedimgs = new ArrayList<>();
	static ArrayList<Integer> x = new ArrayList<>();
	static ArrayList<Integer> y = new ArrayList<>();
	
    public Images(String imgpath, int sizepercentage, int pos_x, int pos_y)
    {
    	arrpos = x.size();
    	InitPicture(imgpath, sizepercentage);
        x.add(pos_x);
        y.add(pos_y);
    }
    
    public void InitPicture(String imgpath, int sizepercentage) 
    {	
    	LoadPicture(imgpath);
    	ResizePicture(sizepercentage);
        
        int w = resizedimgs.get(arrpos).getWidth(this);
        int h = resizedimgs.get(arrpos).getHeight(this);
        setPreferredSize(new Dimension(w, h));    
    }
    
    private void ResizePicture(int sizepercentage)	//https://www.rgagnon.com/javadetails/java-0243.html
    {
    	MediaTracker media = new MediaTracker(this);
    	media.addImage(imgs.get(arrpos), 0);
        try {
            media.waitForID(0);
            ImageFilter replicate = new ReplicateScaleFilter((imgs.get(arrpos).getWidth(this)/100)*sizepercentage, (imgs.get(arrpos).getHeight(this)/100)*sizepercentage);
            ImageProducer prod =  new FilteredImageSource(imgs.get(arrpos).getSource(),replicate);
            resizedimgs.add(createImage(prod));
            media.addImage(resizedimgs.get(arrpos),1);
            media.waitForID(1);
        } 
        catch(InterruptedException e) {}
    }

	private void LoadPicture(String imgpath)
	{
		ImageIcon ii = new ImageIcon(imgpath);
		imgs.add(ii.getImage());
	}
	
    public void paintComponent(Graphics g)
    {
    	for (int i = 0; i < resizedimgs.size(); i++)
    	{
    		g.drawImage(resizedimgs.get(i), x.get(i), y.get(i), null);
    	}
    }
}
