package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.StudentStatus;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IStudentStatusDao extends IBaseDao<StudentStatus,Integer>  {
    List<StudentStatus> findAllByBusId(Integer id);
    Page<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date date, Pageable pageable);
    List<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date date);
    List<StudentStatus> findAllByBusIdAndTakeTimeBetween(Integer id,Date startDate,Date endDate);
}
