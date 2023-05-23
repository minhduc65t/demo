package tranminhduc.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tranminhduc.demo.entity.Book;
import tranminhduc.demo.entity.Category;
import tranminhduc.demo.services.BookService;
import tranminhduc.demo.services.CategoryService;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listBook(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Book List");
        return "book/list";
    }
    @GetMapping("/add")
        public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
        }
      @PostMapping("/add")
      public String addBook(@Valid  @ModelAttribute("book") Book book, BindingResult result ,Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
        }

       @GetMapping("/edit/{id}")
        public String editBookForm(@PathVariable("id") Long id, Model model){
            Book editBook = bookService.getBookById(id);
            if(editBook!=null){
                model.addAttribute("book", editBook);
                model.addAttribute("categories",categoryService.getAllCategories());
                return "book/edit";
            }else {
                return "not-found";
            }
        }

        @PostMapping("/edit")
        public String editBook( @Valid @ModelAttribute("book") Book updateBook,BindingResult result, Model model){
            if(result.hasErrors()){
                model.addAttribute("categories",categoryService.getAllCategories());
                return "book/edit";
            }
        bookService.getAllBooks().stream()
                .filter(book -> book.getId() == updateBook.getId())
                .findFirst()
                .ifPresent(book -> bookService.updateBook(updateBook));
            return "redirect:/books";
        }

        @PostMapping("/delete/{id}")
        public String deleteBook(@PathVariable("id") Long id){
            bookService.deleteBook(id);
            return "redirect:/books";
        }

}
