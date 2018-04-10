package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RlationOFSD;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IRlationOFSDDao extends IBaseDao<RlationOFSD,Integer> {

    public List<RlationOFSD> findByDriverId(Integer id);
    public  List<RlationOFSD> findByDriverIdEquals(Integer id);
    public List<RlationOFSD> findBySchoolId(Integer id);
    public void deleteByDriverId(Integer integer);
    public void deleteAllByDriverId(Integer id);
    void deleteAllBySchoolId(Integer id);
}
