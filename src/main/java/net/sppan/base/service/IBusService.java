package net.sppan.base.service;

import net.sppan.base.entity.Bus;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBusService extends IBaseService<Bus,Integer> {
    void saveOrUpdate(Bus bus,Integer schoolId);

    Page<Bus> findAll(PageRequest pageable);
    Page<Driver> findAllByLike(String searchText, PageRequest pageRequest);
    Page<Bus> findById(List<Integer> id,PageRequest pageRequest);
}
