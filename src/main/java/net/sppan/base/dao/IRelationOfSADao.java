package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationOfSADao extends IBaseDao<RelationOfSA,Integer> {


        List<RelationOfSA> findBySchoolId(Integer id);
        void deleteAllBySchoolId(Integer id);
        void deleteAllByNurseId(Integer id);
}
