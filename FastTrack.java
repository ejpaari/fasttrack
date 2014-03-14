package fasttrack;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FastTrack
{
    public static void main(String[] args)
    {
        Scanner fileScanner;
        
        try
        {
            fileScanner = new Scanner(new File("tree.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.printf(e.getMessage());
            return;
        }
        
        // Read off first line.
        fileScanner.nextLine();
        
        int node = 0;
        int row = 1;
        int nodeCount = 0;
        int weight;
        Graph myGraph = new Graph();
        myGraph.SetGraphSize(5051); // (sum j+1, j=1 to 100) + 1

        while (fileScanner.hasNext())
        {
            ++node;
            ++nodeCount;
            
            // Last node of the row.
            if (nodeCount == row)
            {
                weight = fileScanner.nextInt();
                myGraph.AddLink(node - row, node, weight);
                ++row;
                nodeCount = 0;
            }
            // First node of the row.
            else if (nodeCount == 1)
            {
                weight = fileScanner.nextInt();
                myGraph.AddLink(node - row + 1, node, weight);
            }
            // Somewhere in the middle.
            else
            {
                weight = fileScanner.nextInt();
                myGraph.AddLink(node - row, node, weight);
                myGraph.AddLink(node - row + 1, node, weight);
            }
        }
        
        System.out.println(myGraph.FindLongestPath(0));
    }
}