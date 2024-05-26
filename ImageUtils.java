
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {

	public static int[][] compute(File file) {
		try {
			BufferedImage img = ImageIO.read(file);
			int w = img.getWidth(), h = img.getHeight();
			int pixels[][] = new int[w][h];
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					pixels[x][y] = img.getRGB(x, y);
				}
			}

			return pixels;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveImage(int pixels[][], File output) {
		int w = pixels.length;
		int h = pixels[0].length;

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				image.setRGB(i, j, pixels[i][j]);
			}
		}
		try {
			ImageIO.write(image, "png", output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
