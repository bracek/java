package fi.ixonos.projects.logic;

import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class AbstractWebTest extends AbstractTransactionalDataSourceSpringContextTests {

    protected String testPassword = "password";
    protected String testUsername = "polaktest";
    protected String testProjectName = "Android";
    protected String testName;
    protected String testSurname;
    protected String testUpdatedTestName;
    protected String testEmail;
    protected final String testTelephonNumber;
    private HibernateTemplate hibernateTemplate;

    public AbstractWebTest() {
        this.testTelephonNumber = "12345";
        this.testEmail = "test_email";
        this.testName = "test_default_test_name";
        this.testSurname = "test_surname";
        this.testUpdatedTestName = "test_updated_defaultTestName";
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath*:applicationContext-logic.xml"};
    }

    @Autowired
    @Override
    public void setDataSource(@Qualifier("dataSourceProjects") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    /* (non-Javadoc)

     * @see org.springframework.test.AbstractTransactionalSpringContextTests#setTransactionManager(org.springframework.transaction.PlatformTransactionManager)

     */
    @Autowired
    @Override
    public void setTransactionManager(@Qualifier("projectsTxManager") PlatformTransactionManager transactionManager) {
        super.setTransactionManager(transactionManager);
    }

    protected void flush() {
        //Initialize Hibernate Template if Necessary
        if (hibernateTemplate == null) {
            try {
                hibernateTemplate = new HibernateTemplate((SessionFactory) ProjectsApplicationContext.getApplicationContext().getBean("sessionFactory"));
            } catch (Exception e) {
            }
        }
        hibernateTemplate.flush();
    }
}
