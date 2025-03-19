package br.com.gravitech.condonews.controller.news;

import br.com.gravitech.condonews.dto.NewsDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.service.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsControllerTest {

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    private NewsDto newsDto;
    private UUID newsId;
    private PageResponseDto pageResponseDto;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        newsId = UUID.randomUUID();
        String currentDate = java.time.LocalDateTime.now().toString();
        newsDto = new NewsDto(
                newsId,
                "Test News",
                "Test Content",
                "test-image.jpg",
                currentDate,
                currentDate,
                true,
                UUID.randomUUID()
        );

        pageable = PageRequest.of(0, 10);
        pageResponseDto = new PageResponseDto(
            List.of(newsDto),
            0,
            10,
            1,
            1
        );
    }

    @Test
    @DisplayName("Should return paginated news when getNews is called")
    void shouldReturnPaginatedNewsWhenGetNewsIsCalled() {
        when(newsService.findAllNews(any(Pageable.class))).thenReturn(pageResponseDto);

        PageResponseDto result = newsController.getNews(pageable);

        assertNotNull(result);
        assertEquals(1, result.totalPages());
        assertEquals(1, result.totalElements());
        assertEquals(1, result.content().size());
        verify(newsService).findAllNews(pageable);
    }

    @Test
    @DisplayName("Should return news by id when getNews with id is called")
    void shouldReturnNewsByIdWhenGetNewsWithIdIsCalled() {
        when(newsService.findNewsById(newsId)).thenReturn(newsDto);

        NewsDto result = newsController.getNews(newsId);

        assertNotNull(result);
        assertEquals(newsId, result.id());
        assertEquals("Test News", result.title());
        verify(newsService).findNewsById(newsId);
    }

    @Test
    @DisplayName("Should return paginated breaking news when getBreakingNews is called")
    void shouldReturnPaginatedBreakingNewsWhenGetBreakingNewsIsCalled() {
        when(newsService.findAllBreakingNews(any(Pageable.class))).thenReturn(pageResponseDto);

        PageResponseDto result = newsController.getBreakingNews(pageable);

        assertNotNull(result);
        assertEquals(1, result.totalPages());
        assertEquals(1, result.totalElements());
        verify(newsService).findAllBreakingNews(pageable);
    }

    @Test
    @DisplayName("Should create news when createNews is called")
    void shouldCreateNewsWhenCreateNewsIsCalled() {
        when(newsService.createNews(any(NewsDto.class))).thenReturn(newsDto);

        NewsDto result = newsController.createNews(newsDto);

        assertNotNull(result);
        assertEquals(newsId, result.id());
        assertEquals("Test News", result.title());
        verify(newsService).createNews(newsDto);
    }

    @Test
    @DisplayName("Should update news when updateNews is called")
    void shouldUpdateNewsWhenUpdateNewsIsCalled() {
        when(newsService.updateNews(any(NewsDto.class))).thenReturn(newsDto);

        NewsDto result = newsController.updateNews(newsDto);

        assertNotNull(result);
        assertEquals(newsId, result.id());
        assertEquals("Test News", result.title());
        verify(newsService).updateNews(newsDto);
    }

    @Test
    @DisplayName("Should delete news when deleteNews is called")
    void shouldDeleteNewsWhenDeleteNewsIsCalled() {
        doNothing().when(newsService).deleteNews(newsId);

        newsController.deleteNews(newsId);

        verify(newsService).deleteNews(newsId);
    }
}
