/*
Intrucoes:
1. Criar um poster de 500x600 com o fundo Background_Image.png
2. Colocar uma nova Layer com a imagem CovidNews.png numa posicao aproximadamente central
3. Colocar uma nova Layer com a mesma imagem em (0,50) e escala (1.5)
4. Exportar para uma instancia nova de ColorImage (first)
5. Colocar uma nova Layer com a selecao circular (centro (120,100) e raio (10)) da imagem DonaldDuck.png em (50,0)
6. Exportar para uma instancia nova de ColorImage (second)
7. Trocar a ordem da primeira e ultima Layers (1 com a 3)
8. Exportar para uma instancia nova de ColorImage (third)
9. Desativar a primeira Layer
10. Exportar para uma instancia nova de ColorImage (fourth)
11. Apagar a segunda Layer
12. Voltar a ativar a primeira Layer
13. Exportar para uma instancia nova de ColorImage (fifth)
*/

// METER BREAKPOINTS NOS RETURNS!
class Apresentacao {
	static ColorImage imagem = new ColorImage("CovidNews.png");
	static ColorImage donald = new ColorImage("DonaldDuck.png");

	// Testar funcoes auxiliares

	// Testar Color.WHITE
	static Color whiteTest(){
		Color white = Color.WHITE;
		return white;
	}

	// Testar Color.isWhite
	static void isWhiteTest() {
		Color white = new Color(255,255,255);
		Color black = new Color(0,0,0);
		Color red = new Color(255,0,0);
		Color green = new Color(0,255,0);
		Color blue = new Color(0,0,255);
		System.out.println("Igual a branco:");
		System.out.println("white: " + Color.isWhite(white));
		System.out.println("black: " + Color.isWhite(black));
		System.out.println("red: " + Color.isWhite(red));
		System.out.println("green: " + Color.isWhite(green));
		System.out.println("blue: " + Color.isWhite(blue));
		return;
	}

	// Testar Color.getGray
	static Color getGrayTest() {
		Color cor = new Color(23,170,124);
		Color cinzenta = Color.getGray(cor);
		return cinzenta;	
	}

	// Testar Parte 1 (Manipulation.java)

	// Testar Manipulation.copy
	static void copyTest(ColorImage image, ColorImage background, int x, int y){
		Manipulation.copy(Manipulation.scale(image,2), background, 0, 0);
		return;
	}

	// Testar Manipulation.background
	static ColorImage backgroundTest(ColorImage image, int width, int height){
		ColorImage background = Manipulation.background(image, width , height);
		return background;
	}

	// Testar Manipulation.scale
	static ColorImage scaleTest(ColorImage image, double factor){
		ColorImage scaledImage = Manipulation.scale(image, factor);
		return scaledImage;
	}

	// Testar Manipulation.circle
	static ColorImage circleTest(ColorImage image, int x, int y, int r){
		ColorImage circledImage = Manipulation.circle(image, x, y, r);
		return circledImage;
	}

	// Testar Manipulation.gray
	static ColorImage grayTest(ColorImage image){
		Manipulation.gray(image);
		return image;
	}


	// Testar Parte 2 (Layer.java)

	// Testar Metodos Construtores
	// i)
	static Layer MetodoContrutor1(ColorImage imagem, double factor, int x, int y, String nome){
		Layer layer = new Layer(imagem, factor, x, y, nome);
		return layer;
	}

	// ii)
	static Layer MetodoContrutor2(ColorImage imagem, int x, int y){
		Layer layer = new Layer(imagem,x, y);
		return layer;
	}

	// iii)
	static Layer MetodoContrutor3(ColorImage imagem){
		Layer layer = new Layer(imagem);
		return layer; 
	}

	// Testar Modificadores e Gets
	static Layer MetodoContrutor(ColorImage imagem, double factor, int x, int y, String nome){
		Layer layer = new Layer(imagem, factor, x, y, nome);
		System.out.println("Nome antigo: " + layer.getName());
		layer.setName("ISCTE");
		System.out.println("Nome novo: " + layer.getName());
		System.out.println();

		System.out.println("Posicao antiga: x->" + layer.getX() + " e y->" + layer.getY());;
		layer.setPosition(100,100);
		System.out.println("Posicao nova: x->" + layer.getX() + " e y->" + layer.getY());;
		System.out.println();

		System.out.println("Fator antigo: " + layer.getFactor());
		layer.setScale(3);
		System.out.println("Fator novo: " + layer.getFactor());
		System.out.println();
		return layer;
	}

	// Testar Parte 3 (Poster.java)

	static void Apresentacao(){
		Poster finalImage = new Poster(500, 600);

		Layer background = new Layer(new ColorImage("Background_Image.png"));
		Layer covid = new Layer(new ColorImage("CovidNews.png"), 0, 0);
		Layer covid2 = new Layer(new ColorImage("CovidNews.png"),1.5,0,50,"");

		finalImage.setBackground(background);
		finalImage.add(covid);
		finalImage.add(covid2);

		ColorImage first = finalImage.getPoster();
		System.out.println(first);

		ColorImage donaldcirc = Manipulation.circle(new ColorImage("DonaldDuck.png"), 120, 100, 10);
		Layer donaldcirclayer = new Layer(donaldcirc, 50, 0);
		finalImage.add(donaldcirclayer);

		ColorImage second = finalImage.getPoster();
		System.out.println(second);

		finalImage.swapPosition(1,3);

		ColorImage third = finalImage.getPoster();
		System.out.println(third);

		covid.setEnable(false);

		ColorImage fourth = finalImage.getPoster();
		System.out.println(fourth);

		finalImage.deletePosition(2);

		covid.setEnable(true);

		ColorImage fifth = finalImage.getPoster();
		System.out.println(fifth);

		return;
	}
}