package es.stacks.graph.core;

import java.util.LinkedHashSet;

public interface IGraphVertex<T> {

	public void setData(T data);

	public T getData();

	public void addNeighbors(IGraphVertex<T> vert);

	public LinkedHashSet<IGraphVertex<T>> getNeighbors();

	public boolean isVisited();

	public void setVisited(boolean visited);
}
