package net.sppan.base.service;

import net.sppan.base.entity.RelationOfSchoolAndNurse;
import net.sppan.base.service.support.IBaseService;

import java.util.List;

public interface IRelationOfSchoolAndNurseService extends IBaseService<RelationOfSchoolAndNurse,Integer> {

    @Override
    List<RelationOfSchoolAndNurse> findList(Iterable<Integer> integers);
    List<RelationOfSchoolAndNurse> findNurseBySchoolId(Iterable<Integer> integers);
    List<RelationOfSchoolAndNurse> findNurseBySchoolId(Integer id);
    List<RelationOfSchoolAndNurse> findBySchoolId(Integer id);
    List<RelationOfSchoolAndNurse> findByNurseId(Integer id);

    void saveOrUpdate(RelationOfSchoolAndNurse relationOfSchoolAndNurse);

    void  deleteAllBySchoolId(Integer id);
    void  deleteAllByNurseId(Integer id);
    void deleteAllByDriverId(Integer id);
}
