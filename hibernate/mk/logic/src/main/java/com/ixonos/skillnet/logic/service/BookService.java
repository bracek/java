
package com.ixonos.skillnet.logic.service;


import com.ixonos.skillnet.logic.bean.Book;
import java.util.Collection;

/**
 *
 * @author katrami
 */
public interface BookService extends HibernateGenericService<Book> {

     Book load(final Integer id);

     Collection<Book> loadAll();
}


