package net.sppan.base.service.impl;

import net.sppan.base.dao.IRelationOfSADao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSA;
import net.sppan.base.service.IRelationOfSAService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationOfSAServiceImpl extends BaseServiceImpl <RelationOfSA, Integer>  implements IRelationOfSAService{
    @Autowired
    IRelationOfSADao relationOfSADao;
    @Override
    public IBaseDao<RelationOfSA, Integer> getBaseDao() {
        return relationOfSADao;
    }

    public void saveOrUpdate(RelationOfSA relationOfSA) {
        RelationOfSA dbRelationOfSA=relationOfSADao.findOne(relationOfSA.getId());
        if (dbRelationOfSA!=null){
            dbRelationOfSA.setNurseId(relationOfSA.getNurseId());
            dbRelationOfSA.setSchoolId(relationOfSA.getSchoolId());
            update(dbRelationOfSA);
        }else {
            save(dbRelationOfSA);

        }

    }

    @Override
    public List<RelationOfSA> findNurseBySchoolId(Integer id) {
        return relationOfSADao.findAll();
    }

    @Override
    public void deleteAllByNurseId(Integer id) {
        relationOfSADao.deleteAllByNurseId(id);
    }

    @Override
    public void deleteAllBySchoolId(Integer id) {
        relationOfSADao.deleteAllBySchoolId(id);
    }
}
