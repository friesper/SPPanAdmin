package net.sppan.base.service.impl;

import net.sppan.base.dao.IStudentStatusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.StudentStatus;
import net.sppan.base.service.IStudentStatusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentStatusServiceImpl extends BaseServiceImpl<StudentStatus,Integer> implements IStudentStatusService {

    @Autowired
    IStudentStatusDao studentStatusDao;

    @Override
    public IBaseDao<StudentStatus, Integer> getBaseDao() {
        return studentStatusDao;
    }
        @Override
        public List<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date Date){
               return studentStatusDao.findAllByBusIdAndTakeTime(id,Date);
        }
    @Override
    public void saveOrUpdate(StudentStatus studentStatus) {
            studentStatusDao.save(studentStatus);
    }
}
