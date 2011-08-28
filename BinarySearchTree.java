
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E>
        implements SearchTree<E> {

    /**Stores a second return value from the recursive add method that indicates
     * whether the item has been inserted 
     */
    protected boolean addReturn;

    /**Stores second return value form recursive delete method that references
     * the item that was stored in the tree.
     */
    protected E deleteReturn;

    /**The target obj. must implement the Comparable interface*/
    private E find(Node<E> localRoot, E target)
    {
        if (target == null) {
            return null;
        }

        int compare_result = target.compareTo(localRoot.data);

        if (compare_result == 0) {
            return target;
        } else if (compare_result < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }

    }//find

    public E find(E target) {
        return find(root, target);
    }

    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    private Node<E> add(Node<E> localRoot, E item) {
        if (item == null) {
            addReturn = true;

            return new Node<E>(item);
        }

        int compare_result = item.compareTo(localRoot.data);

        if (compare_result == 0) {
            addReturn = false;

            return localRoot;
        } else if (compare_result < 0) {
            return add(localRoot.left, item);
        } else {
            return add(localRoot.right, item);
        }

    }// add

    public E delete(E target) {
        root = delete(root, target);

        return deleteReturn;
    }

    private Node<E> delete(Node<E> localRoot, E target) {
        if (target == null) {
            return null;
        }

        int compare_result = target.compareTo(localRoot.data);

        if (compare_result < 0) {
            return delete(localRoot.left, target);
        } else if (compare_result > 0) {
            return delete(localRoot.right, target);

        } else {
            deleteReturn = localRoot.data;

            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else //if has children
            {
                if (localRoot.left.right == null) {
                    /**the left child has no right child.  Replace the data in the
                     * localRoot with the data in the left child.*/
                    localRoot.data = localRoot.left.data;

                    /**Replace the left child with its left child.*/
                    localRoot.left = localRoot.left.left;
                } else
                    /**Find the rightmost node in the right subtree of the left
                    * child*/
                {
                    localRoot.data = findGreatestChild(localRoot.left);
                }

                return localRoot;

            }
        }//else

    }//delete

    /**Find the node tha tis in inorder predecessor and replace it with left 
     * child (if any)
     * */
    private E findGreatestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;

            parent.right = parent.right.left;

            return returnValue;
        } else {
            return findGreatestChild(parent.right);
        }
    }
}
