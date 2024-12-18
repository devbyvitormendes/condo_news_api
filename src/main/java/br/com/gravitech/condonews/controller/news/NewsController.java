package br.com.gravitech.condonews.controller.news;

import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/news")
public class NewsController implements NewsApi {

    private final NewsService newsService;

    @Override
    @GetMapping
    public List<NewsDto> getNews() {
        return newsService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public NewsDto getNews(@PathVariable UUID id) {
        return newsService.findNewsById(id);
    }

    @Override
    @GetMapping("/breaking")
    public List<NewsDto> getBreakingNews() {
        return newsService.findAllBreakingNews();
    }

    @Override
    @PostMapping
    public void createNews(@RequestBody NewsDto news) {
        newsService.createNews(news);
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

