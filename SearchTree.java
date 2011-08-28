
public interface SearchTree<E> {

    boolean add(E item);

    E find(E target);

    E delete(E target);
}
