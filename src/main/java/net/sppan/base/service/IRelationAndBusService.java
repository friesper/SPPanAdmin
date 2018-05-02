package net.sppan.base.service;

import net.sppan.base.entity.RelationOfSchoolAndBus;
import net.sppan.base.service.support.IBaseService;

import java.util.List;

public interface IRelationAndBusService extends IBaseService<RelationOfSchoolAndBus ,Integer> {

    List<RelationOfSchoolAndBus> findBySchoolId(Integer id);
    void deleteAllBySchoolId(Integer id);
    void deleteAllByBusid(Integer id);
    RelationOfSchoolAndBus findByBusId(Integer id);

}
