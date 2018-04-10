package net.sppan.base.service.impl;

import net.sppan.base.Application;
import net.sppan.base.dao.IRlationOFSDDao;
import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.RlationOFSD;
import net.sppan.base.service.IRlationOFSDService;
import net.sppan.base.service.support.impl.BaseServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RlationOFSDServiceImpl extends BaseServiceImpl<RlationOFSD,Integer> implements IRlationOFSDService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    IRlationOFSDDao RlationOFSDrDao;
    @Autowired
    IRlationOFSDService rlationOFSDService;

    @Override

    public IBaseDao<RlationOFSD, Integer> getBaseDao() {
        return this.RlationOFSDrDao;
    }


    @Override
    public List<RlationOFSD> findByDriverId(Integer id) {
       List<RlationOFSD> list= RlationOFSDrDao.findByDriverId(id);
       for (int i=0;i<list.size();i++){
           logger.debug("dasd               ++++++"+list.get(i).toString());
       }
        return  list ;
    }
    @Override
    public List<RlationOFSD> findBySchoolId(Integer id){
        List<RlationOFSD> list= RlationOFSDrDao.findBySchoolId(id);
        for (int i=0;i<list.size();i++){
            logger.debug("dasd               ++++++"+list.get(i).toString());
        }
        return  list ;
    }

    @Override
    public void deleteByDriverId(Integer id) {
        RlationOFSDrDao.deleteByDriverId(id);
    }

    @Override
    public void deleteAllByDriverId(Integer id) {
        RlationOFSDrDao.deleteAllByDriverId(id);
    }

    @Override
    public void deleteAllBySchoolId(Integer id) {
        RlationOFSDrDao.deleteAllBySchoolId(id);
    }
}
