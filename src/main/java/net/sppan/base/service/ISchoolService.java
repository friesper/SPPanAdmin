package net.sppan.base.service;

import net.sppan.base.entity.School;
import net.sppan.base.service.support.IBaseService;

public interface ISchoolService extends IBaseService<School,Integer> {
    void saveOrUpdate(School school);

}
