import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageGUI {
	static JLabel picture;
	static BufferedImage moviePicture;
	static int pictureHeight = 1;
	static int pictureWidth = 1;

	public ImageGUI() throws IOException
	{
		moviePicture = ImageIO.read(new File("./Images/rotla.jpg"));
		pictureHeight = moviePicture.getWidth() / 2;
		pictureWidth = moviePicture.getHeight() / 2;
		picture = new JLabel(new ImageIcon(moviePicture.getScaledInstance(
			pictureHeight, pictureWidth, Image.SCALE_DEFAULT)));
	}

	public void SetImage(String imageDir)
	{
		try {
			moviePicture = ImageIO.read(new File(imageDir));
			pictureHeight = moviePicture.getWidth() / 2;
			pictureWidth = moviePicture.getHeight() / 2;
			picture.setIcon(new ImageIcon(moviePicture.getScaledInstance(
				pictureHeight, pictureWidth, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			System.out.println("'" + imageDir + "' is not a valid path.");
		}
	}
}
