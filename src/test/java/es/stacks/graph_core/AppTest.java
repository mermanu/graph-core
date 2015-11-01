package es.stacks.graph_core;

import es.stacks.graph.core.IGraph;
import es.stacks.graph.core.IGraphVertex;
import es.stacks.graph.core.impl.Graph;
import es.stacks.graph.core.impl.GraphVertex;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	private IGraph<String> graph = new Graph<String>();
	
	private IGraphVertex<String> a = new GraphVertex<String>("A");
	
	IGraphVertex<String> b = new GraphVertex<String>("B");
	IGraphVertex<String> c = new GraphVertex<String>("C");
	IGraphVertex<String> d = new GraphVertex<String>("D");
	IGraphVertex<String> e = new GraphVertex<String>("E");
	IGraphVertex<String> f = new GraphVertex<String>("F");
	IGraphVertex<String> g = new GraphVertex<String>("G");

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}
	
	private void setDataToTest2() {
		
		b = new GraphVertex<String>("B");
		c = new GraphVertex<String>("C");
		d = new GraphVertex<String>("D");
		e = new GraphVertex<String>("E");
		f = new GraphVertex<String>("F");
		g = new GraphVertex<String>("G");
		
		graph = new Graph<String>();
		graph.addConnection(a, b);
		graph.addConnection(a, c);
		graph.addConnection(c, d);
		graph.addConnection(d, f);
		graph.addConnection(f, e);
		graph.addConnection(f, g);
		graph.addConnection(b, c);
		graph.addConnection(f, b);
		graph.addConnection(g, b);
		graph.addConnection(e, b);

	}

	public void testDFS() {
		// set the data for the vertex
		
		
		setDataToTest2();
		graph.calculateRoutes(a);
		setDataToTest2();
		graph.calculateRoutes(b);
		setDataToTest2();
		graph.calculateRoutes(c);
		setDataToTest2();
		graph.calculateRoutes(d);
		setDataToTest2();
		graph.calculateRoutes(e);
		setDataToTest2();
		graph.calculateRoutes(f);
		setDataToTest2();
		graph.calculateRoutes(g);
		setDataToTest2();
	}
}
