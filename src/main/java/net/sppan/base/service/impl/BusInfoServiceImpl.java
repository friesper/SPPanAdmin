package net.sppan.base.service.impl;

import net.sppan.base.dao.IBusInfoDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.service.IBusInfoService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusInfoServiceImpl extends BaseServiceImpl<BusInfo,Integer> implements IBusInfoService {
    @Autowired
    IBusInfoDao iBusInfoDao;

    @Override
    public IBaseDao<BusInfo, Integer> getBaseDao() {
        return iBusInfoDao;
    }

    @Override
    public List<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date) {
        return iBusInfoDao.findAllByBusIdAndCreateTime(id,date);
    }

    @Override
    public Page<BusInfo> findAllByBusIdAndCreateTime(Integer id, Date date, PageRequest pageRequest) {
        return iBusInfoDao.findAllByBusIdAndCreateTime(id,date,pageRequest);
    }

    @Override
    public List<BusInfo> findAllByBusIdAndCreateTimeBetween(Integer id, Date startDate, Date endDate) {
        return iBusInfoDao.findAllByBusIdAndCreateTimeBetweenOrderByCreateTime(id,startDate,endDate);
    }

    public void saveOrUpdate(BusInfo  busInfo) {
        if (busInfo.getId()!=null){
            BusInfo dbBusInfo=find(busInfo.getId());
        }
        else {
            save(busInfo);
        }


    }
}
