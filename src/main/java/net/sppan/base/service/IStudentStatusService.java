package net.sppan.base.service;

import net.sppan.base.entity.StudentStatus;
import net.sppan.base.service.support.IBaseService;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface IStudentStatusService extends IBaseService<StudentStatus,Integer> {
    @Override
    List<StudentStatus> findList(Iterable<Integer> integers);

    Page<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date Date, PageRequest pageRequest);
    List<StudentStatus> findAllByBusIdAndTakeTime(Integer id, Date Date);
    public void saveOrUpdate(StudentStatus studentStatus);
    List<StudentStatus> findByBusIdBetweenDate(Integer id,Date startDate,Date endDate);

}
