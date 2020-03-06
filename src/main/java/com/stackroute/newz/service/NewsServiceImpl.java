package com.stackroute.newz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;


/*
 * This class is implementing the NewsService interface. This class has to be annotated with 
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus 
 * clarifying it's role.
 * 
 * */

@Service
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsRepository.
	 */
	@Autowired
	NewsRepository newsRepo;

	/*
	 * Add a new news. Throw NewsAlreadyExistsException if the news with specified
	 * newsId already exists.
	 */
	public News addNews(News news) throws NewsAlreadyExistsException {
		News newsObj = newsRepo.getOne(news.getNewsId());
		if(newsObj!=null) {
			throw new NewsAlreadyExistsException();
		}else {
			return newsRepo.save(news);
		}
		
	}

	/*
	 * Retrieve an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News getNews(int newsId) throws NewsNotExistsException {
		
		Optional<News> newsObj = newsRepo.findById(newsId);
		if(newsObj.isPresent()) {
			return newsObj.get();
		}else {
			throw new NewsNotExistsException();
		}
		
	}

	/*
	 * Retrieve all existing news
	 */
	public List<News> getAllNews() {

		return newsRepo.findAll();
	}

	
	/*
	 * Update an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public News updateNews(News news) throws NewsNotExistsException {
		News newsObj = newsRepo.getOne(news.getNewsId());
		if(newsObj!=null) {
			return newsRepo.saveAndFlush(news);
		}else {
			throw new NewsNotExistsException();
		}
	}

	/*
	 * Delete an existing news by it's newsId. Throw NewsNotExistsException if the 
	 * news with specified newsId does not exist.
	 */
	public void deleteNews(int newsId) throws NewsNotExistsException {
		News newsObj = newsRepo.getOne(newsId);
		if(newsObj!=null) {
			newsRepo.deleteById(newsId);
		}else {
			throw new NewsNotExistsException();
		}
		
	}

}
