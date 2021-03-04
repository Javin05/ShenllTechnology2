package com.book.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.BookDAO;
import com.book.pojo.Book;
 


public class BookControllerServlet extends HttpServlet  {
	
	
	
	 private static final long serialVersionUID = 1L;
	    private BookDAO bookDAO;
	 
	    public void init() {
	        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
	        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
	        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
	 
	        bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	 
	    }
	 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	System.out.println("doPost entered EWE$#$#$##$#$##$#$#$#$"); 
	        doGet(request, response);
	    }
	 
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getServletPath();
	 
	        try {
	            switch (action) {
	            case "/new":
	                showNewForm(request, response);
	                break;
	            case "/insert":
	                insertBook(request, response);
	                break;
	            case "/delete":
	                deleteBook(request, response);
	                break;
	            case "/edit":
	                showEditForm(request, response);
	                break;
	            case "/update":
	                updateBook(request, response);
	                break;
	            default:
	                listBook(request, response);
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	    }
	 
	    

	  private void listBook(HttpServletRequest request, HttpServletResponse
	  response) throws SQLException, IOException, ServletException {
	  
	  System.out.println("listBook entered EWE$#$#$##$#$##$#$#$#$");
	  
	  

String uname = request.getParameter("username");
String password = request.getParameter("password");

boolean result=bookDAO.loginValidate(uname, password);
System.out.println("validate result"+result);

if (result) {
		

	  List<Book> listBook = bookDAO.listAllBooks();
	  request.setAttribute("listBook", listBook); RequestDispatcher dispatcher =
	  request.getRequestDispatcher("BookList.jsp"); dispatcher.forward(request,
	  response); 
	  

} else {
	response.sendRedirect("error.jsp");
	//out.println("login failed");
}
	  
	  
	  
	  }
	 
	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	System.out.println("new  entered bookForm jsp $#$#$##$#$##$#$#$#$");

	        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	    	
	    	System.out.println("edit form entered R$#$#$#$#$#$#");
	        int id = Integer.parseInt(request.getParameter("id"));
	        Book existingBook = bookDAO.getBook(id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
	        request.setAttribute("book", existingBook);
	        dispatcher.forward(request, response);
	 
	    }
	 
	    private void insertBook(HttpServletRequest request, HttpServletResponse response) 
	            throws SQLException, IOException {
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        float price = Float.parseFloat(request.getParameter("price"));
	 
	        Book newBook = new Book(title, author, price);
	        bookDAO.insertBook(newBook);
	        response.sendRedirect("listBook");
	    }
	 
	    private void updateBook(HttpServletRequest request, HttpServletResponse response) 
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        float price = Float.parseFloat(request.getParameter("price"));
	 
	        Book book = new Book(id, title, author, price);
	        bookDAO.updateBook(book);
	        response.sendRedirect("listBook");
	    }
	 
	    private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	 
	        Book book = new Book(id);
	        bookDAO.deleteBook(book);
	        response.sendRedirect("listBook");
	 
	    }

	
	

}
