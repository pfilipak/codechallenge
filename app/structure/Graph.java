package structure;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {

	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private boolean hasCycle = false;

	public ArrayList<Vertice> encontrarMenorCaminhoDijkstra(Vertice v1, Vertice v2) {
		ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice verticeCaminho;
		Vertice atual;
		Vertice vizinho;
		Aresta ligacao;
		ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
		menorCaminho.add(v1);
		for (int i = 0; i < this.getVertices().size(); i++) {
			if (this.getVertices().get(i).getNome().equals(v1.getNome()))
				this.getVertices().get(i).setDistancia(0);
			else
				this.getVertices().get(i).setDistancia(9999);
			naoVisitados.add(this.getVertices().get(i));
		}
		Collections.sort(naoVisitados);

		while (!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			for (int i = 0; i < atual.getVizinhos().size(); i++) {
				vizinho = atual.getVizinhos().get(i);
				if (!vizinho.isVisitado()) {
					ligacao = this.acharAresta(atual, vizinho);
					if (vizinho.getDistancia() > (atual.getDistancia() + ligacao
							.getPeso())) {
						vizinho.setDistancia(atual.getDistancia()
								+ ligacao.getPeso());
						vizinho.setPai(atual);

						if (vizinho == v2) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();

							}
							Collections.sort(menorCaminho);

						}
					}
				}

			}
			atual.setVisitado(true);
			naoVisitados.remove(atual);

			Collections.sort(naoVisitados);

		}
		this.limparVerticesPai();
		return menorCaminho;
	}

	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	public Vertice acharVertice(String nome) {
		return this.vertices.get(this.posicaoVertice(nome));
	}

	public void addAresta(int peso, String origem, String destino) {
		int i, j, k;

		i = this.addVertice(origem);
		j = this.addVertice(destino);

		Aresta a = new Aresta(peso, this.vertices.get(i), this.vertices.get(j));

		temCiclo(a);
		this.arestas.add(a);
		k = this.arestas.size();

		this.vertices.get(i).addIncidentes(this.arestas.get(k - 1));
		this.vertices.get(j).addIncidentes(this.arestas.get(k - 1));
	}

	public void setVertices(ArrayList<Vertice> vertices) {
		this.clearLists();

		for (int i = 0; i < vertices.size(); i++) {

			if (this.posicaoVertice(vertices.get(i).getNome()) == this.vertices.size()) {
				for (int j = 0; j < vertices.get(i).getIncidentes().size(); j++) {

					if ((vertices.get(i).getNome().equals(vertices.get(i)
							.getIncidentes().get(j).getOrigem().getNome()))
							&& (this.posicaoVertice(vertices.get(i)
									.getIncidentes().get(j).getDestino()
									.getNome()) != this.vertices.size())) {

						this.addAresta(vertices.get(i).getIncidentes().get(j)
								.getPeso(), vertices.get(i).getIncidentes()
								.get(j).getOrigem().getNome(), vertices.get(i)
								.getIncidentes().get(j).getDestino().getNome());

					} else if ((vertices.get(i).getNome().equals(vertices
							.get(i).getIncidentes().get(j).getDestino()
							.getNome()))
							&& (this.posicaoVertice(vertices.get(i)
									.getIncidentes().get(j).getOrigem()
									.getNome()) != this.vertices.size())) {

						this.addAresta(vertices.get(i).getIncidentes().get(j)
								.getPeso(), vertices.get(i).getIncidentes()
								.get(j).getOrigem().getNome(), vertices.get(i)
								.getIncidentes().get(j).getDestino().getNome());

					}
				}
				this.addVertice(vertices.get(i).getNome());
			}
		}
	}

	private void clearLists() {
		this.arestas.clear();
		this.vertices.clear();
		this.setHasCycle(false);
	}

	private boolean isHasCycle() {
		return hasCycle;
	}

	private void setHasCycle(boolean hasCycle) {
		this.hasCycle = hasCycle;
	}

	private int addVertice(String nome) {
		int i = this.posicaoVertice(nome);

		if (i == this.vertices.size()) {
			this.vertices.add(new Vertice(nome));
			return (this.vertices.size() - 1);
		}

		return i;
	}

	private void limparVerticesPai() {
		for (int i = 0; i < this.getVertices().size(); i++)
			this.getVertices().get(i).setPai(null);
	}

	private void limparVerticeVisitado() {
		for (int i = 0; i < this.getVertices().size(); i++)
			this.getVertices().get(i).setVisitado(false);
	}

	private void limparArestaVisitada() {
		for (int i = 0; i < this.getArestas().size(); i++)
			this.getArestas().get(i).setVisitado(false);
	}

	private int posicaoVertice(String nome) {
		int i;

		for (i = 0; i < this.vertices.size(); i++)
			if (this.vertices.get(i).getNome().equals(nome))
				return i;

		return this.vertices.size();

	}

	private Aresta acharAresta(Vertice vet1, Vertice vet2) {
		for (int i = 0; i < this.arestas.size(); i++) {
			if (((this.arestas.get(i).getOrigem().getNome().equals(vet1
					.getNome())) && (this.arestas.get(i).getDestino().getNome()
					.equals(vet2.getNome())))
					|| ((this.arestas.get(i).getOrigem().getNome().equals(vet2
							.getNome())) && (this.arestas.get(i).getDestino()
							.getNome().equals(vet1.getNome())))) {
				return this.arestas.get(i);
			}
		}
		return null;
	}

	private ArrayList<Aresta> getArestas() {
		return arestas;
	}

	private boolean temCiclo(Aresta aresta) {

		Vertice anterior = aresta.getDestino();

		for (int j = 0; j < this.getArestas().size(); j++) {

			for (int i = 0; i < this.getArestas().size(); i++) {

				if ((aresta == this.getArestas().get(i))
						&& (this.getArestas().get(i).isVisitado() == false))
					this.getArestas().get(i).setVisitado(true);
				else if (aresta != this.getArestas().get(i)) {

					if (anterior.getNome().equals(
							this.getArestas().get(i).getOrigem().getNome())) {

						if (aresta
								.getOrigem()
								.getNome()
								.equals(this.getArestas().get(i).getDestino()
										.getNome())) {
							this.limparArestaVisitada();
							this.hasCycle = true;
							return true;
						} else {
							anterior = this.getArestas().get(i).getDestino();
							this.getArestas().get(i).setVisitado(true);
						}

					} else if (anterior.getNome().equals(
							this.getArestas().get(i).getDestino().getNome())) {

						if (aresta
								.getOrigem()
								.getNome()
								.equals(this.getArestas().get(i).getOrigem()
										.getNome())) {
							this.limparArestaVisitada();
							this.hasCycle = true;
							return true;
						} else {
							anterior = this.getArestas().get(i).getOrigem();
							this.getArestas().get(i).setVisitado(true);
						}
					}
				}
			}
		}
		this.limparArestaVisitada();
		this.hasCycle = false;
		return false;
	}

}
