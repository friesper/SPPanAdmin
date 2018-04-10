package net.sppan.base.service;

import net.sppan.base.entity.RelationOfSA;
import net.sppan.base.service.support.IBaseService;

import java.util.List;

public interface IRelationOfSAService extends IBaseService<RelationOfSA,Integer> {
     List<RelationOfSA> findNurseBySchoolId(Integer id);
     void  deleteAllByNurseId(Integer id);
     void deleteAllBySchoolId(Integer id);


}
