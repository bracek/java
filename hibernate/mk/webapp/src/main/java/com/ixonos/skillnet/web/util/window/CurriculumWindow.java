package com.ixonos.skillnet.web.util.window;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.annotation.Secured;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Filedownload;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.UsersService;

/**
 * 
 * @author katrami
 */
public final class CurriculumWindow {

	final static protected UsersService usersService = (UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");
	public static final String CURRICULUM_DOC_SUFFIX = "_cv.doc";

	/**
	 * handle getting curriculum for logged user
	 * 
	 * @param event
	 * @throws java.lang.Exception
	 */
	@Secured(ROLE_GM)
	public static void downloadCV(final org.zkoss.zk.ui.event.Event event,
			final Users user) {
		try {
			final Users readUser = usersService.getUser(user.getUsername());
			final byte[] curricullumByteArray = readUser.getCurriculum();
			if (curricullumByteArray != null) {
				final InputStream inputStream = new ByteArrayInputStream(
						curricullumByteArray);
				final String cvdoc;
				if (!user.getName().equals(null)
						&& !user.getSurname().equals(null)) {
					cvdoc = user.getName() + user.getSurname()
							+ CURRICULUM_DOC_SUFFIX;
				} else {
					cvdoc = user.getUsername() + CURRICULUM_DOC_SUFFIX;
				}
				Filedownload.save(inputStream, null, cvdoc);
			}
		} catch (final Exception ex) {
			Logger.getLogger(CurriculumWindow.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}
}
