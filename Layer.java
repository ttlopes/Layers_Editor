class Layer {

	// Variaveis
	ColorImage image;
	ColorImage duplication;
	int x;
	int y;
	double factor;
	String name;
	boolean enable;

	// Construtores
	// i)
	Layer(ColorImage image, double factor, int x, int y, String name){
		this.image = image;
		duplication = image;
		this.factor = factor;
		this.x = x;
		this.y = y;
		this.name = name;
		enable = true;
	}

	// ii)
	Layer(ColorImage image, int x, int y){
		this.image = image;
		duplication = image;
		this.factor = 1;
		this.x = x;
		this.y = y;
		this.name = null;
		enable = true;
	}

	// iii)
	Layer(ColorImage image){
		this.image = image;
		duplication = image;
		this.factor = 1;
		this.x = 0;
		this.y = 0;
		enable = true;
	}

	// Modificadores
	// 1.
	// Mudar nome da imagem
	void setName(String name){
		this.name = name;
	}

	// 2.
	// Mudar a escala
	void setScale(double factor){
		if(factor <= 0)
			throw new IllegalArgumentException("O fator tem de superior a 0");
		this.factor = factor;
	}

	// Mudar posicionamento da imagem no poster
	void setPosition(int x, int y){
		if(x < 0 || y < 0)
			throw new IllegalArgumentException("As coordenadas tem de ser numeros positivos");
		this.x = x;
		this.y = y;
	}

	// 3.
	// Definir imagem como ativa ou inativa
	void setEnable(boolean enable){
		if(enable)
			image=duplication;
		else
			image=null;
	}
	
	// 4.
	// Devolver a imagem completa da camada
	
	//Tinha o getHeight como getWidth por distracao
	
	ColorImage getLayer() {
		ColorImage finalImage;
		if(image==null){
			finalImage=null;
		}
		else{
			finalImage = new ColorImage(Manipulation.scale(image, factor).getWidth() + x, Manipulation.scale(image, factor).getHeight() + y);
			ColorImage.white(finalImage);
			Manipulation.copy(Manipulation.scale(this.image, this.factor), finalImage, x, y);
		}
		return finalImage;
	}

	// Funcoes auxiliares para o Poster
	// Devolver imagem
	ColorImage getImg(){
		return this.image;
	}

	// Devolver posicao inicial X
	int getX(){
		return this.x;
	}

	// Devolver posicao inicial Y
	int getY(){
		return this.y;
	}

	// Devolver fator da escala
	double getFactor(){
		return this.factor;
	}

	// Devolver nome
	String getName(){
		return this.name;
	}

	// Devolver estado (ativa ou inativa)
	boolean getState(){
		return this.enable;
	}
}