package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.News;
import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.exception.news.NewsNotFoundException;
import br.com.gravitech.condonews.mapper.NewsMapper;
import br.com.gravitech.condonews.repository.NewsRepository;
import br.com.gravitech.condonews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;
    private final NewsRepository newsRepository;

    @Override
    public List<NewsDto> findAll() {
        return newsMapper.toDtoList(newsRepository.findAll());
    }

    @Override
    public NewsDto findNewsById(UUID id) {
        return newsMapper.toDto(newsRepository.findById(id).orElseThrow(NewsNotFoundException::new));
    }

    @Override
    public List<NewsDto> findAllBreakingNews() {
        return newsMapper.toDtoList(newsRepository.findByBreakingIsTrue());
    }

    @Override
    @Transactional
    public NewsDto createNews(NewsDto news) {
        News savedNews = newsRepository.save(newsMapper.toEntity(news));
        return newsMapper.toDto(savedNews);
    }

    @Override
    @Transactional
    public NewsDto updateNews(NewsDto news) {
        News entity = newsRepository.findById(news.getId()).orElseThrow(NewsNotFoundException::new);
        newsMapper.merge(news, entity);
        return newsMapper.toDto(newsRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteNews(UUID id) {
        NewsDto news = findNewsById(id);
        newsRepository.deleteById(news.getId());
    }
}
