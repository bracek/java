package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.bean.Message;
import com.ixonos.skillnet.logic.dao.MessageDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.MessageService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author katrami
 */
@Service("messageService")
public final class MessageServiceImpl extends AbstractGenericService<Message> implements MessageService {
    // -------------------------------- ATTRS ----------------------------------

    /** The logger. */
    private static final Log logger = LogFactory.getLog(MessageServiceImpl.class);
    /** The product dao. */
    @Resource
    private MessageDAO messageDAO;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void save(final Message mess) {
        if (mess != null) {
            if (mess.getId() == null) {
                messageDAO.create(mess);
            } else {
                messageDAO.update(mess);
            }
        }
    }

    @Override
    public void delete(final Integer id) {
        Message message = messageDAO.read(id);
        messageDAO.delete(message);
    }

    @Override
    public Message load(final Integer id) {
        return messageDAO.read(id);
    }

    @Override
    public List<Message> getList() {
        return messageDAO.readAll();
    }
}
