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
        return List.of(
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID())
        );
    }

    @Override
    @GetMapping("/{id}")
    public NewsDto getNews(@PathVariable UUID id) {
        return null;
    }

    @Override
    @GetMapping("/breaking")
    public List<NewsDto> getBreakingNews() {
        return List.of(
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID()),
                new NewsDto(UUID.randomUUID(), "title", "content", "imageBase64", "date", true, UUID.randomUUID())
        );
    }

    @Override
    @PostMapping
    public void createNews(@RequestBody NewsDto customer) {

    }

    @Override
    @PutMapping
    public NewsDto updateNews(@RequestBody NewsDto customer) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteNews(UUID id) {

    }
}

