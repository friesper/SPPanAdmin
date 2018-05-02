package net.sppan.base.service;

import net.sppan.base.entity.Bus;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface IBusInfoService extends IBaseService<BusInfo,Integer> {

    List<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date);
    Page<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date, PageRequest pageRequest);
    List<BusInfo>  findAllByBusIdAndCreateTimeBetween(Integer id,Date startDate,Date endDate);

}
