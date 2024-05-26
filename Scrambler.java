public class Scrambler {

	private int[][] inputImage;
	private int[] kR;
	private int[] kC;
	private int M, N;

	public Scrambler(int[][] iImage, int[] kR, int[] kC) {
		inputImage = iImage;
		this.kR = kR;
		this.kC = kC;
		M = inputImage.length;
		N = inputImage[0].length;
	}

	public int[][] scramble() {

		for (int i = 0; i < M; i++) {
			int sumI = 0;
			for (int j = 0; j < N; j++) {
				sumI += inputImage[i][j];
			}
			if (sumI % 2 == 0) {
				shiftRowRight(i);
			} else {
				shiftRowLeft(i);
			}
		}

		for (int j = 0; j < N; j++) {
			int sumJ = 0;
			for (int i = 0; i < M; i++) {
				sumJ += inputImage[i][j];
			}
			if (sumJ % 2 == 0) {
				shiftColumnUp(j);
			} else {
				shiftColumnDown(j);
			}
		}

		return inputImage;
	}

	public int[][] unscramble() {

		for (int j = 0; j < N; j++) {
			int sumJ = 0;
			for (int i = 0; i < M; i++) {
				sumJ += inputImage[i][j];
			}
			if (sumJ % 2 == 0) {
				shiftColumnDown(j);
			} else {
				shiftColumnUp(j);
			}
		}

		for (int i = 0; i < M; i++) {
			int sumI = 0;
			for (int j = 0; j < N; j++) {
				sumI += inputImage[i][j];
			}
			if (sumI % 2 == 0) {
				shiftRowLeft(i);
			} else {
				shiftRowRight(i);
			}
		}

		return inputImage;
	}

	private void shiftColumnDown(int colNum) {
		int shiftBy = Math.abs(kC[colNum] % M);
		int[] temp = new int[M];
		for (int i = 0; i < M; i++) {
			temp[i] = inputImage[(i - shiftBy + M) % M][colNum];
		}
		// copy
		for (int i = 0; i < M; i++) {
			inputImage[i][colNum] = temp[i];
		}
	}

	private void shiftColumnUp(int colNum) {
		int shiftBy = Math.abs(kC[colNum] % M);
		int[] temp = new int[M];
		for (int i = 0; i < M; i++) {
			temp[i] = inputImage[(i + shiftBy) % M][colNum];
		}

		for (int i = 0; i < M; i++) {
			inputImage[i][colNum] = temp[i];
		}
	}

	private void shiftRowLeft(int rowNum) {
		int shiftBy = Math.abs(kR[rowNum] % N);
		int[] temp = new int[N];
		for (int i = 0; i < N; i++) {
			temp[i] = inputImage[rowNum][(i + shiftBy) % N];
		}
		// copy
		for (int i = 0; i < N; i++) {
			inputImage[rowNum][i] = temp[i];
		}
	}

	private void shiftRowRight(int rowNum) {
		int shiftBy = Math.abs(kR[rowNum] % N);
		int[] temp = new int[N];
		for (int i = 0; i < N; i++) {
			temp[i] = inputImage[rowNum][(i - shiftBy + N) % N];
		}
		// copy
		for (int i = 0; i < N; i++) {
			inputImage[rowNum][i] = temp[i];
		}
	}

}
