package net.sppan.base.service.impl;

import net.sppan.base.Application;
import net.sppan.base.dao.IDriverDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.service.IDriverService;
import net.sppan.base.service.ISchoolService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class DriverServiceImpl extends BaseServiceImpl<Driver, Integer>
        implements IDriverService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IDriverDao iDriverDao;
    @Autowired
    ISchoolService schoolService;
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
            dbDriver.setWorkUnitName(driver.getWorkUnitName());
            update(dbDriver);
        }
        else {
            save(driver);
        }

    }
    @Override
    public Page<Driver> findByWorkUnitId(Integer id,PageRequest pageRequest){
            return null;
    }

    public Page<Driver> findById(HashSet<Integer> ids, PageRequest pageRequest) {

         List<Driver> list=iDriverDao.findAll(ids);
         Page<Driver> driverPage=new PageImpl<Driver>(list,pageRequest,list.size());
         return  driverPage;
    }


    @Override
    public IBaseDao<Driver, Integer> getBaseDao() {
        return this.iDriverDao;
    }
}
