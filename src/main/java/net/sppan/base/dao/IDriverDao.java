package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface IDriverDao extends IBaseDao<Driver,Integer> {

    Page<Driver> findByWorkUnitId(Integer id);

}
