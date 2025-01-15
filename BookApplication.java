import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import javax.servlet.*;
import java.util.*;

public class BookApplication {

    public static void main(String[] args) throws ServletException {
        // Set up the Spring context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        // Set up the DispatcherServlet
        ServletContext servletContext = new org.eclipse.jetty.server.Server().getServletContext();
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.addMapping("/");

        // Start the server
        dispatcher.setLoadOnStartup(1);
        servletContext.setContextPath("/");

        new org.eclipse.jetty.server.Server().start();
    }

    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages = "com.example")
    static class AppConfig implements WebMvcConfigurer {
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.jsp("/WEB-INF/templates/", ".html");
        }
    }

    @Component
    static class Book {
        private String title;
        private String author;
        private String isbn;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
    }

    @Controller
    @RequestMapping("/books")
    static class BookController {

        private final List<Book> books = new ArrayList<>();

        @GetMapping
        public String showBooks(Model model) {
            model.addAttribute("books", books);
            return "list-books";
        }

        @GetMapping("/add")
        public String showAddBookForm(Model model) {
            model.addAttribute("book", new Book("", "", ""));
            return "add-book";
        }

        @PostMapping("/add")
        public String addBook(@ModelAttribute Book book) {
            books.add(book);
            return "redirect:/books";
        }
    }

    // Embedded Thymeleaf views as Java strings
    public static class ThymeleafTemplates {
        public static String addBookView() {
            return "<html xmlns:th=\"http://www.thymeleaf.org\">" +
                    "<head><title>Add Book</title></head>" +
                    "<body>" +
                    "<h1>Add New Book</h1>" +
                    "<form action=\"#\" th:action=\"@{/books/add}\" th:object=\"${book}\" method=\"post\">" +
                    "<label for=\"title\">Title:</label>" +
                    "<input type=\"text\" id=\"title\" th:field=\"*{title}\" required/><br/>" +
                    "<label for=\"author\">Author:</label>" +
                    "<input type=\"text\" id=\"author\" th:field=\"*{author}\" required/><br/>" +
                    "<label for=\"isbn\">ISBN:</label>" +
                    "<input type=\"text\" id=\"isbn\" th:field=\"*{isbn}\" required/><br/>" +
                    "<button type=\"submit\">Add Book</button>" +
                    "</form>" +
                    "<a href=\"/books\">Back to Book List</a>" +
                    "</body></html>";
        }

        public static String listBooksView() {
            return "<html xmlns:th=\"http://www.thymeleaf.org\">" +
                    "<head><title>Books List</title></head>" +
                    "<body>" +
                    "<h1>Books List</h1>" +
                    "<a href=\"/books/add\">Add New Book</a>" +
                    "<table>" +
                    "<thead><tr><th>Title</th><th>Author</th><th>ISBN</th></tr></thead>" +
                    "<tbody>" +
                    "<tr th:each=\"book : ${books}\">" +
                    "<td th:text=\"${book.title}\"></td>" +
                    "<td th:text=\"${book.author}\"></td>" +
                    "<td th:text=\"${book.isbn}\"></td>" +
                    "</tr>" +
                    "</tbody>" +
                    "</table>" +
                    "</body></html>";
        }
    }
}
