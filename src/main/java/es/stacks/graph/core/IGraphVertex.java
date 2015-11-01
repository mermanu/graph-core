package es.stacks.graph.core;

import java.util.LinkedHashSet;

/**
 * The Interface IGraphVertex.
 *
 * @author manuelmerida
 * @param <T> the generic type
 */
public interface IGraphVertex<T> {

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data);

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData();

	/**
	 * Adds the neighbors.
	 *
	 * @param vertex the vertex
	 */
	public void addNeighbors(IGraphVertex<T> vertex);

	/**
	 * Gets the neighbors.
	 *
	 * @return the neighbors
	 */
	public LinkedHashSet<IGraphVertex<T>> getNeighbors();

	/**
	 * Checks if is visited.
	 *
	 * @return true, if is visited
	 */
	public boolean isVisited();

	/**
	 * Sets the visited.
	 *
	 * @param visited the new visited
	 */
	public void setVisited(boolean visited);
}
