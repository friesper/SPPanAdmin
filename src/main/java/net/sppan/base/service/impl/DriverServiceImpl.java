package net.sppan.base.service.impl;

import net.sppan.base.dao.IDriverDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.IDriverService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl extends BaseServiceImpl<Driver, Integer>
        implements IDriverService {

    @Autowired
    IDriverDao iDriverDao;
    @Autowired
    IDriverService iDriverServicel;
    @Override
    public void saveOrUpdate(Driver driver) {
        if (driver.getId()!=null){
            Driver dbDriver=find(driver.getId());
            dbDriver.setName(driver.getName());
            dbDriver.setBusId(driver.getBusId());
            dbDriver.setDriverImage(driver.getDriverImage());
            dbDriver.setPhone(driver.getPhone());
            dbDriver.setWorkUnitId(driver.getWorkUnitId());
            update(dbDriver);
        }
        else {
            save(driver);

        }

    }
    @Override
    public Page<Driver> findByWorkUnitId(Integer id){
        return iDriverDao.findByWorkUnitId(id);


    }

    @Override
    public IBaseDao<Driver, Integer> getBaseDao() {
        return this.iDriverDao;
    }
}
