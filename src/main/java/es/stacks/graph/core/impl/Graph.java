package es.stacks.graph.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import es.stacks.graph.core.IGraph;
import es.stacks.graph.core.IGraphVertex;
import java.util.Queue;


public class Graph<T> implements IGraph<T> {

	private Map<String, IGraphVertex<T>> vertSet = new LinkedHashMap<String, IGraphVertex<T>>();
	private Map<String, LinkedHashSet<IGraphVertex<T>>> map = new HashMap<String, LinkedHashSet<IGraphVertex<T>>>();
	private Map<String, Map<String, LinkedList<IGraphVertex<T>>>> routes = new HashMap<String, Map<String, LinkedList<IGraphVertex<T>>>>();

	@Override
	public void addVertex(IGraphVertex<T> hashVert) {
		vertSet.put(hashVert.toString(), hashVert);
	}

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

	/** The method that does all the work **/
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

	public LinkedList<IGraphVertex<T>> adjacentNodes(String last) {
		LinkedHashSet<IGraphVertex<T>> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<IGraphVertex<T>>();
		}
		return new LinkedList<IGraphVertex<T>>(adjacent);
	}

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
	 * @param visited
	 *            the visited
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

	private String getRefName(LinkedList<IGraphVertex<T>> visited) {
		StringBuilder builder = new StringBuilder();
		for (IGraphVertex<T> vis : visited) {
			builder.append(vis.toString());
		}
		return builder.toString();
	}

	/** To string method for depth first search **/
	@Override
	public String toDFSString(IGraphVertex<T> v) {
		// create a list
		List<IGraphVertex<T>> list = dfs(v);

		// add a two string method to the list
		return list.toString();
	}

	@Override
	// Breadth First Search
	public List<IGraphVertex<T>> bfs(IGraphVertex<T> v) {
		// create an array list
		List<IGraphVertex<T>> bfsVert = new ArrayList<IGraphVertex<T>>();
		// create a queue
		Queue<IGraphVertex<T>> qVert = new LinkedList<IGraphVertex<T>>();

		// the queue will enqueue the vertex
		qVert.add(v);
		// while the queue is not empty
		while (!qVert.isEmpty()) {
			// dequeue the queue
			v = qVert.poll();
			// add a vertex to the list
			bfsVert.add(v);
			// for loop to check the neighbors
			for (IGraphVertex<T> vertex : v.getNeighbors()) {
				// if the vertex is not visited
				if (vertex.isVisited() == false) {
					vertex.setVisited(true);
					// enqueue the vertex
					qVert.add(vertex);
				}
			}
		}
		return bfsVert;
	}

	@Override
	public String toBFSString(IGraphVertex<T> v) {
		// create a list which does the breadth first search
		List<IGraphVertex<T>> list = bfs(v);
		// make a string to the list
		return list.toString();
	}

	@Override
	public void addConnection(IGraphVertex<T> vertA, IGraphVertex<T> vertB) {
		if (vertSet.get(vertA.toString()) == null) {
			vertSet.put(vertA.toString(), vertA);
		}
		if (vertSet.get(vertB.toString()) == null) {
			vertSet.put(vertB.toString(), vertB);
		}
		vertSet.get(vertA.toString()).addNeighbors(vertB);

		LinkedHashSet<IGraphVertex<T>> adjacent = map.get(vertA.toString());
		if (adjacent == null) {
			adjacent = new LinkedHashSet<IGraphVertex<T>>();
			map.put(vertA.toString(), adjacent);
		}
		adjacent.add(vertB);
	}

	@Override
	public Map<String, IGraphVertex<T>> getGraphMap() {
		return vertSet;
	}

	public Map<String, Map<String, LinkedList<IGraphVertex<T>>>> calculateRoutes(IGraphVertex<T> v) {
		List<IGraphVertex<T>> list = dfs(v);
		int size = list.size() - 1;
		while (size > 0) {
			LinkedList<IGraphVertex<T>> visited = new LinkedList<IGraphVertex<T>>();
			visited.add(v);
			depthFirstSearch(visited, list.get(size).toString());
			size--;
		}
		System.out.println("routes "+ v.toString() +" to "+ list.get(list.size() - 1).toString() + ":"+ routes.toString());
		return routes;
	}

}
