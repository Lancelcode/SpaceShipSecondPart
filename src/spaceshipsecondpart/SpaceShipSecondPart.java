package spaceshipsecondpart;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SpaceShipSecondPart {

    // Custom ListNode class
    public static class ListNode<T> {
        T data;
        ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Custom ListQueue class
    public static class ListQueue<T> implements Queue<T> {
        private ListNode<T> front;
        private ListNode<T> rear;
        private int size;

        public ListQueue() {
            this.front = null;
            this.rear = null;
            this.size = 0;
        }

        @Override
        public void enqueue(T item) {
            ListNode<T> newNode = new ListNode<>(item);
            if (rear != null) {
                rear.next = newNode;
            }
            rear = newNode;
            if (front == null) {
                front = newNode;
            }
            size++;
        }

        @Override
        public T dequeue() throws UnderFlowException {
            if (isEmpty()) {
                throw new UnderFlowException("Queue is empty");
            }
            T data = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            size--;
            return data;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode<T> current = front;
            while (current != null) {
                sb.append(current.data).append(" ");
                current = current.next;
            }
            return sb.toString();
        }
    }

    // Custom ListStack class
    public static class ListStack<T> implements Stack<T> {
        private ListNode<T> top;
        private int size;

        public ListStack() {
            this.top = null;
            this.size = 0;
        }

        @Override
        public void push(T item) {
            ListNode<T> newNode = new ListNode<>(item);
            newNode.next = top;
            top = newNode;
            size++;
        }

        @Override
        public T pop() throws UnderFlowException {
            if (isEmpty()) {
                throw new UnderFlowException("Stack is empty");
            }
            T data = top.data;
            top = top.next;
            size--;
            return data;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }
    }

    // Custom ArrayQueue class
    public static class ArrayQueue<T> implements Queue<T> {
        private T[] data;
        private int front;
        private int rear;
        private int capacity;
        private int size;

        @SuppressWarnings("unchecked")
        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            data = (T[]) new Object[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        @Override
        public void enqueue(T item) {
            if (size == capacity) {
                throw new RuntimeException("Queue is full");
            }
            rear = (rear + 1) % capacity;
            data[rear] = item;
            size++;
        }

        @Override
        public T dequeue() throws UnderFlowException {
            if (isEmpty()) {
                throw new UnderFlowException("Queue is empty");
            }
            T item = data[front];
            front = (front + 1) % capacity;
            size--;
            return item;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int size() {
            return size;
        }
    }

    // Custom ArrayStack class
    public static class ArrayStack<T> implements Stack<T> {
        private T[] data;
        private int top;
        private int capacity;

        @SuppressWarnings("unchecked")
        public ArrayStack(int capacity) {
            this.capacity = capacity;
            data = (T[]) new Object[capacity];
            top = -1;
        }

        @Override
        public void push(T item) {
            if (top == capacity - 1) {
                throw new RuntimeException("Stack is full");
            }
            data[++top] = item;
        }

        @Override
        public T pop() throws UnderFlowException {
            if (isEmpty()) {
                throw new UnderFlowException("Stack is empty");
            }
            return data[top--];
        }

        @Override
        public boolean isEmpty() {
            return top == -1;
        }

        @Override
        public int size() {
            return top + 1;
        }
    }

    // Queue interface
    public interface Queue<T> {
        void enqueue(T item);
        T dequeue() throws UnderFlowException;
        boolean isEmpty();
        int size();
    }

    // Stack interface
    public interface Stack<T> {
        void push(T item);
        T pop() throws UnderFlowException;
        boolean isEmpty();
        int size();
    }

    // Custom UnderFlowException class
    public static class UnderFlowException extends Exception {
        public UnderFlowException(String message) {
            super(message);
        }
    }

    // Method to load data from the mission file
    public static String[] loadData(String filename) throws IOException {
        String[] pods = new String[18]; // Max size
        int index = 0;
        File myFile = new File(filename);

        try (FileReader fr = new FileReader(myFile); BufferedReader br = new BufferedReader(fr)) {
            String data;

            // Read the file line by line
            while ((data = br.readLine()) != null && index < pods.length) {
                StringTokenizer st = new StringTokenizer(data, ",");
                while (st.hasMoreTokens() && index < pods.length) {
                    pods[index++] = st.nextToken().trim();
                }
            }
        }
        // Return only the filled portion of the array
        String[] result = new String[index];
        System.arraycopy(pods, 0, result, 0, index);
        return result;
    }

    public static void main(String[] args) {
        String[] missions = {"src/mission1.txt", "src/mission2.txt", "src/mission3.txt", "src/mission4.txt", "src/mission5.txt", "src/mission6.txt", "src/mission7.txt"};
        final int MAX_PODS = 18; // Maximum allowed pods for a mission

        for (String mission : missions) {
            System.out.println("\nLaunching Mission: " + mission);
            String[] sd;

            // Try loading the data
            try {
                sd = loadData(mission);
            } catch (IOException e) {
                System.out.println("Aborting mission: " + mission + " due to file reading error. Cause: " + e.getMessage());
                continue; // Skip to the next mission if there's a file error
            }

            // Check if there are too many pods in the mission
            if (sd.length > MAX_PODS) {
                System.out.println("Aborting mission: " + mission + " due to too many pods (" + sd.length + " pods). Maximum allowed is " + MAX_PODS + ".");
                continue; // Skip the rest of the mission processing and move to the next mission
            }

            // Set to track duplicate pod IDs
            HashSet<String> uniquePods = new HashSet<>();

            // Validate pods for duplicates and correct ID format (3 characters)
            boolean abortMission = false;
            for (String pod : sd) {
                if (!uniquePods.add(pod)) {
                    System.out.println("Aborting mission: " + mission + " due to duplicate pod ID: " + pod);
                    abortMission = true;
                    break;
                }

                // Check if pod ID has exactly 3 characters
                if (pod.length() != 3) {
                    System.out.println("Aborting mission: " + mission + " due to invalid pod ID format: " + pod + ". Pod ID must be exactly 3 characters.");
                    abortMission = true;
                    break;
                }
            }

            if (abortMission) {
                continue; // Skip the rest of the mission if there are issues with pod validation
            }

            // Custom Stack and Queue implementations
            Stack<String> container1 = new ListStack<>();  // Custom Stack using linked list
            Stack<String> container2 = new ListStack<>();  // Custom Stack using linked list
            Queue<String> corridor1 = new ListQueue<>();   // Custom Queue using linked list

            System.out.println("\n------------------------------\n");
            System.out.println("\nInitial container1 amount of pods: " + container1.size());
            System.out.println("Initial container2 amount of pods: " + container2.size());

            System.out.println("\n------------------------------\n");
            System.out.println("\nShuttle has reached the space station, the container 1 have " + container1.size() + " amount of pods, container 2 has " + container2.size() + " amount of pods.");
            System.out.println("\nLoading pods into containers!");

            // Load the first 9 elements into container1
            for (int i = 0; i < Math.min(9, sd.length); i++) {
                container1.push(sd[i]);
            }

            // Load the next 9 elements into container2
            for (int i = 9; i < Math.min(18, sd.length); i++) {
                container2.push(sd[i]);
            }

            System.out.println("\ncontainer1 size: " + container1.size());
            System.out.println("container2 size: " + container2.size());

            System.out.println("\n===================\n");

            // Fill the queue with the pods from container1
            System.out.println("\n----------------------------------------------------------");
            System.out.println("\nTransporting pods to the loading bay through the corridor.");
            System.out.println("\n----------------------------------------------------------\n");

            // Transfer elements from container1 to the queue (corridor1)
            while (!container1.isEmpty()) {
                try {
                    corridor1.enqueue(container1.pop());
                } catch (UnderFlowException e) {
                    System.out.println("Failed to pop from container1: " + e.getMessage());
                }
            }

            System.out.println("\nCorridor contents: " + corridor1);

            // Transfer elements from container2 to the queue (corridor1)
            while (!container2.isEmpty()) {
                try {
                    corridor1.enqueue(container2.pop());
                } catch (UnderFlowException e) {
                    System.out.println("Failed to pop from container2: " + e.getMessage());
                }
            }

            System.out.println("\nFinal container1 amount of pods: " + container1.size());
            System.out.println("Final container2 amount of pods: " + container2.size());
            System.out.println("\nPods in corridor1: " + corridor1.toString());
            System.out.println("\nMission: " + mission + " completed successfully.\n");
        }
    }
}
