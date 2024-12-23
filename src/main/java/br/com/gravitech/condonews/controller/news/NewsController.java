package br.com.gravitech.condonews.controller.news;

import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController implements NewsApi {

    private final NewsService newsService;

    @Override
    @GetMapping
    public PageResponseDto getNews(Pageable pageable) {
        return newsService.findAllNews(pageable);
    }

    @Override
    @GetMapping("/{id}")
    public NewsDto getNews(@PathVariable UUID id) {
        return newsService.findNewsById(id);
    }

    @Override
    @GetMapping("/breaking")
    public PageResponseDto getBreakingNews(Pageable pageable) {
        return newsService.findAllBreakingNews(pageable);
    }

    @Override
    @PostMapping
    public NewsDto createNews(@RequestBody NewsDto news) {
       return newsService.createNews(news);
    }

    @Override
    @PutMapping
    public NewsDto updateNews(@RequestBody NewsDto news) {
        return newsService.updateNews(news);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteNews(UUID id) {
        newsService.deleteNews(id);
    }
}

