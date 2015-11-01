package es.stacks.graph.core.impl;

import java.util.LinkedHashSet;

import es.stacks.graph.core.IGraphVertex;


/**
 * The Class GraphVertex.
 *
 * @author manuelmerida
 * @param <T> the generic type
 */
public class GraphVertex<T> implements IGraphVertex<T> {

	/** The data. */
	private T data;
	
	/** The visited. */
	private boolean visited;

	/** The vertices. */
	private LinkedHashSet<IGraphVertex<T>> vertices = new LinkedHashSet<IGraphVertex<T>>();

	/**
	 * Instantiates a new graph vertex.
	 *
	 * @param data the data
	 */
	public GraphVertex(T data) {
		super();
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#setData(java.lang.Object)
	 */
	@Override
	public void setData(T data) {
		// TODO Auto-generated method st
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#getData()
	 */
	@Override
	public T getData() {
		// TODO Auto-generated method stub
		return data;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#addNeighbors(es.stacks.graph.core.IGraphVertex)
	 */
	@Override
	public void addNeighbors(IGraphVertex<T> vert) {
		// TODO Auto-generated method stub
		vertices.add(vert);
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#getNeighbors()
	 */
	@Override
	public LinkedHashSet<IGraphVertex<T>> getNeighbors() {
		// TODO Auto-generated method stub
		return vertices;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#isVisited()
	 */
	@Override
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return visited;
	}

	/* (non-Javadoc)
	 * @see es.stacks.graph.core.IGraphVertex#setVisited(boolean)
	 */
	@Override
	public void setVisited(boolean visited) {
		// TODO Auto-generated method stub
		this.visited = visited;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return data + "";
	}

}
