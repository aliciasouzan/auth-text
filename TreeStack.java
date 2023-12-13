class TreeStackNode {
  AVLTree tree;
  TreeStackNode next;

  public TreeStackNode(AVLTree tree) {
    this.tree = tree;
    this.next = null;
  }
}

public class TreeStack {
  private TreeStackNode top;

  public TreeStack() {
    this.top = null;
  }

  public void push(AVLTree tree) {
    TreeStackNode newNode = new TreeStackNode(tree);
    newNode.next = top;
    top = newNode;
  }

  public AVLTree pop() {
    if (isEmpty()) {
      return null;
    }

    AVLTree poppedTree = top.tree;
    top = top.next;
    return poppedTree;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public void displayStack() {
    TreeStackNode current = top;
    while (current != null) {
      current.tree.preOrderTraversal();
      current = current.next;
    }
  }
}