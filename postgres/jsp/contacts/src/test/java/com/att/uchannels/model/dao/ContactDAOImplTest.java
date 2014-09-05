
package com.att.uchannels.model.dao;

import com.att.uchannels.AbstractWebTest;
import com.att.uchannels.TestInterface;
import com.att.uchannels.domain.Contact;

import javax.annotation.Resource;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */
public class ContactDAOImplTest extends AbstractWebTest implements TestInterface {

    @Resource
    private ContactDAO contactDAO;

    @Override
    public void testInsert() {
        final Contact contact = new Contact();
        contact.setName(TEST_NAME);
        contact.setPhone(TEST_TELEPHONE_NUMBER);
        contact.setMobile(TEST_TELEPHONE_NUMBER);
        contact.setGender(TEST_GENDER);
        contact.setEmail(TEST_EMAIL);
        contact.setAddress(TEST_ADDRESS);
        contactDAO.create(contact);

        flush();
        IdHolder.contactId = contact.getId();
    }

    @Override
    public void testLoad() {
        Contact user = contactDAO.read(IdHolder.contactId);
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_TELEPHONE_NUMBER, user.getMobile());
    }

    @Override
    public void testUpdate() {
        Contact user = contactDAO.read(IdHolder.contactId);
        user.setMobile(TEST_TELEPHONE_NUMBER_UP);
        contactDAO.update(user);
        flush();

        user = contactDAO.read(IdHolder.contactId);
        assertEquals(TEST_TELEPHONE_NUMBER_UP, user.getMobile());
    }

    @Override
    public void testDelete() {
        Contact user = contactDAO.read(IdHolder.contactId);
        boolean isDeleted = contactDAO.delete(user);
        flush();
        assertTrue("User was not successfully deleted", isDeleted);
    }
}
