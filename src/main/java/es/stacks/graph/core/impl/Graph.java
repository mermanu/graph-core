package es.stacks.graph.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import es.stacks.graph.core.IGraph;
import es.stacks.graph.core.IGraphVertex;

/**
 * The Class Graph.
 *
 * @author manuelmerida
 * @param <T> the generic type
 */
public class Graph<T> implements IGraph<T> {

	/** The vert set. */
	private Map<String, IGraphVertex<T>> graphMap = new LinkedHashMap<String, IGraphVertex<T>>();
	
	/** The map. */
	private Map<String, LinkedHashSet<IGraphVertex<T>>> map = new HashMap<String, LinkedHashSet<IGraphVertex<T>>>();
	
	/** The routes. */
	private Map<String, Map<String, LinkedList<IGraphVertex<T>>>> routes = new HashMap<String, Map<String, LinkedList<IGraphVertex<T>>>>();

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraph#addVertex(es.stacks.graph.core.IGraphVertex)
	 */
	@Override
	public void addVertex(IGraphVertex<T> hashVert) {
		graphMap.put(hashVert.toString(), hashVert);
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraph#dfs(es.stacks.graph.core.IGraphVertex)
	 */
	@Override
	/**
	 * The helper method allows you to create a new Array list to see if the
	 * vertex was visited or not
	 **/
	public List<IGraphVertex<T>> dfs(IGraphVertex<T> v) {
		List<IGraphVertex<T>> dfsVert = new ArrayList<IGraphVertex<T>>();

		// if the vertex is visited
		v.setVisited(true);

		// then do a depth first search
		dfsVisit(v, dfsVert);
		return dfsVert;
	}

	/**
	 *  The method that does all the work *.
	 *
	 * @param v the v
	 * @param list the list
	 */
	private void dfsVisit(IGraphVertex<T> v, List<IGraphVertex<T>> list) {
		// add the vertex to the list
		list.add(v);

		// create a for loop to check all the neighbors
		for (IGraphVertex<T> vertex : v.getNeighbors()) {
			// if the neighbors doen't get visited
			if (vertex.isVisited() == false) {
				// visit the vertex neighbors
				vertex.setVisited(true);
				// do a depth first search visit through the list
				dfsVisit(vertex, list);
			}
		}
	}

	/**
	 * Adjacent nodes.
	 *
	 * @param last the last
	 * @return the linked list
	 */
	public LinkedList<IGraphVertex<T>> adjacentNodes(String last) {
		LinkedHashSet<IGraphVertex<T>> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<IGraphVertex<T>>();
		}
		return new LinkedList<IGraphVertex<T>>(adjacent);
	}

