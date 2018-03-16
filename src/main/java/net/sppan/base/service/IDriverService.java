package net.sppan.base.service;

import net.sppan.base.entity.Driver;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;

public interface IDriverService extends IBaseService<Driver,Integer> {
    void saveOrUpdate(Driver driver);
    Page<Driver> findByWorkUnitId(Integer id);
}
