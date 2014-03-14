package fasttrack;

import java.util.ArrayList;

public class Node
{    
    private boolean m_visited;
    final private ArrayList<Link> m_links;
    
    public Node()
    {
        m_visited = false;
        m_links = new ArrayList<>();
    }
    
    public void SetAsVisited()
    {
        m_visited = true;
    }
    
    public boolean IsVisited()
    {
        return m_visited;
    }
    
    public void AddLink(int target, int weight)
    {
        Link newLink = new Link(target, weight);
        m_links.add(newLink);
    }
    
    public ArrayList<Link> GetLinks()
    {
        return m_links;
    }
    
    public int GetLinksSize()
    {
        return m_links.size();
    }
    
    public int GetLinkTarget(int index)
    {
        return m_links.get(index).GetTarget();
    }
    
    public int GetLinkWeight(int index)
    {
        return m_links.get(index).GetWeight();
    }
}
