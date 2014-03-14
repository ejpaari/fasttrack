package fasttrack;

public class Link
{
    final private int m_target;
    final private int m_weight;
    
    public Link(int target, int weight)
    {
        m_target = target;
        m_weight = weight;
    }
    
    public int GetWeight()
    {
        return m_weight;
    }
    
    public int GetTarget()
    {
        return m_target;
    }
}
