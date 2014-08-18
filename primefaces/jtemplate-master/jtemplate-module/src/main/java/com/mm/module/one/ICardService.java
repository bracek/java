package com.mm.module.one;

import com.mm.model.domain.Card;

import java.util.List;

/**
 * Card Service Interface
 *
 * @author Miro Katrak
 * @version 1.0.0
 */

public interface ICardService {

    public void addCard(Card Card);

    public void updateCard(Card Card);

    public void deleteCard(Card Card);

    public Card getCardById(int id);

    public List<Card> getCards();
}
