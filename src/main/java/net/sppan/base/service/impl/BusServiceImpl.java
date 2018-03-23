package net.sppan.base.service.impl;

import net.sppan.base.dao.IBusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.RelationOfSchoolAndBus;
import net.sppan.base.service.IBusService;
import net.sppan.base.service.IRelationAndBusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


@Service
public class BusServiceImpl extends BaseServiceImpl<Bus,Integer> implements IBusService {

    @Autowired
    IBusService iBusService;
    @Autowired
    IRelationAndBusService relationAndBusService;
    @Autowired
    IBusDao busDao;
    @Override
    public IBaseDao<Bus, Integer> getBaseDao() {
        return busDao;
    }

    @Override
    public void saveOrUpdate(Bus  bus) {
        if (bus.getId()!=null){
            Bus dbBus=find(bus.getId());
            dbBus.setNumber(bus.getNumber());
            update(dbBus);
        }
        else {
            save(bus);
            RelationOfSchoolAndBus relationOfSchoolAndBus=new RelationOfSchoolAndBus();
            relationAndBusService.save(relationOfSchoolAndBus);
        }


    }

    @Override
    public Page<Driver> findAllByLike(String searchText, PageRequest pageRequest) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Bus bus = find(id);
        super.delete(id);
        relationAndBusService.deleteAllByBusid( id);
    }
}
