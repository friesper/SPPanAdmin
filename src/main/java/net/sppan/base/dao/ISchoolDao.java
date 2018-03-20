package net.sppan.base.dao;

import net.sppan.base.dao.support.IBaseDao;
import net.sppan.base.entity.Driver;
import net.sppan.base.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ISchoolDao extends IBaseDao<School,Integer> {
    @Override
    Page<School> findAll(Pageable pageable);

    @Override
    School findOne(Specification<School> specification);


}
