package net.sppan.base.service;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.School;

public interface ISchoolService extends IBaseDao<School,Integer> {
    void saveOrUpdate(School school);

}
