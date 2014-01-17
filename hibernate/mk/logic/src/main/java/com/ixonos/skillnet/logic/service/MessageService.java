package com.ixonos.skillnet.logic.service;

import java.util.List;
import com.ixonos.skillnet.logic.bean.Message;

/**
 *
 * @author katrami
 */
public interface MessageService extends HibernateGenericService<Message> {

     void delete(final Integer id);

     Message load(final Integer id);

     void save(final Message mess);

    List<Message> getList();
}
