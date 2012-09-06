package fi.ixonos.projects.logic.enumeration;

public class ProjectsConfigParams {

    public static final String SMTP_HOST = "SMTP_HOST";
    public static final String SMTP_HOST_DEFAULT = "mxtreme.ixonos.com";
    public static final String SMTP_PROTOCOL = "SMTP_PROTOCOL";
    public static final String SMTP_PROTOCOL_DEFAULT = "smtp";
    public static final String SMTP_PORT = "SMTP_PORT";
    public static final Integer SMTP_PORT_DEFAULT = 25;
    public static final String LDAP_HOST = "LDAP_HOST";
    public static final String LDAP_HOST_DEFAULT = "jkldc02";
    public static final String LDAP_PORT = "LDAP_PORT";
    public static final Integer LDAP_PORT_DEFAULT = 389;
    public static final String LDAP_BASE_DN = "LDAP_BASE_DN";
    public static final String LDAP_BASE_DN_DEFAULT = "DC=ixonos,DC=local";
    public static final String MAIL_ADDRESS = "MAIL_ADDRESS";
    public static final String MAIL_ADDRESS_DEFAULT = "skillnet@ixonos.com";
    public static final String MAIL_ADDRESS_PERSONAL = "MAIL_ADDRESS_PERSONAL";
    public static final String MAIL_ADDRESS_PERSONAL_DEFAULT = "Skillnet";
    /** The minimal level of user skills (where user count of user skills is counted as
     * percentage of user skills from all valueable skills)*/
    public static final String MINIMUM_SKILLS = "MINIMUM_SKILLS";
    public static final Integer MINIMUM_SKILLS_DEFAULT = 5;

    /*
     * Uneditable parameters
     */
    public static final String GROUP_CODE_ANSWER_TYPES = "ANSWER_TYPES";
    public static final String ANSWER_TYPE_LEVELS = "LEVELS";
    public static final String ANSWER_TYPE_BOOLEAN = "BOOLEAN";
    public static final String ANSWER_TYPE_TIME_INTERVAL = "TIME_INTERVAL";
    public static final String PROJECT_STATE = "PROJECT_STATE";
    public static final String ASSIGNMENT_STATE = "ASSIGNMENT_STATE";
}

