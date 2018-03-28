package test.shortpath.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shortpath.graph.BuildGraph;

/**
 * BuildGraph Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/28/2018</pre>
 */
public class BuildGraphTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: main(String[] agrs)
     */
    @Test
    public void testMain() throws Exception {
        BuildGraph.main(null);
    }


} 