	/**
	 * Depth first search.
	 *
	 * @param visited the visited
	 * @param destiny the destiny
	 */
	private void depthFirstSearch(LinkedList<IGraphVertex<T>> visited, String destiny) {
		LinkedList<IGraphVertex<T>> nodes = adjacentNodes(visited.getLast().toString());
		// examine adjacent nodes
		for (IGraphVertex<T> node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.toString().equals(destiny)) {
				visited.add(node);
				addWalked(visited, destiny);
				visited.removeLast();
				break;
			}
		}
		for (IGraphVertex<T> node : nodes) {
			if (visited.contains(node) || node.toString().equals(destiny)) {
				continue;
			}
			visited.addLast(node);
			depthFirstSearch(visited, destiny);
			visited.removeLast();
		}
	}

	/**
	 * Adds the walked.
	 *
	 * @param visited            the visited
	 * @param destiny the destiny
	 */
	private void addWalked(LinkedList<IGraphVertex<T>> visited, String destiny) {
		if (routes.get(visited.getFirst().toString() + destiny) != null) {
			routes.get(visited.getFirst().toString() + destiny).put(getRefName(visited),
					new LinkedList<IGraphVertex<T>>(visited));
		} else {
			Map<String, LinkedList<IGraphVertex<T>>> lList = new HashMap<String, LinkedList<IGraphVertex<T>>>();
			lList.put(getRefName(visited), new LinkedList<IGraphVertex<T>>(visited));
			routes.put(visited.getFirst().toString() + destiny, lList);
		}

	}

	/**
	 * Gets the ref name.
	 *
	 * @param visited the visited
	 * @return the ref name
	 */
	private String getRefName(LinkedList<IGraphVertex<T>> visited) {
		StringBuilder builder = new StringBuilder();
		for (IGraphVertex<T> vis : visited) {
			builder.append(vis.toString());
		}
		return builder.toString();
	}

	/**
	 *  To string method for depth first search *.
	 *
	 * @param v the v
	 * @return the string
	 */
	@Override
	public String toDFSString(IGraphVertex<T> v) {
		// create a list
		List<IGraphVertex<T>> list = dfs(v);

		// add a two string method to the list
		return list.toString();
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraph#addConnection(es.stacks.graph.core.IGraphVertex, es.stacks.graph.core.IGraphVertex)
	 */
	@Override
	public void addConnection(IGraphVertex<T> vertA, IGraphVertex<T> vertB) {
		
		addVertexToMap(vertA, vertB);
		addAdjacentNode(vertA, vertB);
	}
	
	/**
	 * Adds the vertex to map.
	 *
	 * @param vertA the vert a
	 * @param vertB the vert b
	 */
	private void addVertexToMap(IGraphVertex<T> vertA, IGraphVertex<T> vertB){
		if (graphMap.get(vertA.toString()) == null) {
			graphMap.put(vertA.toString(), vertA);
		}
		if (graphMap.get(vertB.toString()) == null) {
			graphMap.put(vertB.toString(), vertB);
		}
		graphMap.get(vertA.toString()).addNeighbors(vertB);
	}
	
	/**
	 * Adds the adjacent node.
	 *
	 * @param vertA the vert a
	 * @param vertB the vert b
	 */
	private void addAdjacentNode(IGraphVertex<T> vertA, IGraphVertex<T> vertB){
		LinkedHashSet<IGraphVertex<T>> adjacent = map.get(vertA.toString());
		if (adjacent == null) {
			adjacent = new LinkedHashSet<IGraphVertex<T>>();
			map.put(vertA.toString(), adjacent);
		}
		adjacent.add(vertB);
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraph#getGraphMap()
	 */
	@Override
	public Map<String, IGraphVertex<T>> getGraphMap() {
		return graphMap;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraph#calculateRoutes(es.stacks.graph.core.IGraphVertex)
	 */
	public Map<String, Map<String, LinkedList<IGraphVertex<T>>>> calculateRoutes(IGraphVertex<T> v) {
		List<IGraphVertex<T>> list = dfs(v);
		System.out.println("Calculate routes:"+list.toString());
		int size = list.size() - 1;
		/*LinkedList<IGraphVertex<T>> visited = new LinkedList<IGraphVertex<T>>();
		visited.add(v);
		depthFirstSearch(visited, list.get(size).toString());*/
		
		while (size > 0) {
			LinkedList<IGraphVertex<T>> visited = new LinkedList<IGraphVertex<T>>();
			visited.add(v);
			depthFirstSearch(visited, list.get(size).toString());
			size--;
		}
		int base = 0;
		while(base<list.size()){
			LinkedList<IGraphVertex<T>> visited = new LinkedList<IGraphVertex<T>>();
			visited.add(list.get(base));
			depthFirstSearch(visited, list.get(list.size()-1).toString());
			base++;
		}
		System.out.println("routes "+ v.toString() +" to "+ list.get(list.size() - 1).toString() + ":");
		for(Entry<String, Map<String, LinkedList<IGraphVertex<T>>>> entry : routes.entrySet()){
			System.out.println(entry.getValue().toString());
		}
		System.out.println("---------------------");
		return routes;
	}

}
