package structure;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

	@Test
	public void testMenorCaminho(){
		Graph inicial = new Graph();
		Graph resultado = new Graph();
		inicial.addAresta(1,"A","B");
		inicial.addAresta(1,"E","B");
		inicial.addAresta(1,"A","C");
		inicial.addAresta(1,"C","D");
		inicial.addAresta(1,"E","D");
		
		Vertice origin = inicial.acharVertice("A");
		Vertice destination = inicial.acharVertice("E");
		
		resultado.setVertices(inicial.encontrarMenorCaminhoDijkstra(origin, destination));
		
		String[] result = new String[3];
		ArrayList<Vertice> vertices = resultado.getVertices();
		for (int j = 0; j < vertices.size(); j++) {
			result[j] = vertices.get(j).getNome();
		}
		
		Assert.assertArrayEquals(new String[]{"A", "B", "E"}, result); 
	}
}
