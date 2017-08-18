package com.zg.number.service;

import com.zg.number.bean.AddCard;
import com.zg.number.bean.User;

/**
 * Created by ${hanwenjian} on 2017/8/9.
 */

public interface CardService {
    void addcardxinxi(AddCard addCard);

    User selectUserAll(String userName);

    AddCard selectAddCard(int uid);
}
