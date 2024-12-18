package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.NewsDto;

import java.util.List;
import java.util.UUID;

public interface NewsService {
    List<NewsDto> findAll();

    NewsDto findNewsById(UUID id);

    List<NewsDto> findAllBreakingNews();

    void createNews(NewsDto news);

    NewsDto updateNews(NewsDto news);

    void deleteNews(UUID id);
}
