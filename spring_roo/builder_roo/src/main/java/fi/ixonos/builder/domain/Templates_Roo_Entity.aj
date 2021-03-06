// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fi.ixonos.builder.domain;

import fi.ixonos.builder.domain.Templates;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Templates_Roo_Entity {
    
    declare @type: Templates: @Entity;
    
    @PersistenceContext
    transient EntityManager Templates.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Templates.id;
    
    @Version
    @Column(name = "version")
    private Integer Templates.version;
    
    public Long Templates.getId() {
        return this.id;
    }
    
    public void Templates.setId(Long id) {
        this.id = id;
    }
    
    public Integer Templates.getVersion() {
        return this.version;
    }
    
    public void Templates.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Templates.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Templates.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Templates attached = Templates.findTemplates(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Templates.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Templates.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Templates Templates.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Templates merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Templates.entityManager() {
        EntityManager em = new Templates().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Templates.countTemplateses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Templates o", Long.class).getSingleResult();
    }
    
    public static List<Templates> Templates.findAllTemplateses() {
        return entityManager().createQuery("SELECT o FROM Templates o", Templates.class).getResultList();
    }
    
    public static Templates Templates.findTemplates(Long id) {
        if (id == null) return null;
        return entityManager().find(Templates.class, id);
    }
    
    public static List<Templates> Templates.findTemplatesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Templates o", Templates.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
