package net.sppan.base.service.impl;

import net.sppan.base.dao.IStudentStatusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.StudentStatus;
import net.sppan.base.service.IStudentStatusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentStatusServiceImpl extends BaseServiceImpl<StudentStatus,Integer> implements IStudentStatusService {

    @Autowired
    IStudentStatusDao studentStatusDao;

    @Override
    public IBaseDao<StudentStatus, Integer> getBaseDao() {
        return studentStatusDao;
    }
}
