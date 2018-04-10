package net.sppan.base.service;

import net.sppan.base.entity.RlationOFSD;
import net.sppan.base.service.support.IBaseService;

import java.util.List;

public interface IRlationOFSDService extends IBaseService<RlationOFSD, Integer> {

    @Override
    RlationOFSD find(Integer integer);

    @Override
    List<RlationOFSD> findList(Iterable<Integer> integers);

    List<RlationOFSD> findByDriverId(Integer id);
    List<RlationOFSD> findBySchoolId(Integer id);
    void deleteByDriverId(Integer id);
    public void deleteAllByDriverId(Integer id);
    void  deleteAllBySchoolId(Integer id);
}
