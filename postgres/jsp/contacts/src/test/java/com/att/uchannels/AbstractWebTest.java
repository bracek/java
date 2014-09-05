package com.att.uchannels;

import com.att.uchannels.context.ProjectsApplicationContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//public abstract class AbstractWebTest extends AbstractTransactionalDataSourceSpringContextTests {
public abstract class AbstractWebTest extends AbstractTransactionalDataSourceSpringContextTests {

    public static final String TEST_ATTRIBUTE = "attr";
    public static final String TEST_ATTRIBUTE_UP = "attr_up";
    protected static final String TEST_GENDER = "M";
    protected String TEST_NAME;
    protected String TEST_SURNAME;
    protected String TEST_NAME_UP;
    protected String TEST_EMAIL;
    protected final String TEST_TELEPHONE_NUMBER;
    protected final String TEST_TELEPHONE_NUMBER_UP;
    protected String TEST_ADDRESS = "Hviezdoslavova";
    private HibernateTemplate hibernateTemplate;

    public AbstractWebTest() {
        this.TEST_TELEPHONE_NUMBER = "12345";
        this.TEST_TELEPHONE_NUMBER_UP = "12345up";
        this.TEST_EMAIL = "test_email";
        this.TEST_NAME = "test_default_test_name";
        this.TEST_SURNAME = "test_surname";
        this.TEST_NAME_UP = "test_updated_defaultTestName";
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath*:applicationContext-logic.xml"};
    }

    @Autowired
    @Override
    public void setDataSource(final @Qualifier("dataSourceProjects") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    /* (non-Javadoc)

     * @see org.springframework.test.AbstractTransactionalSpringContextTests#setTransactionManager(org.springframework.transaction.PlatformTransactionManager)

     */
    @Autowired
    @Override
    public void setTransactionManager(final @Qualifier("projectsTxManager") PlatformTransactionManager transactionManager) {
//        super.setTransactionManager(transactionManager);
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
