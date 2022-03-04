/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */
class Color {

	private final int[] rgb; // @color

	// Creates an RGB color. Provided values have to be in the interval [0, 255]

	Color(int r, int g, int b) {
		if(!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);

		this.rgb = new int[] {r, g, b};
	}

	// Red value [0, 255]
	int getR() {
		return rgb[0];
	}


	// Green value [0, 255]
	int getG() {
		return rgb[1];
	}

	// Blue value [0, 255]
	int getB() {
		return rgb[2];
	}


	// Obtains the luminance in the interval [0, 255].

	int getLuminance() {
		return (int) Math.round(rgb[0]*.21 + rgb[1]*.71 + rgb[2]*.08);
	}

	static boolean valid(int value) {
		return value >= 0 && value <= 255;
	}

	// Funções auxiliares
	
	// Cor tranparente
	static final Color WHITE = new Color(255,255,255);

	// Verifica se é não branco
	static boolean isWhite(Color c) {
		return c.getR() == 255 && c.getG() == 255 && c.getB() == 255;	
	}

	// Obter os tons cinzentos de uma dada cor usando a fórmula dada no enunciado
	static Color getGray(Color c) {
		int gray = (int)((c.getR() * 0.59) + (c.getG() * 0.3) + (c.getB() * 0.11));
		return new Color(gray,gray,gray);	
	}
}