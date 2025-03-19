package br.com.gravitech.condonews.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.DashboardDto;
import br.com.gravitech.condonews.dto.DashboardItemDto;
import br.com.gravitech.condonews.repository.NewsRepository;
import br.com.gravitech.condonews.repository.UserRepository;
import br.com.gravitech.condonews.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public DashboardDto getDashboardData(UUID id) {
        log.info(StringConstants.Log.Dashboard.FETCHING_DASHBOARD, id);
        List<DashboardItemDto> items = new ArrayList<>();

        // Total news count
        long newsCount = newsRepository.countByIdCondo(id);
        log.info(StringConstants.Log.Dashboard.DASHBOARD_TOTAL_NEWS, newsCount);
        items.add(new DashboardItemDto("Total News", String.valueOf(newsCount)));

        // Breaking news count
        long breakingNewsCount = newsRepository.countByIdCondoAndBreakingNewsTrue(id);
        log.info(StringConstants.Log.Dashboard.DASHBOARD_BREAKING_NEWS, breakingNewsCount);
        items.add(new DashboardItemDto("Breaking News", String.valueOf(breakingNewsCount)));

        // Active users count
        long activeUsersCount = userRepository.countByIdCondoAndActiveTrue(id);
        log.info(StringConstants.Log.Dashboard.DASHBOARD_ACTIVE_USERS, activeUsersCount);
        items.add(new DashboardItemDto("Active Users", String.valueOf(activeUsersCount)));

        log.info(StringConstants.Log.ENDING_METHOD, "getDashboardData");
        return new DashboardDto(items);
    }
}
