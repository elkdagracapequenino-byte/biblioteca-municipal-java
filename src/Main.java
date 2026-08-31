import java.util.Scanner;

public class Main {

    static final int MAX_LIVROS = 100;
    static final int MAX_UTILIZADORES = 100;

    static Scanner scanner = new Scanner(System.in);

    // ==========================================
    // DADOS DOS LIVROS
    // ==========================================

    static int[] idLivros = new int[MAX_LIVROS];
    static String[] titulos = new String[MAX_LIVROS];
    static String[] autores = new String[MAX_LIVROS];
    static int[] anosPublicacao = new int[MAX_LIVROS];
    static int[] quantidades = new int[MAX_LIVROS];
    static int[] totalEmprestimos = new int[MAX_LIVROS];

    // ==========================================
    // DADOS DOS UTILIZADORES
    // ==========================================

    static int[] idUtilizadores = new int[MAX_UTILIZADORES];
    static String[] nomesUtilizadores = new String[MAX_UTILIZADORES];
    static String[] contactosUtilizadores = new String[MAX_UTILIZADORES];

    // ==========================================
    // CONTADORES
    // ==========================================

    static int numeroLivros = 0;
    static int numeroUtilizadores = 0;
    static int numeroTotalEmprestimos = 0;

    // ==========================================
    // MATRIZ DE EMPRÉSTIMOS
    // ==========================================

    static int[][] emprestimos =
            new int[MAX_UTILIZADORES][MAX_LIVROS];

    // ==========================================
    // MÉTODO PRINCIPAL
    // ==========================================

    public static void main(String[] args) {

        int opcao;

        do {
            mostrarMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    registarLivro();
                    break;

                case 2:
                    listarCatalogo();
                    break;

                case 3:
                    pesquisarLivro();
                    break;

                case 4:
                    registarUtilizador();
                    break;

                case 5:
                    listarUtilizadores();
                    break;

                case 6:
                    efectuarEmprestimo();
                    break;

                case 7:
                    registarDevolucao();
                    break;

                case 8:
                    mostrarEstatisticas();
                    break;

                case 9:
                    System.out.println("\nPrograma encerrado.");
                    break;

                default:
                    System.out.println(
                            "\nErro: opção inválida. " +
                            "Escolha uma opção entre 1 e 9."
                    );
            }

        } while (opcao != 9);

