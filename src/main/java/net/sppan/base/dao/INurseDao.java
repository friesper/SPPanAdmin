package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INurseDao extends IBaseDao<Nurse,Integer> {
    Page<Nurse> findDriverByWorkUnitId(Integer id, Pageable pageable);

    @Override
    List<Nurse> findAll(Iterable<Integer> iterable);
    List<Nurse>  findByWorkUnitId(Integer id);
}
