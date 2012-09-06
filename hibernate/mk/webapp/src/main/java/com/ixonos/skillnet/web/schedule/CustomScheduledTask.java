package com.ixonos.skillnet.web.schedule;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.MailService;
import com.ixonos.skillnet.logic.service.UsersService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hustasl
 * Date: Apr 7, 2009
 * Time: 4:57:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomScheduledTask {

    private static final Logger logger = Logger.getLogger(CustomScheduledTask.class);

    @Resource
    MailService mailService;
                
    public void performScheduledAction() {

        //send mail
        //MailManagerImpl mmi = new MailManagerImpl();
        //mmi.sendMail();
        logger.info("Task is running");
        String networkInformation = "";

        try {
            Process pid = Runtime.getRuntime().exec("ifconfig -a");
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(pid.getInputStream()));
            while (true) {
                String line = in.readLine();
                if (line == null)
                    break;
                networkInformation += line + "<br/>\n";
            }
            in.close();

        } catch (IOException ioex) {
            logger.error(ioex);
        }

        List<String> textParameters = new ArrayList<String>();
        textParameters.add(networkInformation);

        mailService.sendAdminNightlyNotification(textParameters);

    }
}
