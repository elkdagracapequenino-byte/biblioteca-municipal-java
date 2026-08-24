import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    System.out.println("\n[Registo de Livro]");
                    break;

                case 2:
                    System.out.println("\n[Catálogo de Livros]");
                    break;

                case 3:
                    System.out.println("\n[Pesquisar Livro]");
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