        scanner.close();
    }

    // ==========================================
    // MENU PRINCIPAL
    // ==========================================

    public static void mostrarMenu() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("          BIBLIOTECA MUNICIPAL");
        System.out.println("==========================================");
        System.out.println("1. Registar livro");
        System.out.println("2. Listar catálogo");
        System.out.println("3. Pesquisar livro");
        System.out.println("4. Registar utilizador");
        System.out.println("5. Listar utilizadores");
        System.out.println("6. Efectuar empréstimo");
        System.out.println("7. Registar devolução");
        System.out.println("8. Estatísticas");
        System.out.println("9. Sair");
        System.out.println("==========================================");
    }

    // ==========================================
    // REGISTO DE LIVROS
    // ==========================================

    public static void registarLivro() {

        System.out.println();
        System.out.println("========== REGISTO DE LIVRO ==========");

        if (numeroLivros >= MAX_LIVROS) {
            System.out.println(
                    "Erro: o limite máximo de livros foi atingido."
            );
            return;
        }

        int id = lerInteiroPositivo("ID do livro: ");

        if (encontrarLivro(id) != -1) {
            System.out.println(
                    "Erro: já existe um livro com o ID " + id + "."
            );
            return;
        }

        String titulo = lerTextoNaoVazio("Título: ");

        String autor = lerTextoNaoVazio("Autor: ");

        int ano = lerInteiroPositivo(
                "Ano de publicação: "
        );

        int quantidade = lerInteiroNaoNegativo(
                "Quantidade disponível: "
        );

        idLivros[numeroLivros] = id;
        titulos[numeroLivros] = titulo;
        autores[numeroLivros] = autor;
        anosPublicacao[numeroLivros] = ano;
        quantidades[numeroLivros] = quantidade;
        totalEmprestimos[numeroLivros] = 0;

        numeroLivros++;

        System.out.println();
        System.out.println("Livro registado com sucesso!");
            }
        // ==========================================
    // LISTAR CATÁLOGO
    // ==========================================

    public static void listarCatalogo() {

        System.out.println();
        System.out.println("========== CATÁLOGO DE LIVROS ==========");

        if (numeroLivros == 0) {
            System.out.println(
                    "Não existem livros registados."
            );
            return;
        }

        for (int i = 0; i < numeroLivros; i++) {
            mostrarDadosLivro(i);
        }
    }

    // ==========================================
    // PESQUISAR LIVRO
    // ==========================================

    public static void pesquisarLivro() {

        System.out.println();
        System.out.println("========== PESQUISAR LIVRO ==========");

        if (numeroLivros == 0) {
            System.out.println(
                    "Não existem livros registados."
            );
            return;
        }

        System.out.println("1. Pesquisar por título");
        System.out.println("2. Pesquisar por autor");

        int opcao = lerInteiro(
                "Escolha uma opção: "
        );

        if (opcao != 1 && opcao != 2) {
            System.out.println(
                    "Erro: opção de pesquisa inválida."
            );
            return;
        }

        String termo = lerTextoNaoVazio(
                "Digite o termo de pesquisa: "
        ).toLowerCase();

        boolean encontrado = false;

        for (int i = 0; i < numeroLivros; i++) {

            boolean corresponde;

            if (opcao == 1) {

                corresponde = titulos[i]
                        .toLowerCase()
                        .contains(termo);

            } else {

                corresponde = autores[i]
                        .toLowerCase()
                        .contains(termo);
            }

            if (corresponde) {

                mostrarDadosLivro(i);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(
                    "\nNenhum livro encontrado para: "
                    + termo
            );
        }
    }

    // ==========================================
    // REGISTO DE UTILIZADOR
    // ==========================================

    public static void registarUtilizador() {

        System.out.println();
        System.out.println(
                "======= REGISTO DE UTILIZADOR ======="
        );

        if (numeroUtilizadores >= MAX_UTILIZADORES) {

            System.out.println(
                    "Erro: o limite máximo de utilizadores "
                    + "foi atingido."
            );

            return;
        }

        int id = lerInteiroPositivo(
                "ID do utilizador: "
        );

        if (encontrarUtilizador(id) != -1) {

            System.out.println(
                    "Erro: já existe um utilizador com o ID "
                    + id + "."
            );

            return;
        }

        String nome = lerTextoNaoVazio(
                "Nome: "
        );

        String contacto = lerTextoNaoVazio(
                "Contacto: "
        );

        idUtilizadores[numeroUtilizadores] = id;
        nomesUtilizadores[numeroUtilizadores] = nome;
        contactosUtilizadores[numeroUtilizadores] = contacto;

        numeroUtilizadores++;

        System.out.println();
        System.out.println(
                "Utilizador registado com sucesso!"
        );
    }

    // ==========================================
    // LISTAR UTILIZADORES
    // ==========================================

    public static void listarUtilizadores() {

        System.out.println();

        System.out.println(
                "========== LISTA DE UTILIZADORES =========="
        );

        if (numeroUtilizadores == 0) {

            System.out.println(
                    "Não existem utilizadores registados."
            );

            return;
        }

        for (int i = 0; i < numeroUtilizadores; i++) {

            System.out.println();

            System.out.println(
                    "ID: " + idUtilizadores[i]
            );

            System.out.println(
                    "Nome: " + nomesUtilizadores[i]
            );

            System.out.println(
                    "Contacto: " + contactosUtilizadores[i]
            );

            System.out.println(
                    "------------------------------------------"
            );
        }
        }
        // ==========================================
    // EFECTUAR EMPRÉSTIMO
    // ==========================================

    public static void efectuarEmprestimo() {

        System.out.println();

        System.out.println(
                "========== EFECTUAR EMPRÉSTIMO =========="
        );

        if (numeroLivros == 0) {

            System.out.println(
                    "Erro: não existem livros registados."
            );

            return;
        }

        if (numeroUtilizadores == 0) {

            System.out.println(
                    "Erro: não existem utilizadores registados."
            );

            return;
        }

        int idUtilizador = lerInteiroPositivo(
                "ID do utilizador: "
        );

        int indiceUtilizador =
                encontrarUtilizador(idUtilizador);

        if (indiceUtilizador == -1) {

            System.out.println(
                    "Erro: utilizador não encontrado."
            );

            return;
        }

        int idLivro = lerInteiroPositivo(
                "ID do livro: "
        );

        int indiceLivro =
                encontrarLivro(idLivro);

        if (indiceLivro == -1) {

            System.out.println(
                    "Erro: livro não encontrado."
            );

            return;
        }

        if (quantidades[indiceLivro] <= 0) {

            System.out.println(
                    "Erro: o livro não possui exemplares "
                    + "disponíveis."
            );

            return;
        }

        if (emprestimos[indiceUtilizador][indiceLivro] == 1) {

            System.out.println(
                    "Erro: este utilizador já possui "
                    + "este livro emprestado."
            );

            return;
        }

        // Registar o empréstimo na matriz
        emprestimos[indiceUtilizador][indiceLivro] = 1;

        // Diminuir quantidade disponível
        quantidades[indiceLivro]--;

        // Aumentar contador de empréstimos do livro
        totalEmprestimos[indiceLivro]++;

        // Aumentar contador geral
        numeroTotalEmprestimos++;

        System.out.println();
        System.out.println(
                "Empréstimo efectuado com sucesso!"
        );

        System.out.println(
                "Livro: " + titulos[indiceLivro]
        );

        System.out.println(
                "Utilizador: "
                + nomesUtilizadores[indiceUtilizador]
        );

        System.out.println(
                "Quantidade disponível: "
                + quantidades[indiceLivro]
        );
    }

    // ==========================================
    // REGISTAR DEVOLUÇÃO
    // ==========================================

    public static void registarDevolucao() {

        System.out.println();

        System.out.println(
                "========== REGISTAR DEVOLUÇÃO =========="
        );

        if (numeroLivros == 0) {

            System.out.println(
                    "Erro: não existem livros registados."
            );

            return;
        }

        if (numeroUtilizadores == 0) {

            System.out.println(
                    "Erro: não existem utilizadores registados."
            );

            return;
        }

        int idUtilizador = lerInteiroPositivo(
                "ID do utilizador: "
        );

        int indiceUtilizador =
                encontrarUtilizador(idUtilizador);

        if (indiceUtilizador == -1) {

            System.out.println(
                    "Erro: utilizador não encontrado."
            );

            return;
        }

        int idLivro = lerInteiroPositivo(
                "ID do livro: "
        );

        int indiceLivro =
                encontrarLivro(idLivro);

        if (indiceLivro == -1) {

            System.out.println(
                    "Erro: livro não encontrado."
            );

            return;
        }

        if (emprestimos[indiceUtilizador][indiceLivro] == 0) {

            System.out.println(
                    "Erro: este utilizador não possui "
                    + "este livro emprestado."
            );

            return;
        }

        // Remover o empréstimo da matriz
        emprestimos[indiceUtilizador][indiceLivro] = 0;

        // Devolver o exemplar ao catálogo
        quantidades[indiceLivro]++;

        System.out.println();
        System.out.println(
                "Devolução registada com sucesso!"
        );

        System.out.println(
                "Livro: " + titulos[indiceLivro]
        );

        System.out.println(
                "Utilizador: "
                + nomesUtilizadores[indiceUtilizador]
        );

        System.out.println(
                "Quantidade disponível: "
                + quantidades[indiceLivro]
        );
    }

    // ==========================================
    // ESTATÍSTICAS
    // ==========================================

    public static void mostrarEstatisticas() {

        System.out.println();

        System.out.println(
                "========== ESTATÍSTICAS =========="
        );

        if (numeroLivros == 0) {

            System.out.println(
                    "Não existem livros registados."
            );

            return;
        }

        System.out.println(
                "Total de títulos registados: "
                + numeroLivros
        );

        int totalDisponivel = 0;

        for (int i = 0; i < numeroLivros; i++) {

            totalDisponivel += quantidades[i];
        }

        System.out.println(
                "Total de exemplares disponíveis: "
                + totalDisponivel
        );

        System.out.println(
                "Total de empréstimos realizados: "
                + numeroTotalEmprestimos
        );

        int indiceMaisEmprestado = 0;

        for (int i = 1; i < numeroLivros; i++) {

            if (totalEmprestimos[i]
                    > totalEmprestimos[indiceMaisEmprestado]) {

                indiceMaisEmprestado = i;
            }
        }

        System.out.println();

        System.out.println(
                "Livro mais emprestado: "
                + titulos[indiceMaisEmprestado]
        );

        System.out.println(
                "Autor: "
                + autores[indiceMaisEmprestado]
        );

        System.out.println(
                "Número de empréstimos: "
                + totalEmprestimos[indiceMaisEmprestado]
        );

        System.out.println(
                "=================================="
        );
                }
        // ==========================================
    // PROCURAR LIVRO PELO ID
    // ==========================================

    public static int encontrarLivro(int id) {

        for (int i = 0; i < numeroLivros; i++) {

            if (idLivros[i] == id) {

                return i;
            }
        }

        return -1;
    }

    // ==========================================
    // PROCURAR UTILIZADOR PELO ID
    // ==========================================

    public static int encontrarUtilizador(int id) {

        for (int i = 0; i < numeroUtilizadores; i++) {

            if (idUtilizadores[i] == id) {

                return i;
            }
        }

        return -1;
    }

    // ==========================================
    // MOSTRAR DADOS DE UM LIVRO
    // ==========================================

    public static void mostrarDadosLivro(int indice) {

        System.out.println();

        System.out.println(
                "ID: " + idLivros[indice]
        );

        System.out.println(
                "Título: " + titulos[indice]
        );

        System.out.println(
                "Autor: " + autores[indice]
        );

        System.out.println(
                "Ano de publicação: "
                + anosPublicacao[indice]
        );

        System.out.println(
                "Quantidade disponível: "
                + quantidades[indice]
        );

        System.out.println(
                "Total de empréstimos: "
                + totalEmprestimos[indice]
        );

        System.out.println(
                "------------------------------------------"
        );
    }

    // ==========================================
    // LER INTEIRO
    // ==========================================

    public static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Erro: introduza um número inteiro válido."
                );
            }
        }
    }

    // ==========================================
    // LER INTEIRO POSITIVO
    // ==========================================

    public static int lerInteiroPositivo(String mensagem) {

        while (true) {

            int valor = lerInteiro(mensagem);

            if (valor > 0) {

                return valor;
            }

            System.out.println(
                    "Erro: o valor deve ser maior que zero."
            );
        }
    }

    // ==========================================
    // LER INTEIRO NÃO NEGATIVO
    // ==========================================

    public static int lerInteiroNaoNegativo(String mensagem) {

        while (true) {

            int valor = lerInteiro(mensagem);

            if (valor >= 0) {

                return valor;
            }

            System.out.println(
                    "Erro: o valor não pode ser negativo."
            );
        }
    }

    // ==========================================
    // LER TEXTO NÃO VAZIO
    // ==========================================

    public static String lerTextoNaoVazio(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto =
                    scanner.nextLine().trim();

            if (!texto.isEmpty()) {

                return texto;
            }

            System.out.println(
                    "Erro: este campo não pode ficar vazio."
            );
        }
    }
            }
