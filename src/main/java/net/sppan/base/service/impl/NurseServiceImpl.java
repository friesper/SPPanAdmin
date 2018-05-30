package net.sppan.base.service.impl;

import net.sppan.base.dao.INurseDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.Nurse;
import net.sppan.base.entity.RelationOfSA;
import net.sppan.base.service.INurseService;
import net.sppan.base.service.IRelationOfSAService;
import net.sppan.base.service.IRelationOfSchoolAndNurseService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class NurseServiceImpl extends BaseServiceImpl<Nurse,Integer> implements INurseService {

    @Autowired
    INurseDao iNurseDao;
    @Autowired
    IRelationOfSAService relationOfSAService;
    @Autowired
    IRelationOfSchoolAndNurseService relationOfSchoolAndNurseService;
    @Override
    public IBaseDao<Nurse, Integer> getBaseDao() {
        return iNurseDao;
    }
    public Page<Nurse> findById(HashSet<Integer> ids, PageRequest pageRequest) {

        List<Nurse> list=iNurseDao.findAll(ids);
        Page<Nurse> nursesPage=new PageImpl<Nurse>(list,pageRequest,list.size());
        return  nursesPage;
    }

    @Override
    public List<Nurse> findBySchoolId(Integer id) {
        return iNurseDao.findByWorkUnitId(id);
    }

    @Override
    public Nurse findNurseByUserName(String name) {
        return iNurseDao.findNurseByUserName(name);
    }

    @Override
    public void saveOrUpdate(Nurse nurse) {
        if (nurse.getId()!=null){
            Nurse dbnurse=find(nurse.getId());
            dbnurse.setName(nurse.getName());
            dbnurse.setPhone(nurse.getPhone());
            dbnurse.setWorkUnitId(nurse.getWorkUnitId());
            dbnurse.setWorkUnitName(nurse.getWorkUnitName());
            dbnurse.setUserName(nurse.getUserName());
            dbnurse.setPassWord(nurse.getPassWord());
            dbnurse.setNurseImage(nurse.getNurseImage());
            update(nurse);
        }
        else {
            save(nurse);
            RelationOfSA relationOfSA=new RelationOfSA();
            relationOfSA.setSchoolId(nurse.getWorkUnitId());
            relationOfSA.setNurseId(nurse.getId());
            relationOfSAService.save(relationOfSA);
        }

    }
    @Override
    public void delete(Integer integer) {
        iNurseDao.delete(integer);
        relationOfSAService.deleteAllByNurseId(integer);
        relationOfSchoolAndNurseService.deleteAllByNurseId(integer);
    }

}
