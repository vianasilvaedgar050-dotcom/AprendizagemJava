import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    private final ArrayList<Books> books = new ArrayList<>();

    public void addBook(Scanner scanner) {
        String title = readRequiredText(scanner, "Digite o título: ");
        if (title == null) {
            return;
        }
        String author = readRequiredText(scanner, "Digite o autor: ");
        if (author == null) {
            return;
        }
        int year = readYear(scanner);
        if (year <= 0) {
            return;
        }

        books.add(new Books(title, author, year));
        System.out.println("Livro cadastrado com sucesso.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("\nLivros cadastrados:");
        for (Books book : books) {
            System.out.println("--------------------");
            System.out.println("Título: " + book.getTitle());
            System.out.println("Autor: " + book.getAuthor());
            System.out.println("Ano: " + book.getYear());
            System.out.println("Status: " + (book.isRented() ? "Emprestado" : "Disponível"));
        }
        System.out.println("--------------------");
    }

    public void rentBook(Scanner scanner) {
        String title = readRequiredText(scanner, "Digite o título do livro para emprestar: ");
        if (title == null) {
            return;
        }

        for (Books book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isRented()) {
                book.setRented(true);
                System.out.println("Livro emprestado: " + book.getTitle());
                return;
            }
        }

        System.out.println("Livro disponível não encontrado com esse título.");
    }

    public void returnBook(Scanner scanner) {
        String title = readRequiredText(scanner, "Digite o título do livro para devolver: ");
        if (title == null) {
            return;
        }

        for (Books book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isRented()) {
                book.setRented(false);
                System.out.println("Livro devolvido: " + book.getTitle());
                return;
            }
        }

        System.out.println("Livro emprestado não encontrado com esse título.");
    }

    private String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return null;
            }

            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("O valor não pode ficar vazio.");
        }
    }

    private int readYear(Scanner scanner) {
        while (true) {
            System.out.print("Digite o ano de publicação: ");
            if (!scanner.hasNextLine()) {
                return -1;
            }

            String value = scanner.nextLine().trim();
            try {
                int year = Integer.parseInt(value);
                if (year > 0) {
                    return year;
                }
            } catch (NumberFormatException ignored) {
                // Continue asking until the user enters a valid year.
            }
            System.out.println("Digite um ano válido (número maior que zero).");
        }
    }
}
