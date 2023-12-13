import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Declaração de variáveis úteis
        String fileName = "content.txt";
        TreeStack treeStack = new TreeStack();
        String hashes = "";

        // Parte 1 - Criação da pilha de árvores

        // Busca arquivo para iniciar leitura
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Quebra a linha em palavras, e inverte a ordem para adicionar na árvore AVL
            while ((line = br.readLine()) != null) {
                AVLTree avlTree = new AVLTree();

                // Quebra a linha em palavras
                String[] words = line.split(" ");

                // Inverte a ordem das palavras e insere na árvore AVL
                for (int i = words.length - 1; i >= 0; i--) {
                    avlTree.insert(words[i]);
                }

                // Adiciona a árvore AVL pronta na pilha de árvores
                treeStack.push(avlTree);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parte 2 - Criação dos códigos Hash

        // Desempilha as árvores para criar a hash delas de uma a uma
        while (!treeStack.isEmpty()) {
            AVLTree tree = treeStack.pop();

            // Insere a hash na variável de hashes
            hashes += tree.getHash() + "\n";
        }

        System.out.println(hashes);

    }

}