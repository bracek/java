package com.globallogic.kaacoo.app;

import com.globallogic.kaacoo.model.Merchant;
import com.globallogic.kaacoo.model.President;
import com.globallogic.kaacoo.model.Store;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Jaroslav Sebes
 */

@Component
@Transactional
public class PocDatabasePopulator {

    private boolean populated;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void populateDatabase() {
        if (populated) {
            return;
        }
        Merchant m1 = Merchant.newBuilder().title("Walmart").entity;
        Merchant m2 = Merchant.newBuilder().title("IKEA").entity;
        Merchant m3 = Merchant.newBuilder().title("Sears").entity;
        Merchant m4 = Merchant.newBuilder().title("K-Mart").entity;
        Merchant m5 = Merchant.newBuilder().title("Macy's").entity;
        Merchant m6 = Merchant.newBuilder().title("Gap").entity;
        Merchant m7 = Merchant.newBuilder().title("Blockbuster").entity;
        Merchant m8 = Merchant.newBuilder().title("Radio Shack").entity;
        Merchant m9 = Merchant.newBuilder().title("Perfumania").entity;
        Merchant m10 = Merchant.newBuilder().title("Marshalls").entity;
        Merchant m11 = Merchant.newBuilder().title("Spider").entity;
        Merchant m12 = Merchant.newBuilder().title("Burger King").entity;


        President p0 = President.newBuilder().name("title0", "Miro", "Katrak").entity;
        President p1 = President.newBuilder().name("title1", "George", "Washington").entity;
        President p2 = President.newBuilder().name("title2", "John", "Adams").entity;
        President p3 = President.newBuilder().name("title3", "Thomas", "Jefferson").entity;
        persist(p0, p1, p2, p3);

        Store s1 = Store.newBuilder()
                .merchant(m1)
                .street("Washington Street")
                .city("Sacramento")
                .state("California")
                .zip("98765")
                .phone("89040 899 788")
                .hours("Mon-Fri 10.00 AM - 9.00 PM")
                .entity;

        Store s2 = Store.newBuilder()
                .merchant(m1)
                .street("Princeton Street")
                .city("Los Angeles")
                .state("California")
                .zip("98744")
                .phone("345435 455 788")
                .hours("Mon-Fri 11.00 AM - 9.00 PM")
                .entity;

        Store s3 = Store.newBuilder()
                .merchant(m1)
                .street("13th Avenue")
                .city("Seattle")
                .state("Washington")
                .zip("65665")
                .phone("345435 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s4 = Store.newBuilder()
                .merchant(m1)
                .street("Jackson Street")
                .city("Las Vegas")
                .state("Nevada")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s5 = Store.newBuilder()
                .merchant(m1)
                .street("Green Road")
                .city("Dallas")
                .state("Texas")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s6 = Store.newBuilder()
                .merchant(m2)
                .street("Boulevard Avenue")
                .city("Miami")
                .state("Florida")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s7 = Store.newBuilder()
                .merchant(m2)
                .street("East Coast Street")
                .city("Atlanta")
                .state("Georgia")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s8 = Store.newBuilder()
                .merchant(m2)
                .street("5th Avenue")
                .city("New York")
                .state("New York")
                .zip("98765")
                .phone("89040 899 788")
                .hours("Mon-Fri 10.00 AM - 9.00 PM")
                .entity;

        Store s9 = Store.newBuilder()
                .merchant(m3)
                .street("Bay Street")
                .city("Boston")
                .state("Massachussets")
                .zip("98744")
                .phone("345435 455 788")
                .hours("Mon-Fri 11.00 AM - 9.00 PM")
                .entity;

        Store s10 = Store.newBuilder()
                .merchant(m3)
                .street("Fish road 21")
                .city("Bayou La Batre")
                .state("Alabama")
                .zip("65665")
                .phone("345435 544 211")
                .hours("Mon-Fri 06.00 AM - 9.00 PM")
                .entity;

        Store s11 = Store.newBuilder()
                .merchant(m3)
                .street("Josh Street")
                .city("Carson City")
                .state("Nevada")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s12 = Store.newBuilder()
                .merchant(m4)
                .street("Pallace museum")
                .city("St. Louis")
                .state("Missouri")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s13 = Store.newBuilder()
                .merchant(m4)
                .street("Nameless street")
                .city("San Diego")
                .state("California")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s14 = Store.newBuilder()
                .merchant(m5)
                .street("East Coast Street")
                .city("Atlanta")
                .state("Georgia")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s15 = Store.newBuilder()
                .merchant(m5)
                .street("Washington Street")
                .city("Sacramento")
                .state("California")
                .zip("98765")
                .phone("89040 899 788")
                .hours("Mon-Fri 10.00 AM - 9.00 PM")
                .entity;

        Store s16 = Store.newBuilder()
                .merchant(m6)
                .street("Princeton Street")
                .city("Los Angeles")
                .state("California")
                .zip("98744")
                .phone("345435 455 788")
                .hours("Mon-Fri 11.00 AM - 9.00 PM")
                .entity;

        Store s17 = Store.newBuilder()
                .merchant(m7)
                .street("13th Avenue")
                .city("Seattle")
                .state("Washington")
                .zip("65665")
                .phone("345435 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s18 = Store.newBuilder()
                .merchant(m7)
                .street("Jackson Street")
                .city("Las Vegas")
                .state("Nevada")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s19 = Store.newBuilder()
                .merchant(m7)
                .street("Green Road")
                .city("Dallas")
                .state("Texas")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s20 = Store.newBuilder()
                .merchant(m8)
                .street("Boulevard Avenue")
                .city("Miami")
                .state("Florida")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s21 = Store.newBuilder()
                .merchant(m8)
                .street("East Coast Street")
                .city("Atlanta")
                .state("Georgia")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s22 = Store.newBuilder()
                .merchant(m9)
                .street("5th Avenue")
                .city("New York")
                .state("New York")
                .zip("98765")
                .phone("89040 899 788")
                .hours("Mon-Fri 10.00 AM - 9.00 PM")
                .entity;

        Store s23 = Store.newBuilder()
                .merchant(m10)
                .street("Bay Street")
                .city("Boston")
                .state("Massachussets")
                .zip("98744")
                .phone("345435 455 788")
                .hours("Mon-Fri 11.00 AM - 9.00 PM")
                .entity;

        Store s24 = Store.newBuilder()
                .merchant(m10)
                .street("Fish road 21")
                .city("Bayou La Batre")
                .state("Alabama")
                .zip("65665")
                .phone("345435 544 211")
                .hours("Mon-Fri 06.00 AM - 9.00 PM")
                .entity;

        Store s25 = Store.newBuilder()
                .merchant(m10)
                .street("Josh Street")
                .city("Carson City")
                .state("Nevada")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s26 = Store.newBuilder()
                .merchant(m10)
                .street("Pallace museum")
                .city("St. Louis")
                .state("Missouri")
                .zip("99822")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s27 = Store.newBuilder()
                .merchant(m10)
                .street("Nameless street")
                .city("San Diego")
                .state("California")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        Store s28 = Store.newBuilder()
                .merchant(m10)
                .street("East Coast Street")
                .city("Atlanta")
                .state("Georgia")
                .zip("323434")
                .phone("3424234 544 211")
                .hours("Mon-Fri 08.00 AM - 9.00 PM")
                .entity;

        persist(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15,
                s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28);

        em.flush();
        em.clear();
    }

    private void persist(Object... entities) {
        for (Object entity : entities) {
            em.persist(entity);
        }
    }

}
