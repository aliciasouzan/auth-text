import java.security.NoSuchAlgorithmException;
import util.Sha1;

class AVLTreeNode {

  String value;
  int height;
  AVLTreeNode left, right;

  public AVLTreeNode(String value) {
    this.value = value;
    this.height = 1;
    this.left = this.right = null;
  }
}

public class AVLTree {

  private AVLTreeNode root;

  private int height(AVLTreeNode node) {
    return (node == null) ? 0 : node.height;
  }

  private int max(int a, int b) {
    return (a > b) ? a : b;
  }

  private int getBalance(AVLTreeNode node) {
    return (node == null) ? 0 : height(node.left) - height(node.right);
  }

  private AVLTreeNode rightRotate(AVLTreeNode y) {
    AVLTreeNode x = y.left;
    AVLTreeNode T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return x;
  }

  private AVLTreeNode leftRotate(AVLTreeNode x) {
    AVLTreeNode y = x.right;
    AVLTreeNode T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  private AVLTreeNode insert(AVLTreeNode node, String value) {
    if (node == null) {
      return new AVLTreeNode(value);
    }

    int compareResult = value.compareToIgnoreCase(node.value);

    if (compareResult < 0) {
      node.left = insert(node.left, value);
    } else if (compareResult > 0) {
      node.right = insert(node.right, value);
    } else {
      return node;
    }

    node.height = 1 + max(height(node.left), height(node.right));

    int balance = getBalance(node);

    // Left Left Case
    if (balance > 1 && value.compareToIgnoreCase(node.left.value) < 0) {
      return rightRotate(node);
    }

    // Right Right Case
    if (balance < -1 && value.compareToIgnoreCase(node.right.value) > 0) {
      return leftRotate(node);
    }

    // Left Right Case
    if (balance > 1 && value.compareToIgnoreCase(node.left.value) > 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && value.compareToIgnoreCase(node.right.value) < 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  public void insert(String value) {
    root = insert(root, value);
  }

  private void preOrderTraversal(AVLTreeNode node) {
    if (node != null) {
      System.out.print(node.value + " ");
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }
  }

  public void preOrderTraversal() {
    preOrderTraversal(root);
    System.out.println();
  }

  public String getHash() {
    return getHash(root);
  }

  private String getHash(AVLTreeNode node) {
    if (node == null) {
      return "";
    }

    String leftHash = getHash(node.left);
    String rightHash = getHash(node.right);

    String currentValueHash = "";
    try {
      currentValueHash = Sha1.sha1(node.value);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    // Concatenar hashes e node value
    String concatenatedHash = "";
    if (leftHash.length() != 0) {
      concatenatedHash += leftHash;
    }
    if (rightHash.length() != 0) {
      concatenatedHash += rightHash;
    }

    // Este if faz com que as folhas da árvore não façam hash duas vezes
    if (leftHash.length() != 0 || rightHash.length() != 0) {
      // System.out.println("Got here in " + node.value);
      concatenatedHash += currentValueHash;
    } else {
      concatenatedHash = node.value;
    }

    try {
      return Sha1.sha1(concatenatedHash);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return "";
    }
  }
}
