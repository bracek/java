package com.ixonos.skillnet.logic.service;

import java.util.List;
import com.ixonos.skillnet.logic.bean.Message;

/**
 *
 * @author katrami
 */
public interface MessageService extends HibernateGenericService<Message> {

    public void delete(final Integer id);

    public Message load(final Integer id);

    public void save(final Message mess);

    List<Message> getList();
}
