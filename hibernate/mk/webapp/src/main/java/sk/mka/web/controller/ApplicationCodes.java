package sk.mka.web.controller;

/**
 *
 * @author bracek
 */
public interface ApplicationCodes {

    interface MESSAGE {

        String COMMAND = "Message";
        String DELETE_REQUEST_MAPPING = "/menu/message/deleteMessage.do";
        String VIEW_REQUEST_MAPPING = "/menu/message/viewMessages.do";
        String EDIT_REQUEST_MAPPING = "/menu/message/editMessage.do";
        String VIEW_MESSAGE_KEY = "/menu/message/viewMessages";
        String EDIT_KEY = "/menu/message/editMessage";
    }

    interface PRODUCT {

        String COMMAND = "Product";
        String DELETE_REQUEST_MAPPING = "/menu/message/deleteProduct.do";
        String EDIT_REQUEST_MAPPING = "/menu/message/editProduct.do";
        String EDIT_KEY = "/menu/message/editProduct";
    }

    interface USERS {

        String COMMAND = "Users";
        String VIEW_MESSAGE_KEY = "/menu/users/listUsers";
        String VIEW_REQUEST_MAPPING = "/menu/users/listUsers.do";
    }
}
