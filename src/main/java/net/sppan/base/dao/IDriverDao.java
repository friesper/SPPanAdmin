package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDriverDao extends IBaseDao<Driver,Integer> {

    Page<Driver> findDriverByWorkUnitId(Integer id,Pageable pageable);

    @Override
    List<Driver> findAll(Iterable<Integer> iterable);
}
