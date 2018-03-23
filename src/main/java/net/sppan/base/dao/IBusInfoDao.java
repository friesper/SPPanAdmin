package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import net.sppan.base.entity.BusInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusInfoDao extends IBaseDao<BusInfo,Integer> {

    @Override
    Page<BusInfo> findAll(Pageable pageable);
    List<BusInfo> findAllByBusId(Integer id);
    @Override
    List<BusInfo> findAll(Specification<BusInfo> specification);
}
