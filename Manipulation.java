class Manipulation {
	// 1.
	// Copiar uma imagem(image) para um fundo(background)
	static void copy(ColorImage image, ColorImage background, int x, int y){
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("As coordenadas tem de ser numeros positivos");
		if(x > background.getWidth() || y > background.getHeight())
			throw new IllegalArgumentException("As coordenadas excedem o tamanho da tela");
		if(image!=null)
			for(int i=x; i < background.getWidth() && i-x < image.getWidth(); i++)
				for(int j=y; j < background.getHeight()  && j-y < image.getHeight(); j++)
					if(!(Color.isWhite(image.getColor(i-x,j-y))))
						background.setColor(i, j, image.getColor(i-x,j-y));
	}

	// 2.
	// Fazer um fundo usando uma imagem(image) e dando um tamanho para a tela final
	static ColorImage background(ColorImage image, int width, int height){
		if(width <= 0 || height <= 0)
			throw new IllegalArgumentException("As coordenadas tem de ser superiores a 0");
		ColorImage background = new ColorImage(width, height);
		if(image != null)
			for(int x=0; x<width; x+=image.getWidth())
				for(int y=0; y<height; y+=image.getHeight())
					for(int smallx=0; smallx<image.getWidth(); smallx++)
						for(int smally=0; smally<image.getHeight(); smally++)
							if((x+smallx < width) && (y+smally < height))
								background.setColor(x+smallx, y+smally, image.getColor(smallx,smally));
		return background;
	}

	// 3.
	// Aumentar/diminuir uma imagem usando um fator(factor)
	static ColorImage scale(ColorImage image, double factor){
		if(factor <= 0)
			throw new IllegalArgumentException("O fator tem de ser superior a 0");
		ColorImage scaledImage = new ColorImage((int) (image.getWidth() * factor), (int) (image.getHeight() * factor));
		for(int x=0; x<scaledImage.getWidth(); x++) 
			for(int y=0; y<scaledImage.getHeight(); y++) 
				scaledImage.setColor(x,y,image.getColor((int)(x/factor),(int)(y/factor)));
		return scaledImage;
	}

	// 4.
	// Recortar um circulo de uma imagem(image) dando um x, y e raio(r), os "cantos" v�o ser tranparentes(WHITE)
	static ColorImage circle(ColorImage image, int x, int y, int r){
		if(x-r < 0 || y-r < 0 || x+r > image.getWidth() || x+r > image.getHeight())
			throw new IllegalArgumentException("O x ou y sao valores impossiveis devido ao raio, ou porque ficam negativos ou porque excedem o tamanho da tela");
		ColorImage circledImage = new ColorImage(r*2,r*2);
		double distance;
		for(int i=x-r; i< x+r; i++){
			for(int j=y-r; j< y+r; j++){
				distance = Math.sqrt(Math.pow((x-i),2) + Math.pow((y-j),2));
				if(distance<=r)
					circledImage.setColor(i-x+r,j-y+r,image.getColor(i,j));
				else
					circledImage.setColor(i-x+r,j-y+r,Color.WHITE);
			}
		}
		return circledImage;
	}

	// 5.
	// Devolver a imagem(image) com tons cinzentos
	static void gray(ColorImage image){
		if(image == null)
			throw new NullPointerException("Imagem inexistente");
		for(int x=0; x < image.getWidth(); x++)
			for(int y=0; y < image.getHeight(); y++)
				image.setColor(x, y, Color.getGray(image.getColor(x,y)));	
	}
}