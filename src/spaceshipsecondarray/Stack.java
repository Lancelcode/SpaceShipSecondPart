package spaceshipsecondarray;

public interface Stack<AnyType> {
    void push(AnyType var1);

    AnyType pop();

    AnyType peek();

    void topAndPop();

    boolean empty();

    void makeEmpty();

    int size();
}
