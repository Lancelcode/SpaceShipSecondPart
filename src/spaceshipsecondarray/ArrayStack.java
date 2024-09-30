package spaceshipsecondarray;


public class ArrayStack<AnyType> implements Stack<AnyType> {
    private static final int DEFAULT_CAPACITY = 18;
    private AnyType[] theArray ;
    private int topOfStack = -1;


    public ArrayStack() {
        theArray = (AnyType[]) new Object[18];
    }

    public boolean empty() {
        return this.topOfStack == -1;
    }

    public void makeEmpty() {
        this.topOfStack = -1;
    }

    public AnyType peek() {
        if (this.empty()) {
            throw new UnderflowException("ArrayStack top");
        } else {
            return this.theArray[this.topOfStack];
        }
    }

    public void topAndPop() {
        if (this.empty()) {
            throw new UnderflowException("ArrayStack pop");
        } else {
            --this.topOfStack;
        }
    }

    public AnyType pop() {
        if (this.empty()) {
            throw new UnderflowException("ArrayStack topAndPop");
        } else {
            return this.theArray[this.topOfStack--];
        }
    }

    public void push(AnyType x) {
        if (this.topOfStack + 1 == this.theArray.length) {
            this.doubleArray();
        }

        this.theArray[++this.topOfStack] = x;
    }

    private void doubleArray() {
        AnyType[] newArray = (AnyType[]) new Object[this.theArray.length * 2];

        for (int i = 0; i < this.theArray.length; ++i) {
            newArray[i] = this.theArray[i];
        }

        this.theArray = newArray;
    }

    // Method to return the size of the stack added
    public int size() {
        return topOfStack + 1;
    }

    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i <= topOfStack; i++) {
            sb.append(theArray[i]);
            if (i < topOfStack) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

}