package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import net.sppan.base.entity.BusInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBusInfoDao extends IBaseDao<BusInfo,Integer> {

    @Override
    Page<BusInfo> findAll(Pageable pageable);
    List<BusInfo> findAllByBusId(Integer id);
    Page<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date,Pageable pageable);

    @Override
    List<BusInfo> findAll(Specification<BusInfo> specification);
    List<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date);
    List<BusInfo>  findAllByBusIdAndCreateTimeBetweenOrderByCreateTime(Integer id,Date startDate,Date endDate);
}
