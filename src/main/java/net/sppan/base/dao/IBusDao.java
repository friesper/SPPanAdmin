package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusDao extends IBaseDao<Bus,Integer> {

    @Override
    Page<Bus> findAll(Pageable pageable);
    Page<Bus> findByIdIn(List<Integer> id,Pageable pageable);
}
