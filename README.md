  O projeto a ser desenvolvido é uma versão de autenticador digital, ou seja, um aplicativo para verificação da autenticidade de documentos. O aplicativo deverá gerar códigos de autenticação para documentos textuais. A geração do código de autenticação do documento deverá ser feita com o auxílio de um conjunto de estruturas de dados. Cada estrutura tem um papel específico na geração de tal código e segue a seguinte sequência de passos:
  
1. Realizar a leitura de um arquivo textual, em arquivo .txt;
2. Cada linha do texto é consumida individualmente e as palavras presentes nessa linha textual são inseridas em uma lista dinâmica. Nesse caso, as palavras são obtidas ao identificar os espaços em branco entre textos.
3. Deve-se em seguida, acessar os elementos da lista no sentido reverso (do final até o início) e inserir tais elementos um a um em uma árvore AVL. Para realizar as comparações lexicográficas Strings, pode-se utilizar o método compareToIgnoreCase(). Além disso, deve-se desconsiderar quaisquer palavras duplicadas na String em questão.
4. Após o cadastro de todas as Strings para aquela linha, deve-se inserir a árvore AVL em uma pilha.
5. Os procedimentos devem ser repetidos para todas as linhas no texto.
6. Após a pilha de árvores ser construída, deve-se desempilhar as árvores de uma em uma, e gerar o código hash para aquela árvore desempilhada.
7. Cada hash gerado de cada uma das árvores deve ser concatenado e separados por uma quebra de linha.
