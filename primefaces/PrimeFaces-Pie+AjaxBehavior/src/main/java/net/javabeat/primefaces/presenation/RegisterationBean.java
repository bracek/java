package net.javabeat.primefaces.presenation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.javabeat.eclipselink.persistence.data.User;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@SessionScoped
public class RegisterationBean {
	static EntityManagerFactory factory = null;
	static EntityManager em = null;
	
	static {
		factory = Persistence.createEntityManagerFactory("mongoPU");
		em = factory.createEntityManager();
	}
	
	private User user = new User();
	private PieChartModel model = new PieChartModel();
	private List<User> selectedUsers = new ArrayList<User>();
	
	int aCounter = 0;
	int bCounter = 0;
	int cCounter = 0;
	int dCounter = 0;	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<User> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	@SuppressWarnings("unchecked")
	public PieChartModel getModel() {

		// At each rendering of data, a query has made upon mongo data.
		Query query = em.createQuery("SELECT u FROM User u");
		// Fetch the all users
		List<User> users = query.getResultList();
		// Categorize Users
		for(User u : users){
			if(u.getStatus().equals("A")){
				aCounter++;
			}
			if(u.getStatus().equals("B")){
				bCounter++;
			}
			if(u.getStatus().equals("C")){
				cCounter++;
			}		
			if(u.getStatus().equals("D")){
				dCounter++;
			}				
		}
		this.model = new PieChartModel();
		// Fill the model
		model.set("A", aCounter);
		model.set("B", bCounter);
		model.set("C", cCounter);
		model.set("D", dCounter);		
		return model;
	}

	public void setModel(PieChartModel model) {
		this.model = model;
	}

	public String saveUser(){
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		this.user = new User();
		return "";
	}
	
	public void listener(ItemSelectEvent e){
		// Rest selected users
		this.selectedUsers = new ArrayList<User>();
		// A category has been selected
		if(e.getItemIndex() == 0){
			// At each rendering of data, a query has made upon mongo data.
			Query query = em.createQuery("SELECT u FROM User u WHERE u.status = 'A'");
			// Fetch the all users
			this.selectedUsers = query.getResultList();
		}
		// B category has been selected
		else if(e.getItemIndex() == 1){
			// At each rendering of data, a query has made upon mongo data.
			Query query = em.createQuery("SELECT u FROM User u WHERE u.status = 'B'");
			// Fetch the all users
			this.selectedUsers = query.getResultList();			
		}
		// C category has been selected
		else if(e.getItemIndex() == 2){
			// At each rendering of data, a query has made upon mongo data.
			Query query = em.createQuery("SELECT u FROM User u WHERE u.status = 'C'");
			// Fetch the all users
			this.selectedUsers = query.getResultList();			
		}
		// D category has been selected
		else if(e.getItemIndex() == 3){
			// At each rendering of data, a query has made upon mongo data.
			Query query = em.createQuery("SELECT u FROM User u WHERE u.status = 'D'");
			// Fetch the all users
			this.selectedUsers = query.getResultList();
		}
	}
	
	
}
