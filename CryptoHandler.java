
import java.io.File;

public class CryptoHandler {

	static final String FILE_EXT = ".png";
	static final String KEY_EXT = ".key";
	static final String ENC = "enc";
	static final String DEC = "dec";

	public static final int PIXEL_SIZE = 32;
	public static final int PIXEL_MAX_VALUE = (int) Math.pow(2, PIXEL_SIZE);

	public static int maxIterations = 1;

	public static void main(String[] args) throws Exception {
		checkCommandValid(args);
		String command = args[0];
		checkInputFile(new File(args[1]));
		File inputFile = new File(new File(args[1]).getAbsolutePath());

		String fileDir = inputFile.getParent() + File.separator;

		int[][] inputImage = ImageUtils.compute(inputFile);
		int M = inputImage.length;
		int N = inputImage[0].length;
		int[] kR;
		int[] kC;

		File keyFile;

		if (command.equals(DEC)) {
			keyFile = new File(args[2]);
			checkInputFile(keyFile);
			kR = new int[M];
			kC = new int[N];
			maxIterations = KeyUtils.readKey(keyFile, kR, kC);
			for (int i = 0; i < maxIterations; i++) {
				inputImage = new Decrypt().decrypt(inputImage, kR, kC);
				inputImage = new Scrambler(inputImage, kR, kC).unscramble();
			}

			String inputFileName = inputFile.getName();
			// int dotIndex = inputFileName.lastIndexOf('.');
			// String baseName = (dotIndex == -1) ? inputFileName :
			// inputFileName.substring(0, dotIndex);
			String baseName = (inputFileName.length() > 7) ? inputFileName.substring(0, inputFileName.length() - 7)
					: inputFileName;

			File decFile = new File(fileDir + baseName + DEC + FILE_EXT);

			// File decFile = new File(fileDir + inputFile.getName() + DEC + FILE_EXT);
			ImageUtils.saveImage(inputImage, decFile);

		} else {
			keyFile = new File(fileDir + inputFile.getName() + KEY_EXT);
			kR = Utils.generateRandomArray(M, PIXEL_MAX_VALUE);
			kC = Utils.generateRandomArray(N, PIXEL_MAX_VALUE);
			KeyUtils.writeKey(keyFile, kR, kC, maxIterations);
			for (int i = 0; i < maxIterations; i++) {
				inputImage = new Scrambler(inputImage, kR, kC).scramble();
				inputImage = new Encrypt().encrypt(inputImage, kR, kC);
			}

			String inputFileName = inputFile.getName();
			int dotIndex = inputFileName.lastIndexOf('.');
			String baseName = (dotIndex == -1) ? inputFileName : inputFileName.substring(0, dotIndex);

			// File encFile = new File(fileDir + inputFile.getName() + ENC + FILE_EXT);
			File encFile = new File(fileDir + baseName + ENC + FILE_EXT);

			ImageUtils.saveImage(inputImage, encFile);
		}

	}

	private static void checkCommandValid(String[] args) {
		if (args.length == 0) {
			printUsage();
		}
		String command = args[0];
		if (command.equals(ENC)) {
			if (args.length < 2) {
				printUsage();
			}
		} else if (command.equals(DEC)) {
			if (args.length < 3) {
				printUsage();
			}
		} else {
			printUsage();
		}
	}

	private static void checkInputFile(File file) {
		if (file.exists() && file.isFile()) {
			return;
		}
		System.out.println("Invalid file: " + file.getName());
	}

	private static void printUsage() {
		System.out.println("Usage: [enc filename|dec filename keyfile]");
		System.exit(1);
	}
}
