package fasttrack;

import java.util.ArrayList;
import java.util.Collections;

public class Graph
{   
    final private ArrayList<Node> nodes;
    final private ArrayList<Integer> topological;
    private ArrayList<Integer> distances;
    
    public Graph()
    {        
        nodes = new ArrayList<>();
        topological = new ArrayList<>();
        distances = new ArrayList<>();
    }
    
    public void SetGraphSize(int size)
    {
        nodes.clear();
        
        for (int i = 0; i < size; ++i)
        {
            nodes.add(new Node());
        }
    }
    
    public void AddLink(int from, int to, int weight)
    {
        nodes.get(from).AddLink(to, weight);
    }
    
    public int FindLongestPath(int startNode)
    {        
        topological.clear();
        
        SortTopological(startNode);
        
        distances = new ArrayList<>(Collections.nCopies(nodes.size(), Integer.MIN_VALUE));
        distances.set(startNode, 0);
        
        // Go through the graph in topological order.
        for (int i = 0; i < topological.size(); ++i)
        {
            // Skip dead ends.
            if (distances.get(i) != Integer.MIN_VALUE)
            {
                // Go through all the links of each node.
                for (int j = 0; j < nodes.get(i).GetLinksSize(); ++j)
                {
                    // If the new route is longer, save it. This is used mainly if there are negative lengths.
                    int newNodeDistance = distances.get(i) + nodes.get(i).GetLinkWeight(j);
                    
                    if (distances.get(nodes.get(i).GetLinkTarget(j)) < newNodeDistance)
                    {
                        distances.set(nodes.get(i).GetLinkTarget(j), newNodeDistance);
                    }
                }
            }
        }
        
        // Find the longest path.
        int max = 0;
        
        for (int i = 0; i < distances.size(); ++i)
        {
            if (distances.get(i) > max)
            {
                max = distances.get(i);
            }
        }
        
        return max;
    }
    
    private void SortTopological(int node)
    {
        for (int i = 0; i < nodes.size(); ++i)
        {
            if (!nodes.get(i).IsVisited())
            {
                SearchDeepest(node);
            }
        }
    }
    
    private void SearchDeepest(int node)
    {
        nodes.get(node).SetAsVisited();
        
        for (int i = 0; i < nodes.get(node).GetLinksSize(); ++i)
        {
            if (!nodes.get(i).IsVisited())
            {
                SearchDeepest(i);
            }
        }
                    
        topological.add(node);
    }
}