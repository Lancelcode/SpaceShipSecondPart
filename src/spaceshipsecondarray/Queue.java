package spaceshipsecondarray;

public interface Queue<AnyType> {
    void add(AnyType var1);

    AnyType peek();

    AnyType remove();

    boolean empty();

    void makeEmpty();
}