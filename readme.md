# PROJETO 2 DE ESTRUTURAS DE DADOS

Este projeto implementa duas estruturas de dados: **Árvore AVL** e **Árvore Binária de Busca (BST)**. Ambas as estruturas têm como objetivo processar e manipular dados contidos em um arquivo CSV localizado no diretório `DATA`.

## OBJETIVO

O propósito deste projeto é demonstrar a eficiência da Árvore AVL em comparação com a Árvore Binária de Busca (BST) ao realizar operações de inserção e busca em um conjunto de dados. Para isso, escolhemos como base de dados um arquivo disponibilizado pelo governo sobre "**Dados de Alunos Estrangeiros por Nacionalidade**", contendo informações relevantes sobre escolas e alunos estrangeiros no Brasil.

## FUNCIONALIDADES

O sistema oferece as seguintes opções de consulta nas árvores:

1. **Quantidade de alunos por país em uma escola**  
   Permite consultar a quantidade de alunos estrangeiros de um determinado país em uma escola específica.

2. **Quantidade de alunos por país no Brasil**  
   Exibe o total de alunos estrangeiros de um país em todas as escolas do Brasil.

3. **Mostrar escolas em ordem alfabética**  
   Exibe a lista de escolas ordenadas alfabeticamente.

4. **Mostrar número de alunos estrangeiros em cada escola**  
   Exibe o número total de alunos estrangeiros em cada escola.

## ESTRUTURAS DE DADOS IMPLEMENTADAS

- **Árvore AVL (Balanced Binary Search Tree)**: Estrutura de dados balanceada, onde a altura das subárvores esquerda e direita de qualquer nó difere no máximo por 1. Garantindo assim um tempo de busca, inserção e remoção em O(log n) no pior caso.

- **Árvore Binária de Busca (BST)**: Estrutura de dados mais simples, onde os valores à esquerda de um nó são menores que o valor do nó e os valores à direita são maiores. Embora a complexidade média de busca seja O(log n), no pior caso (árvore degenerada), a complexidade pode ser O(n).

## INTEGRANTES

- Erick Nakabayashi Dedvitis - 10403532
- Felipe do Nascimento Fonseca - 10409389
- Matheus Hidalgo do Nascimento Fest Ferreira - 10390172