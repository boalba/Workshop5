package pl.coderslab.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.book.Book;
import pl.coderslab.book.service.MemoryBookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private MemoryBookService memoryBookService;


    @GetMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("/")
    public List<Book> allBooks(){
        return memoryBookService.getList();
    }

    @GetMapping("/{id}")
    public Book allBooks(@PathVariable long id){
        return memoryBookService.bookById(id);
    }

    @PostMapping("/")
    public void addBook(@RequestBody Book book){
        memoryBookService.addBook(book);
    }

    @PutMapping("/{id}")
    public String modifyBook(@PathVariable long id, @RequestBody Book book){
        if(id == book.getId()){
            memoryBookService.modifyBook(book);
            return "{książka zmieniona}";
        }else{
            return "{nie ma takiej książki}";
        }
    }
}

