package spaceshipsecondarray;

public class ArrayQueue<AnyType> implements Queue<AnyType> {
    private static final int DEFAULT_CAPACITY = 20;
    private AnyType[] theArray = (AnyType[]) new Object[20];
    private int currentSize;
    private int front;
    private int back;

    public ArrayQueue() {
        this.makeEmpty();
    }

    public boolean empty() {
        return this.currentSize == 0;
    }

    public void makeEmpty() {
        this.currentSize = 0;
        this.front = 0;
        this.back = -1;
    }

    public AnyType remove() {
        if (this.empty()) {
            throw new UnderflowException("Queue is empty cannot remove");
        } else {
            --this.currentSize;
            AnyType returnValue = this.theArray[this.front];
            this.front = this.increment(this.front);
            return returnValue;
        }
    }

    public AnyType peek() {
        if (this.empty()) {
            throw new UnderflowException("ArrayQueue getFront");
        } else {
            return this.theArray[this.front];
        }
    }

    public void add(AnyType x) {
        if (this.currentSize == this.theArray.length) {
            this.doubleQueue();
        }

        this.back = this.increment(this.back);
        this.theArray[this.back] = x;
        ++this.currentSize;
    }

    private int increment(int x) {
        ++x;
        if (x == this.theArray.length) {
            x = 0;
        }

        return x;
    }

    private void doubleQueue() {
        AnyType[] newArray = (AnyType[]) new Object[this.theArray.length * 2];

        for(int i = 0; i < this.currentSize; this.front = this.increment(this.front)) {
            newArray[i] = this.theArray[this.front];
            ++i;
        }

        this.theArray = newArray;
        this.front = 0;
        this.back = this.currentSize - 1;
    }

    // Method to return the size of the stack added
    public int size() {
        return currentSize;
    }

    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        int index = front;
        for (int i = 0; i < currentSize; i++) {
            sb.append(theArray[index]);
            if (i < currentSize - 1) {
                sb.append(", ");
            }
            index = increment(index);
        }

        sb.append("]");
        return sb.toString();
    }

}
