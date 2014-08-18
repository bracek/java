package com.mm.module.one.impl;

import com.mm.model.dao.ICardDAO;
import com.mm.model.domain.Card;
import com.mm.module.one.ICardService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Card Service
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */
@Named
@Transactional(readOnly = true)
public class CardService implements ICardService {

    @Inject
    ICardDAO CardDAO;

    @Transactional(readOnly = false)
    public void addCard(Card Card) {
        getCardDAO().addCard(Card);
    }

    @Transactional(readOnly = false)
    public void deleteCard(Card Card) {
        getCardDAO().deleteCard(Card);
    }

    @Transactional(readOnly = false)
    public void updateCard(Card Card) {
        getCardDAO().updateCard(Card);
    }

    public Card getCardById(int id) {
        return getCardDAO().getCard(id);
    }

    public List<Card> getCards() {
        return getCardDAO().getEntities();
    }

    public ICardDAO getCardDAO() {
        return CardDAO;
    }

    public void setCardDAO(ICardDAO CardDAO) {
        this.CardDAO = CardDAO;
    }
}
