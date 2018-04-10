package net.sppan.base.service.impl;

import net.sppan.base.Application;
import net.sppan.base.dao.IDriverDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.RlationOFSD;
import net.sppan.base.service.*;
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
    IRlationOFSDService rlationOFSDService;
    @Autowired
    IBusService busService;
    @Autowired
    ISchoolService schoolService;
    @Autowired
    IDriverService iDriverServicel;
    @Autowired
    IRelationOfSchoolAndNurseService relationOfSchoolAndNurseService;
    @Override
    public void saveOrUpdate(Driver driver) {
        if (driver.getId()!=null){
            Driver dbDriver=find(driver.getId());
            dbDriver.setName(driver.getName());
            dbDriver.setBusId(driver.getBusId());
            dbDriver.setBusNumber(busService.find(driver.getBusId()).getNumber());
            dbDriver.setDriverImage(driver.getDriverImage());
            dbDriver.setPhone(driver.getPhone());
            dbDriver.setWorkUnitId(driver.getWorkUnitId());
            dbDriver.setWorkUnitName(schoolService.find(driver.getWorkUnitId()).getName());
            dbDriver.setUserName(driver.getUserName());
            dbDriver.setPassWord(driver.getPassWord());
            update(dbDriver);
        }
        else {
            driver.setWorkUnitName(schoolService.find(driver.getWorkUnitId()).getName());
            driver.setBusNumber(busService.find(driver.getBusId()).getNumber());
            save(driver);
            RlationOFSD rlationOFSD=new RlationOFSD();
            rlationOFSD.setDriverId(driver.getId());
            rlationOFSD.setSchoolId(driver.getWorkUnitId());
            logger.debug("driverserviceOmpl++++++++"+driver.toString());
            rlationOFSDService.save(rlationOFSD);
        }


    }

    @Override
    public void delete(Integer integer) {
        iDriverDao.delete(integer);
        rlationOFSDService.deleteAllByDriverId(integer);
        relationOfSchoolAndNurseService.deleteAllByDriverId(integer);
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
    public Driver findDriverByName(String name) {
        return iDriverDao.findDriverByName(name);
    }

    @Override
    public Driver findDriverByUserName(String name) {
        return iDriverDao.findDriverByUserName(name);
    }


    @Override
    public IBaseDao<Driver, Integer> getBaseDao() {
        return this.iDriverDao;
    }
}
