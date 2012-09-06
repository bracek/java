//package com.ixonos.skillnet.web.reporting;
//
//import org.apache.log4j.Logger;
//import org.springframework.security.context.SecurityContextHolder;
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.Page;
//import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
//import org.zkoss.zul.Window;
//
//import com.ixonos.skillnet.logic.security.SkillnetUser;
//
///**
// * @author stibron
// *
// */
//public class MyReportInitiator extends AnnotateDataBinderInit {
//
//    private static Logger logger = Logger.getLogger(SingleReportController.class);
//
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    public void doAfterCompose(Page page, Component[] comps) throws Exception {
//
//    	//load user
//    	SkillnetUser user = (SkillnetUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	logger.debug("#doAfterCompose: User from session: " + user.getUsername());
//
//    	SingleReportController.initReportForUser(page.getDesktop().getSession(), user.getUsername());
//    	Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/reporting/single_report.zul", null, null);
//    	win.doEmbedded();
//
//    	logger.debug("#doAfterCompose: Doing redirect to report page.");
//    }
//
//}
