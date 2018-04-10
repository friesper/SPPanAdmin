package net.sppan.base.service;

import net.sppan.base.entity.Bus;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IBusService extends IBaseService<Bus,Integer> {
    void saveOrUpdate(Bus bus,Integer schoolId);

    @Override
    Page<Bus> findAll(Pageable pageable);
    Page<Driver> findAllByLike(String searchText, PageRequest pageRequest);
}
