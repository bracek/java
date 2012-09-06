package com.ixonos.skillnet.web.util.window;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Filedownload;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;
import org.springframework.security.annotation.Secured;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;

/**
 *
 * @author katrami
 */
public final class CurriculumWindow {

    final static protected UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
    public static final String CURRICULUM_DOC_SUFFIX = "_cv.doc";

    /**
     * handle getting curriculum for logged user
     * @param event
     * @throws java.lang.Exception
     */
    @Secured(ROLE_GM)
    public static void downloadCV(org.zkoss.zk.ui.event.Event event, Users user) {
        try {
            Users readUser = usersService.getUser(user.getUsername());
            final byte[] curricullumByteArray = readUser.getCurriculum();
            if (curricullumByteArray != null) {
                final InputStream inputStream = new ByteArrayInputStream(curricullumByteArray);
                final String cvdoc;
                if (!user.getName().equals(null) && !user.getSurname().equals(null)) {
                    cvdoc = user.getName() + user.getSurname() + CURRICULUM_DOC_SUFFIX;
                } else {
                    cvdoc = user.getUsername() + CURRICULUM_DOC_SUFFIX;
                }
                Filedownload.save(inputStream, null, cvdoc);
            }
        } catch (Exception ex) {
            Logger.getLogger(CurriculumWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}