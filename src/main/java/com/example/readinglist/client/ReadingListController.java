package com.example.readinglist.client;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.readinglist.Book;
import com.example.readinglist.dataAccess.ReadingListRepository;

@Controller
@RequestMapping("/")
public class ReadingListController {

	@Autowired
	private ReadingListRepository readingListRepository;

	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBook(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (CollectionUtils.isNotEmpty(readingList)) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
}
