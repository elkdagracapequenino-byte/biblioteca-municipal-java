import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

// ==========================================
// ESTRUTURAS DE DADOS
// ==========================================

// Livros
static int[] idLivros = new int[100];
static String[] titulos = new String[100];
static String[] autores = new String[100];
static int[] anosPublicacao = new int[100];
static int[] quantidades = new int[100];
static int[] totalEmprestimos = new int[100];

// Utilizadores
static int[] idUtilizadores = new int[100];
static String[] nomesUtilizadores = new String[100];
static String[] contactosUtilizadores = new String[100];

// Contadores
static int numeroLivros = 0;
static int numeroUtilizadores = 0;

// Matriz de empréstimos
static int[][] emprestimos = new int[100][100];

    public static void main(String[] args) {

        int opcao;

        do {
            // ==========================================
// REGISTAR LIVRO
// ==========================================

public static void registarLivro() {

    if (numeroLivros >= idLivros.length) {
        System.out.println("\nErro: limite máximo de livros atingido.");
        return;
    }

    System.out.println("\n========== REGISTAR LIVRO ==========");

    int id = lerInteiro("ID do livro: ");

    // Verificar se o ID já existe
    for (int i = 0; i < numeroLivros; i++) {

        if (idLivros[i] == id) {
            System.out.println("Erro: já existe um livro com esse ID.");
            return;
        }
    }

    System.out.print("Título: ");
    String titulo = scanner.nextLine();

    System.out.print("Autor: ");
    String autor = scanner.nextLine();

    int ano = lerInteiro("Ano de publicação: ");
    int quantidade = lerInteiro("Quantidade disponível: ");

    if (ano <= 0) {
        System.out.println("Erro: ano de publicação inválido.");
        return;
    }

    if (quantidade < 0) {
        System.out.println("Erro: a quantidade não pode ser negativa.");
        return;
    }

    idLivros[numeroLivros] = id;
    titulos[numeroLivros] = titulo;
    autores[numeroLivros] = autor;
    anosPublicacao[numeroLivros] = ano;
    quantidades[numeroLivros] = quantidade;
    totalEmprestimos[numeroLivros] = 0;

    numeroLivros++;

    System.out.println("\nLivro registado com sucesso!");
        }
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
    registarLivro();
    break;
                    // ==========================================
// LISTAR CATÁLOGO
// ==========================================

public static void listarCatalogo() {

    System.out.println("\n========== CATÁLOGO DE LIVROS ==========");

    if (numeroLivros == 0) {
        System.out.println("Nenhum livro registado.");
        return;
    }

    for (int i = 0; i < numeroLivros; i++) {

        System.out.println("\nID: " + idLivros[i]);
        System.out.println("Título: " + titulos[i]);
        System.out.println("Autor: " + autores[i]);
        System.out.println("Ano: " + anosPublicacao[i]);
        System.out.println("Disponível: " + quantidades[i]);
        System.out.println("----------------------------------------");
    }
}
                    // ==========================================
// PESQUISAR LIVRO
// ==========================================

public static void pesquisarLivro() {

    if (numeroLivros == 0) {
        System.out.println("\nNenhum livro registado.");
        return;
    }

    System.out.println("\n========== PESQUISAR LIVRO ==========");
    System.out.println("1. Pesquisar por título");
    System.out.println("2. Pesquisar por autor");

    int opcao = lerInteiro("Escolha uma opção: ");

    System.out.print("Digite o termo de pesquisa: ");
    String termo = scanner.nextLine().toLowerCase();

    boolean encontrado = false;

    for (int i = 0; i < numeroLivros; i++) {

        boolean corresponde = false;

        if (opcao == 1) {
            corresponde = titulos[i].toLowerCase().contains(termo);
        } 
        else if (opcao == 2) {
            corresponde = autores[i].toLowerCase().contains(termo);
        } 
        else {
            System.out.println("Opção inválida.");
            return;
        }

        if (corresponde) {

            System.out.println("\nID: " + idLivros[i]);
            System.out.println("Título: " + titulos[i]);
            System.out.println("Autor: " + autores[i]);
            System.out.println("Ano: " + anosPublicacao[i]);
            System.out.println("Disponível: " + quantidades[i]);
            System.out.println("----------------------------------------");

            encontrado = true;
        }
    }

    if (!encontrado) {
        System.out.println("\nNenhum livro encontrado.");
    }
                }
                case 2:
    listarCatalogo();
    break;

                case 3:
    pesquisarLivro();
    break;

                case 4:
                    System.out.println("\n[Registo de Utilizador]");
                    break;

                case 5:
                    System.out.println("\n[Lista de Utilizadores]");
                    break;

                case 6:
                    System.out.println("\n[Empréstimo de Livro]");
                    break;

                case 7:
                    System.out.println("\n[Devolução de Livro]");
                    break;

                case 8:
                    System.out.println("\n[Estatísticas]");
                    break;

                case 9:
                    System.out.println("\nPrograma encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 9);

        scanner.close();
    }

    // ==========================================
    // MENU PRINCIPAL
    // ==========================================

    public static void mostrarMenu() {

        System.out.println("\n========================================");
        System.out.println("          BIBLIOTECA MUNICIPAL");
        System.out.println("========================================");
        System.out.println("1. Registar livro");
        System.out.println("2. Listar catálogo");
        System.out.println("3. Pesquisar livro");
        System.out.println("4. Registar utilizador");
        System.out.println("5. Listar utilizadores");
        System.out.println("6. Efectuar empréstimo");
        System.out.println("7. Registar devolução");
        System.out.println("8. Estatísticas");
        System.out.println("9. Sair");
        System.out.println("========================================");
    }

    // ==========================================
    // LEITURA DE NÚMEROS INTEIROS
    // ==========================================

    public static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            try {

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Erro: introduza um número válido.");
            }
        }
    }
                      }
