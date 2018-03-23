package net.sppan.base.service.impl;

import net.sppan.base.dao.IRelationAndBusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSchoolAndBus;
import net.sppan.base.service.IRelationAndBusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationAndBusServiceImpl extends BaseServiceImpl<RelationOfSchoolAndBus,Integer> implements IRelationAndBusService {
    @Autowired
    IRelationAndBusDao relationAndBusDao;

    @Override
    public IBaseDao<RelationOfSchoolAndBus, Integer> getBaseDao() {
        return relationAndBusDao;
    }
    @Override
    public List<RelationOfSchoolAndBus> findBySchoolId(Integer id){
        return relationAndBusDao.findBySchoolId(id);

    }

    @Override
    public void deleteAllBySchoolId(Integer id) {
        relationAndBusDao.deleteAllBySchoolId(id);
    }

    @Override
    public void deleteAllByBusid(Integer id) {
            relationAndBusDao.deleteAllByBusId(id);
    }
}
