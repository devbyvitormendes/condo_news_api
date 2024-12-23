package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NewsService {
    PageResponseDto findAllNews(Pageable pageable);

    NewsDto findNewsById(UUID id);

    PageResponseDto findAllBreakingNews(Pageable pageable);

    NewsDto createNews(NewsDto news);

    NewsDto updateNews(NewsDto news);

    void deleteNews(UUID id);
}
