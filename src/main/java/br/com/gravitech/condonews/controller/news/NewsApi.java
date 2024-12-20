package br.com.gravitech.condonews.controller.news;

import br.com.gravitech.condonews.dto.NewsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "News", description = "News related operations")
public interface NewsApi {

    @Operation(summary = "Get News", description = "Get a list of all News.")
    List<NewsDto> getNews();

    @Operation(summary = "Get News by ID", description = "Get a News by UUID.")
    NewsDto getNews(@Parameter(description = "News' UUID", required = true) @PathVariable UUID id);

    @Operation(summary = "Get Breaking News", description = "Get a list of the Breaking News.")
    List<NewsDto> getBreakingNews();

    @Operation(summary = "Create News", description = "Create a new News in database.")
    NewsDto createNews(@RequestBody NewsDto news);

    @Operation(summary = "Update News", description = "Update News data in database.")
    NewsDto updateNews(@Parameter(description = "News data", required = true) @RequestBody NewsDto news);

    @Operation(summary = "Delete News", description = "Delete a News in database.")
    void deleteNews(@Parameter(description = "News' UUID", required = true) @PathVariable UUID id);
}
