package com.example.news;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsApiController {
	final NewsDAO dao;

	@Autowired
	public NewsApiController(NewsDAO dao) {
		this.dao = dao;
	}

	@PostMapping
	public String addNews(@RequestBody News news) {
		try {
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!!";
		}

		return "News API: 뉴스 등록됨!!";
	}

	@DeleteMapping("{aid}")
	public String delNews(@PathVariable("aid") int aid) {
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			return "News API: 뉴스 삭제 실패!! - " + aid;
		}
		return "News API: 뉴스 삭제됨!! - " + aid;
	}

	public List<News> getNewsList() {
		List<News> newsList = null;

		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newsList;
	}

}
