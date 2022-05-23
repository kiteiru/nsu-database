package com.kiteiru.construction.org.services;

import com.kiteiru.construction.org.dto.WorkTypeByBrigadeDto;
import com.kiteiru.construction.org.dto.queries.WorkTypeByBrigadeInPeriodListDTO;
import com.kiteiru.construction.org.entities.Site;
import com.kiteiru.construction.org.entities.WorkTypeByBrigade;
import com.kiteiru.construction.org.mapper.WorkTypeByBrigadeMapper;
import com.kiteiru.construction.org.repositories.WorkTypeByBrigadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkTypeByBrigadeService {
    private final WorkTypeByBrigadeRepository repo;

    private final WorkTypeByBrigadeMapper workTypeByBrigadeMapper;

    @Autowired
    public WorkTypeByBrigadeService(WorkTypeByBrigadeRepository repo, WorkTypeByBrigadeMapper workTypeByBrigadeMapper) {
        this.repo = repo;
        this.workTypeByBrigadeMapper = workTypeByBrigadeMapper;
    }
    public List<WorkTypeByBrigadeDto> getAll() {
        List<WorkTypeByBrigade> workTypeByBrigades = new ArrayList<>(repo.findAll());
        return workTypeByBrigadeMapper.entitiesToDtos(workTypeByBrigades);
    }

    /*
    public List<WorkTypeByBrigadeInPeriodListDTO> getWorkTypeByBrigadeInPeriodList(Integer brigadeIdParam,
                                                                                   Date startDate,
                                                                                   Date endDate,
                                                                                   Integer objectIdParam) {
        return repo.getWorkTypeByBrigadeInPeriodList(brigadeIdParam,
                                                     startDate,
                                                     endDate,
                                                     objectIdParam);
    }*/
}
