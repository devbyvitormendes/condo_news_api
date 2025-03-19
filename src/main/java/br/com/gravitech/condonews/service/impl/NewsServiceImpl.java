package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.News;
import br.com.gravitech.condonews.domain.utils.StringConstants;
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
        log.info(StringConstants.Log.News.FINDING_ALL_NEWS);
        var result = pageMapper.toResponseDto(newsRepository.findAll(pageable));
        log.info(StringConstants.Log.ENDING_METHOD, "findAllNews");
        return result;
    }

    @Override
    public NewsDto findNewsById(UUID id) {
        log.info(StringConstants.Log.News.FINDING_NEWS, id);
        var result = newsMapper.toDto(newsRepository.findById(id).orElseThrow(NewsNotFoundException::new));
        log.info(StringConstants.Log.ENDING_METHOD, "findNewsById");
        return result;
    }

    @Override
    public PageResponseDto findAllBreakingNews(Pageable pageable) {
        log.info(StringConstants.Log.News.FINDING_BREAKING_NEWS);
        var result = pageMapper.toResponseDto(newsRepository.findByBreakingIsTrue(pageable));
        log.info(StringConstants.Log.ENDING_METHOD, "findAllBreakingNews");
        return result;
    }

    @Override
    @Transactional
    public NewsDto createNews(NewsDto news) {
        log.info(StringConstants.Log.News.CREATING_NEWS, news.title());
        News savedNews = newsRepository.save(newsMapper.toEntity(news));
        var result = newsMapper.toDto(savedNews);
        log.info(StringConstants.Log.ENDING_METHOD, "createNews");
        return result;
    }

    @Override
    @Transactional
    public NewsDto updateNews(NewsDto news) {
        log.info(StringConstants.Log.News.UPDATING_NEWS, news.id());
        News entity = newsRepository.findById(news.id()).orElseThrow(NewsNotFoundException::new);
        newsMapper.merge(news, entity);
        var result = newsMapper.toDto(newsRepository.save(entity));
        log.info(StringConstants.Log.ENDING_METHOD, "updateNews");
        return result;
    }

    @Override
    @Transactional
    public void deleteNews(UUID id) {
        log.info(StringConstants.Log.News.DELETING_NEWS, id);
        NewsDto news = findNewsById(id);
        newsRepository.deleteById(news.id());
        log.info(StringConstants.Log.ENDING_METHOD, "deleteNews");
    }
}
