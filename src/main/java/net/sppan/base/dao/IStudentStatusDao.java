package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.StudentStatus;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IStudentStatusDao extends IBaseDao<StudentStatus,Integer>  {
    List<StudentStatus> findAllByBusId(Integer id);
    List<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date date);

}
