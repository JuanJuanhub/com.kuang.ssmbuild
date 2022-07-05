package com.kuang.controller;

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    //调service层
    @Autowired
    @Qualifier(value = "BookServiceImpl")
    private BookService bookService;

    //查询全部的书籍并且返回到一个书籍展示页面

    @RequestMapping("/allBook")
    public String list(Model model){

        List<Books> list = bookService.queryAllBook();

        model.addAttribute("list",list);

        return "allBook";
    }

    //跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper(){
        return "addBook";
    }

    //添加书籍请求

    @RequestMapping("/addBook")
    public String addBook(Books books){
        System.out.println("addBook=>"+books);
        bookService.addBook(books);
        return "redirect:/book/allBook";    //重定向到 /addBook请求
    }

    //跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdatePaper(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("book",books);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updatebook(Books books){

        bookService.updateBook(books);
        System.out.println(books);

        return "redirect: /book/allBook";

    }

    //删除书籍
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") int id){

        bookService.deleteBookById(id);

        return "redirect: /book/allBook";
    }

    //按name查询书籍,l
    @RequestMapping("/queryBook")
    public String queryBookByName(String queryBookName,Model model){
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();

        list.add(books);

        model.addAttribute("list",list);
        return "allBook";
    }



}
