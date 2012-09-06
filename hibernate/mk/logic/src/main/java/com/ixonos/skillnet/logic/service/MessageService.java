package com.ixonos.skillnet.logic.service;

import java.util.List;
import com.ixonos.skillnet.logic.bean.Message;

/**
 *
 * @author katrami
 */
public interface MessageService extends HibernateGenericService<Message> {

    public void delete(Integer id);

    public Message load(Integer id);

    public void save(Message mess);

    List<Message> getList();
}
