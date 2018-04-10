package net.sppan.base.service;

import net.sppan.base.entity.Bus;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.service.support.IBaseService;

import java.util.Date;
import java.util.List;

public interface IBusInfoService extends IBaseService<BusInfo,Integer> {

    List<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date);
}
