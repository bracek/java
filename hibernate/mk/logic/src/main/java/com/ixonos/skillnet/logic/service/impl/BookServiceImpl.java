
package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.bean.Book;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.BookService;
import java.io.Serializable;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author katrami
 */
@Service("bookService")
public final class BookServiceImpl extends AbstractGenericService<Book> implements BookService {

    public Book load(Serializable id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Book> loadAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book load(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
