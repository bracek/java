// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fi.ixonos.builder.domain;

import java.lang.String;

privileged aspect Projects_Roo_ToString {
    
    public String Projects.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Meego: ").append(getMeego()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("News_template: ").append(getNews_template() == null ? "null" : getNews_template().size()).append(", ");
        sb.append("Symbian: ").append(getSymbian()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }
    
}
