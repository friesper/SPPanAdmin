package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RelationOfSchoolAndBus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRelationAndBusDao  extends IBaseDao<RelationOfSchoolAndBus,Integer>{
    public List<RelationOfSchoolAndBus> findAllBySchoolId(Integer id);
    public  void deleteAllByBusId(Integer id);
    public  void  deleteAllBySchoolId(Integer id);
}
