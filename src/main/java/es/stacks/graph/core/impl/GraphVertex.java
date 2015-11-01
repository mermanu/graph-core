package es.stacks.graph.core.impl;

import java.util.LinkedHashSet;

import es.stacks.graph.core.IGraphVertex;


public class GraphVertex<T> implements IGraphVertex<T> {

	private T data;
	private boolean visited;

	private LinkedHashSet<IGraphVertex<T>> vertices = new LinkedHashSet<IGraphVertex<T>>();

	public GraphVertex(T data) {
		super();
		this.data = data;
	}

	@Override
	public void setData(T data) {
		// TODO Auto-generated method st
		this.data = data;
	}

	@Override
	public T getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void addNeighbors(IGraphVertex<T> vert) {
		// TODO Auto-generated method stub
		vertices.add(vert);
	}

	@Override
	public LinkedHashSet<IGraphVertex<T>> getNeighbors() {
		// TODO Auto-generated method stub
		return vertices;
	}

	@Override
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return visited;
	}

	@Override
	public void setVisited(boolean visited) {
		// TODO Auto-generated method stub
		this.visited = visited;
	}

	@Override
	public String toString() {
		return data + "";
	}
	
	

}
