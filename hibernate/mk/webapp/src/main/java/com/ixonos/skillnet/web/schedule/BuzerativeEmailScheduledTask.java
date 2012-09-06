package com.ixonos.skillnet.web.schedule;

import com.ixonos.skillnet.logic.service.MailService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Jun 9, 2009
 * Time: 2:54:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuzerativeEmailScheduledTask {

    @Resource
    private MailService mailService;

    public void performScheduledAction() {
        mailService.sendFewSkillsNotification();
    }

}
