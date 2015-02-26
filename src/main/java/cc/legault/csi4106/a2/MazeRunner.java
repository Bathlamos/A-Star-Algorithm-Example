package cc.legault.csi4106.a2;

import com.google.common.collect.Lists;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeRunner {

    private char[][] map;
    private FibonacciHeap<Node> heap;
    private int numExpanded;
    private Map<String, FibonacciHeapNode<Node>> visited;

    public List<Point> execute(Maze maze, int i, int j, int homeI, int homeJ){
        map = maze.getMap();
        heap = new FibonacciHeap<Node>();
        visited = new HashMap<String, FibonacciHeapNode<Node>>();
        numExpanded = 0;

        Node node = new Node(i, j, cost(0, i, j, homeI, homeJ));
        FibonacciHeapNode<Node> fNode = new FibonacciHeapNode<Node>(node);
        heap.insert(fNode, node.cost);
        visited.put(i + " " + j, fNode);

        int[][] modifiers = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        do{
            fNode = heap.removeMin();
            node = fNode.getData();
            numExpanded++;

            if(node.i == homeI && node.j == homeJ){
                List<Point> points = new ArrayList<Point>();
                do{
                    points.add(new Point(node.i, node.j));
                    node = node.ancestor;
                }while(node.ancestor != null);

                Collections.reverse(points);
                return points;
            }

            for(int[] modifier: modifiers){
                if(valid(node.i + modifier[0], node.j + modifier[1])){
                    int cost = cost(node.cost, i, j, homeI, homeJ);
                    String id = node.i + modifier[0] + " " + node.j + modifier[1];
                    if(visited.containsKey(id)){
                        //Check to see if we have a better claim
                        if(visited.get(id).getData().cost > cost){
                            heap.decreaseKey(visited.get(id), cost);
                            visited.get(id).getData().ancestor = node;
                        }
                    }else {
                        Node newNode = new Node(node.i + modifier[0], node.j + modifier[1], cost);
                        newNode.ancestor = node;
                        fNode = new FibonacciHeapNode<Node>(newNode);
                        heap.insert(fNode, newNode.cost);
                        visited.put(id, fNode);
                    }
                }
            }

        }while(!heap.isEmpty());

        throw new RuntimeException("Not found");
    }

    public int getNumberOfNodesVisited(){
        return numExpanded;
    }

    private int cost(int currentCost, int i, int j, int homeI, int homeJ){
        int numObstacles = 0;
        for(int s = Math.min(i, homeI); s < Math.max(i, homeI); s++)
            if(!valid(s, j))
                numObstacles++;
        for(int s = Math.min(j, homeJ); s < Math.max(j, homeJ); s++)
            if(!valid(i, s))
                numObstacles++;
        return Math.abs(i - homeI) + Math.abs(j - homeJ) + numObstacles + currentCost;
    }

    private boolean valid(int i, int j){
        return i >= 0 &&
                i < map.length &&
                j >= 0 &&
                j < map[i].length &&
                map[i][j] != Maze.OBSTACLE;
    }

    private final class Node{
        private Node ancestor;
        private int i, j, cost;

        Node(int i, int j, int cost){
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }

}
