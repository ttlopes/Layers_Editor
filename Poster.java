class Poster {

	//	Tamanho da colecao e variaveis
	static final int SIZE = 8;
	Layer[] layerCollection = new Layer[SIZE];
	int width = 0;
	int height = 0;
	int next = 0;


	//	Metodo Construtor
	Poster(int width, int heigth){
		this.height = heigth;
		this.width = width;
	}

	//	1.
	//	Define a Layer Background (primeira Layer)
	void setBackground(Layer Background) {
		if(Background == null)
			throw new NullPointerException("Nao foi escolhida nenhuma imagem");
		layerCollection[0] = new Layer(Manipulation.background(Background.getLayer(), this.width, this.height));
		if(next == 0)
			next++;
	}

	//	2.
	//	Adiciona (no fim da colecao) uma layer nova
	void add(Layer newLayer){
		if(next>SIZE)
			throw new IllegalStateException("Nao existe mais espaco para camadas no Poster");
		layerCollection[next] = newLayer;
		next++;
	}

	//	3.
	//	Remover uma layer da colecao num posicacao dada, deslocando as restantes
	void deletePosition(int position){
		if(layerCollection[position]==null)
			throw new NullPointerException("Esta layer esta vazia!");
		if(layerCollection[position]==null){
			System.out.println("Esta layer esta vazia!");
			return;
		}
		for(int i = position; i < next; i++)
			layerCollection[i] = layerCollection[i+1]; // estava i++ em vez de i+1
		next--;
	}

	//	4.
	//	Adiciona uma layer nova numa dada posicao
	void newPosition(Layer newLayer, int position){
		if(position > SIZE)
			throw new IllegalArgumentException("A posicao escolhida e superior a capacidade do Poster");
		if(next++ > SIZE)
			throw new IllegalStateException("Nao existe mais capacidade para camadas no Poster");
		for(int i = position; i < layerCollection.length; i++)
			layerCollection[i+1] = layerCollection[i];
		layerCollection[position] = newLayer;
		next++;
	}

	//	5.
	//	Troca duas layers
	void swapPosition(int initialP, int finalP){
		if(initialP>SIZE || finalP>SIZE)
			throw new IllegalArgumentException("Alguma das posicoes escolhida e superior a capacidade do Poster");
		if(initialP<0 || finalP<0)
			throw new IllegalArgumentException("As posicoes escolhidas tem de ser positivas");
		if(layerCollection[initialP]==null || layerCollection[finalP]==null)
			throw new NullPointerException("Alguma das posicoes que escolheu esta vazia");
		Layer aux = layerCollection[initialP];
		layerCollection[initialP] = layerCollection[finalP];
		layerCollection[finalP] = aux;
	}

	//	6.
	//	Obter imagem final do poster
	ColorImage getPoster(){
		ColorImage finalImage = layerCollection[0].getLayer();
		for(int x=1; x<layerCollection.length; x++)
			if(layerCollection[x]!=null)
				Manipulation.copy(layerCollection[x].getLayer(), finalImage,0,0);
		return finalImage;
	}
}