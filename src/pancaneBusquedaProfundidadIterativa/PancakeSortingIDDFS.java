package pancaneBusquedaProfundidadIterativa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class PancakeSortingIDDFS {
    public static void main(String[] args) {
        String input = "cgehdfba";
        pancakeSortIDDFS(input);
    }

    public static void pancakeSortIDDFS(String input) {
        char[] pancakes = input.toCharArray();
        int n = pancakes.length;
        int nodes=1;
        int maxDepth = 0;
        while(true) {
            Stack<Node> stack = new Stack<>();
            Node start = new Node(pancakes, new ArrayList<>(), 0);
            stack.push(start);
            Set<String> visited = new HashSet<>();
            visited.add(new String(start.state));
            while (!stack.isEmpty()) {
                Node current = stack.pop();
                nodes++;
                for (int i = n; i >= 2; i--) {
                    if(current.depth + 1 <= maxDepth) {
                        char[] childState = flip(current.state, i);
                        String childStateString = new String(childState);
                        if (!visited.contains(childStateString)) {
                            List<Integer> childFlips = new ArrayList<>(current.flipIndices);
                            childFlips.add(i);
                            Node child = new Node(childState, childFlips, current.depth+1);
                            stack.push(child);
                            visited.add(childStateString);
                        }
                    }
                }
                if (isSorted(current.state)) {
                    System.out.println("Pasos: " + current.flips);
                    System.out.println("Volteos: " + current.flipIndices);
                    System.out.println("Nodos visitados: " + nodes);
                    return;
                }
            }
            maxDepth++;
        }
    }

    private static char[] flip(char[] pancakes, int k) {
        char[] result = new char[pancakes.length];
        int cuont =1;
        for (int i = 0; i <= pancakes.length-k; i++) {
            result[i] = pancakes[i];
        }
        for (int i = pancakes.length-k; i < pancakes.length; i++) {
            result[i] = pancakes[pancakes.length - cuont];
            cuont++;
        }
        return result;
    }

    private static boolean isSorted(char[] pancakes) {
        for (int i = 0; i < pancakes.length - 1; i++) {
            if (pancakes[i] > pancakes[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static class Node {
        public char[] state;
        public List<Integer> flipIndices;
        public int flips;
        public int depth;

        public Node(char[] state, List<Integer> flipIndices, int depth) {
            this.state = state;
            this.flipIndices = flipIndices;
            this.flips = flipIndices.size();
            this.depth = depth;
        }
    }
}