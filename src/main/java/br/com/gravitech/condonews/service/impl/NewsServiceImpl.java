package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.News;
import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.exception.news.NewsNotFoundException;
import br.com.gravitech.condonews.mapper.NewsMapper;
import br.com.gravitech.condonews.mapper.PageMapper;
import br.com.gravitech.condonews.repository.NewsRepository;
import br.com.gravitech.condonews.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsMapper newsMapper;
    private final PageMapper pageMapper;
    private final NewsRepository newsRepository;

    @Override
    public PageResponseDto findAllNews(Pageable pageable) {
        log.info("Starting findAllNews method {}", pageable);
        return pageMapper.toResponseDto(newsRepository.findAll(pageable));
    }

    @Override
    public NewsDto findNewsById(UUID id) {
        log.info("Starting findNewsById method {}", id);
        return newsMapper.toDto(newsRepository.findById(id).orElseThrow(NewsNotFoundException::new));
    }

    @Override
    public PageResponseDto findAllBreakingNews(Pageable pageable) {
        log.info("Starting findAllBreakingNews method {}", pageable);
        return pageMapper.toResponseDto(newsRepository.findByBreakingIsTrue(pageable));
    }

    @Override
    @Transactional
    public NewsDto createNews(NewsDto news) {
        log.info("Starting createNews method {}", news.title());
        News savedNews = newsRepository.save(newsMapper.toEntity(news));
        return newsMapper.toDto(savedNews);
    }

    @Override
    @Transactional
    public NewsDto updateNews(NewsDto news) {
        log.info("Starting updateNews method {}", news.id());
        News entity = newsRepository.findById(news.id()).orElseThrow(NewsNotFoundException::new);
        newsMapper.merge(news, entity);
        return newsMapper.toDto(newsRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteNews(UUID id) {
        log.info("Starting deleteNews method {}", id);
        NewsDto news = findNewsById(id);
        newsRepository.deleteById(news.id());
    }
}
