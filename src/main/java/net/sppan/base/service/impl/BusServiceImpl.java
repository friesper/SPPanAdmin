package net.sppan.base.service.impl;

import net.sppan.base.dao.IBusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Bus;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.IBusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


@Service
public class BusServiceImpl extends BaseServiceImpl<Bus,Integer> implements IBusService {

    @Autowired
    IBusService iBusService;
    @Override
    public IBaseDao<Bus, Integer> getBaseDao() {
        return null;
    }

    @Override
    public Bus findBySchool(Integer schoolId) {
        return null;
    }

    @Override
    public void saveOrUpdate(Bus  bus) {
        if (bus.getId()!=null){
            Bus dbBus=find(bus.getId());
            dbBus.setNumber(bus.getNumber());
            update(dbBus);
        }
        else {save(bus);}


    }

    @Override
    public Page<Driver> findAllByLike(String searchText, PageRequest pageRequest) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Bus bus = find(id);
        super.delete(id);
    }
}
