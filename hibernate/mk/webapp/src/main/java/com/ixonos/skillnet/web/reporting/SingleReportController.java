//package com.ixonos.skillnet.web.reporting;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//import org.apache.log4j.Logger;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Restrictions;
//import org.zkoss.util.resource.Labels;
//import org.zkoss.zk.ui.Session;
//import org.zkoss.zk.ui.event.MouseEvent;
//import org.zkoss.zk.ui.event.SelectEvent;
//import org.zkoss.zkex.zul.Jasperreport;
//import org.zkoss.zkplus.spring.SpringUtil;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Window;
//
//import com.ixonos.skillnet.logic.bean.Practicum;
//import com.ixonos.skillnet.logic.bean.Users;
//import com.ixonos.skillnet.logic.service.PracticumService;
//import com.ixonos.skillnet.logic.service.UsersService;
//
///**
// * Controller class for single_report.zul
// *
// * @author molnala
// *
// */
//public class SingleReportController extends Window {
//
//    private static Logger logger = Logger.getLogger(SingleReportController.class);
//
//    private static final long serialVersionUID = 1L;
//
//    private PracticumService practicumService = (PracticumService)SpringUtil.getApplicationContext().getBean("practicumService");
//
//    private UsersService usersService = (UsersService)SpringUtil.getApplicationContext().getBean("usersService");
//
//    /**
//     * The format selection combo-box
//     */
//    private Listbox format_listbox;
//
//    /**
//     * The Jasper report component
//     */
//    private Jasperreport pracReport;
//
//    public void onClickReportBtn(MouseEvent event) throws Exception {
//        createReport();
//    }
//
//    public void onSelectFormat(SelectEvent event) throws Exception {
//        createReport();
//    }
//
//    private void createReport() {
//        if (usersService == null) {
//            logger.error("usersService not found!");
//            return;
//        }
//        format_listbox = (Listbox) this.getFellow("format_listbox");
//        if (format_listbox==null) {
//            logger.error("format_listbox not found!");
//            return;
//        }
//        pracReport = (Jasperreport) this.getFellow("pracReport");
//        if (pracReport==null) {
//            logger.error("pracReport not found!");
//            return;
//        }
//
//        String userNamesStr = (String) this.getDesktop().getSession().getAttribute("userName");
//        Collection<Practicum> practicumList;
//        StringBuilder filterTextBuilder = new StringBuilder();
//
//        if (userNamesStr!=null) {
//            logger.debug("userNames = '" + userNamesStr + "'");
//            String[] userNames = userNamesStr.split(",");
//
//            DetachedCriteria userCriteria = DetachedCriteria.forClass(Users.class, "u");
//            userCriteria.add(Restrictions.in("username", userNames));
//            Collection<Users> usersCollection = usersService.readByCriteria(userCriteria);
//
//            if (usersCollection==null || usersCollection.isEmpty()) {
//                logger.error("usersCollection null or empty!!!");
//                return;
//            }
//
//            StringBuilder usersBuilder = new StringBuilder();
//            for (Users user : usersCollection) {
//                if (usersBuilder.length()>0) {
//                    usersBuilder.append(", ");
//                }
//                usersBuilder.append(user.getHumanReadableUsername());
//            }
//            filterTextBuilder.append(Labels.getLabel("singleReport.usersColon") + " ").append(usersBuilder);
//
//            // build up the criteria and get the list of matching practicums
//            DetachedCriteria practicumCriteria = DetachedCriteria.forClass(Practicum.class, "p");
//            practicumCriteria.add(Restrictions.in("userId", usersCollection));
//            practicumList = practicumService.readByCriteria(practicumCriteria);
//        }
//        else {
//            practicumList = (Collection) getDesktop().getSession().getAttribute("practicumCollection");
//            filterTextBuilder.append((String) getDesktop().getSession().getAttribute("practicumFilterStr"));
//        }
//
//        // prepare parameters and create the jasper report
//        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("filterText", filterTextBuilder.toString());
//        parameters.put("dateFormat", "dd.MM.yyyy");
//        pracReport.setSrc("user_report.jasper");
//        pracReport.setParameters(parameters);
//        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(practicumList, false);
//        pracReport.setDatasource(source);
//        pracReport.setType((String) format_listbox.getSelectedItem().getValue());
//    }
//
//    public static void initReportForUser(Session session, String userName) {
//    	session.setAttribute("userName", userName);
//    	session.setAttribute("practicumCollection", null);
//    	session.setAttribute("practicumFilterStr", null);
//    }
//
//    public static void initReportForPracticums(Session session, Collection<Practicum> practicums, String filter) {
//    	session.setAttribute("userName", null);
//    	session.setAttribute("practicumCollection", practicums);
//    	session.setAttribute("practicumFilterStr", filter);
//    }
//}
