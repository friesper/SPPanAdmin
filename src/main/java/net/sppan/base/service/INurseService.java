package net.sppan.base.service;

import net.sppan.base.entity.Nurse;
import net.sppan.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.List;

public interface INurseService extends IBaseService<Nurse,Integer> {
    public void saveOrUpdate(Nurse nurse) ;
    public Page<Nurse> findById(HashSet<Integer> ids, PageRequest pageRequest) ;
    List<Nurse> findBySchoolId(Integer id);
    Nurse  findNurseByUserName(String name);
}
