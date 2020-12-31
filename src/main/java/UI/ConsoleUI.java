package UI;

import DAO.DAO;
import DAO.ProductDAO;
import domain.*;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    static Scanner scanner = new Scanner(System.in);

    static AuthorDTO authorDTO = new AuthorDTO();
    static BookDTO bookDTO = new BookDTO();
    static JournalDTO journalDTO = new JournalDTO();
    static NewspaperDTO newspaperDTO = new NewspaperDTO();
    static ProductDTO productDTO = new ProductDTO();
    static SoldProductDTO soldDTO = new SoldProductDTO();
    static PublishingDTO publishingDTO = new PublishingDTO();

    public static void chooseAction(){
        int action;

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("| 1) Прием товара;                   |");
            System.out.println("| 2) Продажа товара;                 |");
            System.out.println("| 3) Изменение информации о товаре;  |");
            System.out.println("| 4) Список всех товаров на складе;  |");
            System.out.println("| 5) Список всех проданных товаров;  |");
            System.out.println("--------------------------------------");

            action = scanner.nextInt();scanner.nextLine();

            switch (action) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    saleProduct();
                    break;
                case 3:
                    changeProduct();
                    break;
                case 4:
                    printAllProducts(productDTO.getAll());
                    break;
                case 5:
                    printAllProducts(soldDTO.getAll());
                    break;
            }
        }

    }

    private static void changeProduct() {

    }

    private static void saleProduct() {
        System.out.println("Введите id продукта:");
        long id = scanner.nextLong();
        while (id < 0 || id > productDTO.getAll().size()) {
            System.out.println("Некорректный индекс продукта!");
            id = scanner.nextLong();
        }
        soldDTO.save(productDTO.getById(id));
        productDTO.delete(productDTO.getById(id).getId());
    }

    private static void addProduct() {

        int type;

        System.out.println("1) Газета;");
        System.out.println("2) Журнал;");
        System.out.println("3) Книга;");

        type = scanner.nextInt(); scanner.nextLine();

        switch (type) {
            case 1:
                addNewspaper();
                break;
            case 2:
                addJournal();
                break;
            case 3:
                addBook();
                break;
        }
    }

    private static void printAllProducts(List<DefaultProduct> products){
        for (DefaultProduct product : products){
            long id = product.getId();
            if (newspaperDTO.getById(id) != null){
                Newspaper newspaper = newspaperDTO.getById(id);
                System.out.println();
                System.out.printf("id: %d \n", id);
                System.out.println("Газета");
                System.out.print("Название:"); System.out.println(product.getName());
                System.out.print("Номер:"); System.out.println(newspaper.getNumber());
                System.out.print("Дата:"); System.out.println(newspaper.getDate());
                System.out.println();
            } else if (journalDTO.getById(id) != null){
                Journal journal = journalDTO.getById(id);
                System.out.println();
                System.out.printf("id: %d \n", id);
                System.out.println("Газета");
                System.out.print("Название:"); System.out.println(product.getName());
                System.out.print("Номер:"); System.out.println(journal.getNumber());
                System.out.print("Дата:"); System.out.println(journal.getDate());
                System.out.print("Количество страниц:"); System.out.println(journal.getCountOfPages());
                System.out.println();
            } else if (bookDTO.getById(id) != null){
                Book book = bookDTO.getById(id);
                System.out.println();
                System.out.printf("id: %d \n", id);
                System.out.println("Книга");
                System.out.print("Название:"); System.out.println(product.getName());
                System.out.print("Автор:"); System.out.println(authorDTO.getById(book.getAuthorId()).getName());
                System.out.print("Издательство:"); System.out.println(publishingDTO.getById(book.getPublishingId()).getName());
                System.out.print("Количество страниц:"); System.out.println(book.getCountOfPages());
                System.out.println();
            }
        }
    }

    private static Date readDateFromLine(String line) {

        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        Date parsingDate = new Date();
        try {
            parsingDate = ft.parse(line);
        }catch (ParseException e) {
            System.out.println("Ошибка даты!");
        }

        return parsingDate;
    }

    private static void addNewspaper(){
        System.out.println("1) Название:");
        String name = readStr();
        System.out.println("2) Номер:");
        long number = readNum();
        System.out.println("3) Дата:");
        Date date = readDateFromLine(readStr());
        DefaultProduct product = new DefaultProduct(0, name);
        productDTO.save(product);
        newspaperDTO.save(new Newspaper(product.getId(), number, date));
    }

    private static void addJournal(){
        System.out.println("1) Название:");
        String name = readStr();
        System.out.println("2) Номер:");
        long number = readNum();
        System.out.println("3) Дата:");
        Date date = readDateFromLine(readStr());
        System.out.println("4) Количество страниц:");
        int countOfPages = readNum();
        DefaultProduct product = new DefaultProduct(0, name);
        productDTO.save(product);
        journalDTO.save(new Journal(product.getId(), number, date, countOfPages));
    }

    private static void addBook(){
        System.out.println("1) Название:");
        String name = readStr();
        System.out.println("2) Количество страниц:");
        int countOfPages = readNum();
        System.out.println("3) Aвтор:");
        Author author = new Author(0, readStr());
        authorDTO.save(author);
        System.out.println("4) Издательство:");
        Publishing publishing = new Publishing(0,readStr());
        publishingDTO.save(publishing);
        DefaultProduct product = new DefaultProduct(0, name);
        productDTO.save(product);
        bookDTO.save(new Book(product.getId(), countOfPages, author.getId(), publishing.getId()));
    }

    private static int readNum(){
        int num = scanner.nextInt();
        while (num <= 0){
            System.out.println("Введите чило больше нуля!");
            num = scanner.nextInt();
        }
        return num;
    }

    private static String readStr(){
        String str = scanner.nextLine();
        while (str.length() == 0){
            str = scanner.nextLine();
        }
        return str;
    }

}
