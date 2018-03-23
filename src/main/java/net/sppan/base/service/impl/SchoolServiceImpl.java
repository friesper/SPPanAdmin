package net.sppan.base.service.impl;

import net.sppan.base.dao.ISchoolDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.School;
import net.sppan.base.service.ISchoolService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SchoolServiceImpl extends BaseServiceImpl<School,Integer> implements ISchoolService{

    @Autowired
    private ISchoolDao schoolDao;
    @Autowired
    private ISchoolService schoolService;
    @Override
    public void saveOrUpdate(School school) {

    }

    @Override
    public IBaseDao<School, Integer> getBaseDao() {
        return this.schoolDao;
    }

    public Page<School> findById(HashSet<Integer> ids, PageRequest pageRequest) {

        List<School> list=schoolDao.findAll(ids);
        Page<School> driverPage=new PageImpl<School>(list,pageRequest,list.size());
        return  driverPage;
    }

}
