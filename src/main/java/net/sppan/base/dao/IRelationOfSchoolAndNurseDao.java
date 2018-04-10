package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSchoolAndNurse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationOfSchoolAndNurseDao extends IBaseDao<RelationOfSchoolAndNurse,Integer> {
    public  List<RelationOfSchoolAndNurse> findByNurseId(Integer id);
    public List<RelationOfSchoolAndNurse> findBySchoolId(Integer id);
    void  deleteAllBySchoolId(Integer id);
    void  deleteAllByNurseId(Integer id);
    void deleteAllByDriverId(Integer id);
}
