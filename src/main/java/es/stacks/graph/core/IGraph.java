package es.stacks.graph.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public interface IGraph<T> {
	
	public void addConnection(IGraphVertex<T> vertA, IGraphVertex<T> vertB);
	public void addVertex(IGraphVertex<T> hashVert);
	public List<IGraphVertex<T>> dfs(IGraphVertex<T> v);
	public List<IGraphVertex<T>> bfs( IGraphVertex<T> v);
	public String toBFSString(IGraphVertex<T> v);
	public String toDFSString(IGraphVertex<T> v);
	public Map<String, IGraphVertex<T>> getGraphMap();
	public Map<String, Map<String, LinkedList<IGraphVertex<T>>>> calculateRoutes(IGraphVertex<T> v);
	
}
