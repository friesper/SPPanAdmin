package net.sppan.base.service.impl;

import net.sppan.base.dao.IStudentStatusDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.StudentStatus;
import net.sppan.base.service.IStudentStatusService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        public Page<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date date, PageRequest pageRequest){
               return studentStatusDao.findAllByBusIdAndTakeTime(id,date,pageRequest);
        }

    @Override
    public List<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date date) {
        return studentStatusDao.findAllByBusIdAndTakeTime(id,date);
    }

    @Override
    public void saveOrUpdate(StudentStatus studentStatus) {
            studentStatusDao.save(studentStatus);
    }

    @Override
    public List<StudentStatus> findByBusIdBetweenDate(Integer id, Date startDate, Date endDate) {
        return studentStatusDao.findAllByBusIdAndTakeTimeBetween(id,startDate,endDate);
    }
}
