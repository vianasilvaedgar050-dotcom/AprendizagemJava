import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                showMenu();
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException exception) {
                    System.out.println("Opção inválida. Digite um número de 1 a 5.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        biblioteca.addBook(scanner);
                        break;
                    case 2:
                        biblioteca.displayBooks();
                        break;
                    case 3:
                        biblioteca.rentBook(scanner);
                        break;
                    case 4:
                        biblioteca.returnBook(scanner);
                        break;
                    case 5:
                        running = false;
                        System.out.println("Programa encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha um número de 1 a 5.");
                }
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Gerenciamento de Biblioteca ===");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Exibir livros");
        System.out.println("3. Emprestar livro");
        System.out.println("4. Devolver livro");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
