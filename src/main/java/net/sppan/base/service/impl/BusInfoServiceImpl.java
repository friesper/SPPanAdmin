package net.sppan.base.service.impl;

import net.sppan.base.dao.IBusInfoDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.BusInfo;
import net.sppan.base.service.IBusInfoService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class BusInfoServiceImpl extends BaseServiceImpl<BusInfo,Integer> implements IBusInfoService {
    @Autowired
    IBusInfoDao iBusInfoDao;

    @Override
    public IBaseDao<BusInfo, Integer> getBaseDao() {
        return iBusInfoDao;
    }
}
