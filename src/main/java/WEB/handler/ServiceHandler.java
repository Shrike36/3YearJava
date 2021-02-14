package WEB.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import domain.Book;
import domain.DefaultProduct;
import domain.Journal;
import domain.Newspaper;
import service.BookService;
import service.JournalService;
import service.NewspaperService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServiceHandler extends HttpServlet {

    public static final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

    //private static final ProductService productService = new ProductService();
    private static final BookService bookService = new BookService();
    private static final JournalService journalService = new JournalService();
    private static final NewspaperService newspaperService = new NewspaperService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        List<Book> books = bookService.getBooksInWarehouse();
        out.println(writer.writeValueAsString(books));
        List<Newspaper> newspapers = newspaperService.getNewspapersInWarehouse();
        out.println(writer.writeValueAsString(newspapers));
        List<Journal> journals = journalService.getJournalsInWarehouse();
        out.println(writer.writeValueAsString(journals));
    }

}
