package net.sppan.base.service;

import net.sppan.base.entity.Driver;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.List;

public interface IDriverService extends IBaseService<Driver,Integer> {
    void saveOrUpdate(Driver driver);
    Page<Driver> findByWorkUnitId(Integer id, PageRequest pageRequest);
    public Page<Driver> findById(HashSet<Integer> ids, PageRequest pageRequest) ;
    Driver findDriverByName(String name);
    Driver findDriverByUserName(String name);
}
