package com.demo.springboot.ctrlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.excp.BooksExpn;
import com.demo.springboot.pojo.ApiRespCodes;
import com.demo.springboot.pojo.Identity;
import com.demo.springboot.val.BookValidator;

@RestController
public class ExceptionExamplesCtrlr {
	Logger logger = LogManager.getLogger();
	
	List<Identity> books = new ArrayList<>();
	Map<Integer,Identity> booksMap = new HashMap<>();
	int id = 1;
	private void preloadBooks() {
		books.add(new Identity(id++,"Linux in Nutshell"));
		books.add(new Identity(id++,"Python in Nutshell"));
		books.add(new Identity(id++,"SpringBoot in Nutshell"));
	}
	
	private void reloadMap() {
		if(books.size() == 0) {
			preloadBooks();
		}
		for(Identity book : books) {
			booksMap.put(book.getId(),book);
		}
	}
	
	@GetMapping(value="/ok")
	public Identity getDefStatus(){
		return new Identity(1,"DefStatus=OK");
	}
	
	@GetMapping(value="/502")
	@ResponseStatus(code = HttpStatus.BAD_GATEWAY,value = HttpStatus.BAD_GATEWAY)
	public Identity get502Status(){
		return new Identity(502,"BAD_GATEWAY");
	}
	
	@GetMapping(value="/book/{id}/{name}")
	public Identity checkbook(@PathVariable(required = false, name = "id") Integer id, @PathVariable(required = false, name = "name") String name) throws Exception{
		Identity identity = null;
		logger.trace("Input values id ["+id+"], name ["+name+"]");
		BookValidator.isBookDataValid(logger, id, name);
		reloadMap();
		identity = booksMap.get(id);
		logger.debug("booksMap --> "+booksMap);
		logger.debug("book --> "+identity);
		if(identity == null) {
			logger.info("Book with Id "+id+" not found");
			throw new BooksExpn("Book with Id "+id+" not found");
		}
		return identity;
	}
	
	@PostMapping(path="add-book")
	public Identity addbook(@RequestBody Identity book) throws Exception{
		Integer id = book.getId();
		String name = book.getName();
		Identity identity = null;
		BookValidator.isBookDataValid(logger, id, name);
		reloadMap();
		identity = booksMap.get(id);
		logger.debug("booksMap --> "+booksMap);
		logger.debug("book --> "+identity);
		if(identity == null) {
			identity = new Identity(id,name);
			booksMap.put(id,identity);
			logger.info("added the book --> "+identity);
			logger.debug("booksMap --> "+booksMap);
		}else {
			logger.error("Book with id "+id+" already exists");
			throw new BooksExpn(ApiRespCodes.ID_ALREADY_EXISTS);
		}
		return identity;
	}
}
