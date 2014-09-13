package com.att.uchannels;

import com.att.uchannels.context.ProjectsApplicationContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//public abstract class AbstractWebTest extends AbstractTransactionalDataSourceSpringContextTests {
public abstract class AbstractWebTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    public static final String TEST_ATTRIBUTE = "attr";
    public static final String TEST_ATTRIBUTE_UP = "attr_up";
    protected static final String TEST_GENDER = "M";
    public static final String UP = "_UP";
    protected String TEST_NAME;
    protected String TEST_SURNAME;
    protected String TEST_NAME_UP;
    protected String TEST_EMAIL;
    protected final String TEST_TELEPHONE_NUMBER;
    protected final String TEST_TELEPHONE_NUMBER_UP;
    protected String TEST_ADDRESS = "Hviezdoslavova";
    private HibernateTemplate hibernateTemplate;

    public AbstractWebTest() {
        this.TEST_TELEPHONE_NUMBER = "0907481988";
        this.TEST_TELEPHONE_NUMBER_UP = TEST_TELEPHONE_NUMBER + UP;
        this.TEST_EMAIL = "abc@gmail.com";
        this.TEST_NAME = "test_name";
        this.TEST_NAME_UP = TEST_NAME + UP;
        this.TEST_SURNAME = "test_surname";
        this.TEST_SURNAME = TEST_SURNAME + UP;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath*:applicationContext-test.xml"};
    }

    @Autowired
    @Override
    public void setDataSource(
            final @Qualifier("dataSource") DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#
     * setTransactionManager
     * (org.springframework.transaction.PlatformTransactionManager)
     */
    @Autowired
    @Override
    public void setTransactionManager(
            final @Qualifier("transactionManager") PlatformTransactionManager transactionManager) {
        super.setTransactionManager(transactionManager);
    }

    protected void flush() {
        // Initialize Hibernate Template if Necessary
        if (hibernateTemplate == null) {
            try {
                hibernateTemplate = new HibernateTemplate(
                        (SessionFactory) ProjectsApplicationContext
                                .getApplicationContext().getBean(
                                        "sessionFactory"));
            } catch (Exception e) {
            }
        }
        hibernateTemplate.flush();
    }
}
