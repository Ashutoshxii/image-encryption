
import java.security.SecureRandom;

public class Utils {
	public static SecureRandom rand = new SecureRandom();

	public static int[] rotate(int[] array) {
		int len = array.length;
		int[] r = new int[len];
		for (int i = 0; i < len; i++) {
			r[len - i - 1] = array[i];
		}
		return r;
	}

	public static int[][] copy(int[][] inputImage) {
		int[][] newArray = new int[inputImage.length][];
		for (int i = 0; i < inputImage.length; i++) {
			newArray[i] = new int[inputImage[i].length];
			for (int j = 0; j < inputImage[i].length; j++) {
				newArray[i][j] = inputImage[i][j];
			}
		}
		return newArray;
	}

	public static int[] generateRandomArray(int size, int bound) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(bound);
		}
		return array;
	}

}
