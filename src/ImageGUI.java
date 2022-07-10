import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ImageGUI extends JFrame
{
	static JLabel picture;
	static BufferedImage moviePicture;
	static int pictureHeight = 1;
	static int pictureWidth = 1;


	public ImageGUI() throws IOException
	{
		super("Movie Poster");

		JPanel jp = (JPanel) this.getContentPane();
		jp.add(new JLabel("Alien"));

		moviePicture = ImageIO.read(new File("../Images/rotla.jpg"));
		pictureHeight = moviePicture.getWidth();
		pictureWidth = moviePicture.getHeight();
		picture = new JLabel(new ImageIcon(moviePicture.getScaledInstance(pictureHeight, pictureWidth, Image.SCALE_DEFAULT)));
		jp.add(picture);

		this.setSize(pictureHeight, pictureWidth);
	}


	public void SetImage(String imageDir)
	{
		try
		{
			this.setTitle(imageDir);
			moviePicture = ImageIO.read(new File(imageDir));
			pictureHeight = moviePicture.getWidth();
			pictureWidth = moviePicture.getHeight();
			picture.setIcon(new ImageIcon(moviePicture.getScaledInstance(pictureHeight, pictureWidth, Image.SCALE_DEFAULT)));
			this.setSize(pictureHeight, pictureWidth);
		}
		catch (IOException e)
		{
			System.out.println("'" + imageDir + "' is not a valid path.");
		}
	}


	public static void main (String[] args) throws Exception
	{
		ImageGUI frame = new ImageGUI();
		frame.setSize(pictureHeight, pictureWidth);
		frame.setVisible(true);
	}
}
