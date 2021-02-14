package UI;

import domain.*;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    static Scanner scanner = new Scanner(System.in);

    static BookService bookService = new BookService();
    static JournalService journalService = new JournalService();
    static NewspaperService newspaperService = new NewspaperService();
    static ProductService productService = new ProductService();
    static SoldProductService soldService = new SoldProductService();

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
                    printAllProducts(productService);
                    break;
                case 5:
                    printAllProducts(soldService);
                    break;
            }
        }

    }

    private static void changeProduct() {

    }

    private static void saleProduct() {
        System.out.println("Введите id продукта:");
        long id = scanner.nextLong();
        while (id < 0) {
            System.out.println("Некорректный индекс продукта!");
            //id = scanner.nextLong();
        }
        try {
            soldService.save(productService.getById(id));
            productService.delete(productService.getById(id).getId());
        } catch (Exception e){
            System.out.println("Ошибка!");
        }

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

    private static void printAllProducts(Service<DefaultProduct> service){

        if(service.getClass().equals(ProductService.class)) {
            printBooks(service, bookService.getBooksInWarehouse());
            printJournals(service, journalService.getJournalsInWarehouse());
            printNewspapers(service, newspaperService.getNewspapersInWarehouse());
        } else {
            printBooks(service, bookService.getSoldBooks());
            printJournals(service, journalService.getSoldJournals());
            printNewspapers(service, newspaperService.getSoldNewspapers());
        }

    }

    private static void printBooks(Service<DefaultProduct> service, List<Book> books){
        System.out.println("----------------------");
        System.out.println("|        BOOKS       |");
        System.out.println("----------------------");
        for (Book book : books) {
            System.out.println();
            System.out.printf("id: %d \n", book.getId());
            System.out.print("Название:"); System.out.println(service.getById(book.getId()).getName());
            System.out.print("Автор:"); System.out.println(book.getAuthor());
            System.out.print("Издательство:"); System.out.println(book.getPublishing());
            System.out.print("Количество страниц:"); System.out.println(book.getCountOfPages());
            System.out.println();
        }
    }

    private static void printJournals(Service<DefaultProduct> service, List<Journal> journals){
        System.out.println("-----------------------");
        System.out.println("|       JOURNALS      |");
        System.out.println("-----------------------");
        for (Journal journal : journals) {
            System.out.println();
            System.out.printf("id: %d \n", journal.getId());
            System.out.print("Название:"); System.out.println(service.getById(journal.getId()).getName());
            System.out.print("Номер:"); System.out.println(journal.getNumber());
            System.out.print("Дата:"); System.out.println(journal.getDate());
            System.out.print("Количество страниц:"); System.out.println(journal.getCountOfPages());
            System.out.println();
        }
    }

    private static void printNewspapers(Service<DefaultProduct> service, List<Newspaper> newspapers){
        System.out.println("-----------------------");
        System.out.println("|      NEWSPAPERS     |");
        System.out.println("-----------------------");
        for (Newspaper newspaper : newspapers) {
            System.out.println();
            System.out.printf("id: %d \n", newspaper.getId());
            System.out.print("Название:"); System.out.println(service.getById(newspaper.getId()).getName());
            System.out.print("Номер:"); System.out.println(newspaper.getNumber());
            System.out.print("Дата:"); System.out.println(newspaper.getDate());
            System.out.println();
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
        productService.save(product);
        newspaperService.save(new Newspaper(product.getId(), number, date));
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
        productService.save(product);
        journalService.save(new Journal(product.getId(), number, date, countOfPages));
    }

    private static void addBook(){
        System.out.println("1) Название:");
        String name = readStr();
        System.out.println("2) Количество страниц:");
        int countOfPages = readNum();
        System.out.println("3) Aвтор:");
        String author = readStr();
        System.out.println("4) Издательство:");
        String publishing = readStr();
        DefaultProduct product = new DefaultProduct(0, name);
        productService.save(product);
        bookService.save(new Book(product.getId(), countOfPages, author, publishing));
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
