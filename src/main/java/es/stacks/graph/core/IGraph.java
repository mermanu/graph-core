package es.stacks.graph.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Interface IGraph.
 *
 * @author manuelmerida
 * @param <T> the generic type
 */
public interface IGraph<T> {
	
	/**
	 * Adds the connection.
	 *
	 * @param vertexA the vertex a
	 * @param vertexB the vertex b
	 */
	public void addConnection(IGraphVertex<T> vertexA, IGraphVertex<T> vertexB);
	
	/**
	 * Adds the vertex.
	 *
	 * @param vertex the vertex
	 */
	public void addVertex(IGraphVertex<T> vertex);
	
	/**
	 * Dfs.
	 *
	 * @param vertex the vertex
	 * @return the list
	 */
	public List<IGraphVertex<T>> dfs(IGraphVertex<T> vertex);
	
	/**
	 * To dfs string.
	 *
	 * @param vertex the vertex
	 * @return the string
	 */
	public String toDFSString(IGraphVertex<T> vertex);
	
	/**
	 * Gets the graph map.
	 *
	 * @return the graph map
	 */
	public Map<String, IGraphVertex<T>> getGraphMap();
	
	/**
	 * Calculate routes.
	 *
	 * @param vertex the vertex
	 * @return the map
	 */
	public Map<String, Map<String, LinkedList<IGraphVertex<T>>>> calculateRoutes(IGraphVertex<T> vertex);
	
}
