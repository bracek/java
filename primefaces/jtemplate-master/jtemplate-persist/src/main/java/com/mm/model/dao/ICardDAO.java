package com.mm.model.dao;

import com.mm.model.domain.Card;

import java.util.List;

/**
 * Card DAO Interface
 *
 * @author Miroslav Katrak
 * @version 1.0.0
 */
public interface ICardDAO {

    public void addCard(Card Card);

    public void updateCard(Card Card);

    public void deleteCard(Card Card);

    public Card getCard(int id);

    public List<Card> getEntities();
}
