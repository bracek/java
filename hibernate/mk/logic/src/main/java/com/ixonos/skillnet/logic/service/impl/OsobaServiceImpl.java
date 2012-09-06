package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.bean.Osoba;
import com.ixonos.skillnet.logic.dao.OsobaDao;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.OsobaService;
import java.util.Collection;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author katrami
 */
@Service("personService")
public class OsobaServiceImpl extends AbstractGenericService<Osoba> implements OsobaService {

    /** The product dao. */
    @Resource
    private OsobaDao osobaDao;
    /** The logger. */
    private static final Log logger = LogFactory.getLog(BookServiceImpl.class);

    @Override
    public Collection<Osoba> findPersonsByLastName(String lastName) {
       return osobaDao.findPersonsByLastName(lastName);
    }

//    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//    public void savePerson(Osoba prod) {
//
//        if (prod != null) {
//            if (prod.getId() == null) {
//                ((AbstractGenericService) osobaDao).create(prod);
//
//            } else {
//                ((AbstractGenericService) osobaDao).update(prod);
//            }
//            logger.debug("saveProduct(): Product is stored.");
//        }
//    }
//    public Collection<Osoba> findPersonsByLastName(String lastName) {
//        return osobaDao.findPersonsByLastName(lastName);
//
//    }
}
