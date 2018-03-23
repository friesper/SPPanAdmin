package net.sppan.base.service.impl;

import net.sppan.base.Application;
import net.sppan.base.dao.IRelationOfSchoolAndNurseDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSchoolAndNurse;
import net.sppan.base.service.IRelationOfSchoolAndNurseService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationOfSchoolAndNurseImpl extends BaseServiceImpl<RelationOfSchoolAndNurse,Integer> implements IRelationOfSchoolAndNurseService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IRelationOfSchoolAndNurseDao relationOfSchoolAndNurseDao;
    @Override
    public IBaseDao<RelationOfSchoolAndNurse, Integer> getBaseDao() {
        return relationOfSchoolAndNurseDao;
    }
    public List<RelationOfSchoolAndNurse> findNurseBySchoolId(Integer id) {
        List<RelationOfSchoolAndNurse> list= relationOfSchoolAndNurseDao.findBySchoolId(id);
        for (int i=0;i<list.size();i++){
            logger.debug("dasd               ++++++"+list.get(i).toString());
        }
        return  list ;
    }
    public List<RelationOfSchoolAndNurse> findBySchoolId(Integer id) {
        List<RelationOfSchoolAndNurse> list= relationOfSchoolAndNurseDao.findBySchoolId(id);
        for (int i=0;i<list.size();i++){
            logger.debug("dasd               ++++++"+list.get(i).toString());
        }
        return  list ;
    }

    @Override
    public void saveOrUpdate(RelationOfSchoolAndNurse relationOfSchoolAndNurse) {
        logger.debug(relationOfSchoolAndNurse.toString());
        if (relationOfSchoolAndNurse.getId()!=null){
            RelationOfSchoolAndNurse  dbRelationOfSchoolAndNurse=find(relationOfSchoolAndNurse.getId());
            dbRelationOfSchoolAndNurse.setSchoolId(relationOfSchoolAndNurse.getSchoolId());
            dbRelationOfSchoolAndNurse.setNurseId(relationOfSchoolAndNurse.getNurseId());
            dbRelationOfSchoolAndNurse.setDriverId(relationOfSchoolAndNurse.getDriverId());
            dbRelationOfSchoolAndNurse.setBusId(relationOfSchoolAndNurse.getBusId());
            update(dbRelationOfSchoolAndNurse);
        }
        else {
            save(relationOfSchoolAndNurse);
        }
    }

    @Override
    public List<RelationOfSchoolAndNurse> findNurseBySchoolId(Iterable<Integer> integers) {
        return null;
    }
}
