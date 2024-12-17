package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.repository.NewsRepository;
import br.com.gravitech.condonews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

}
