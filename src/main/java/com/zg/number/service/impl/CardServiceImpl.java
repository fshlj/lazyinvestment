package com.zg.number.service.impl;

import com.zg.number.bean.AddCard;
import com.zg.number.bean.User;
import com.zg.number.mapper.CardMapper;
import com.zg.number.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by ${hanwenjian} on 2017/8/9.
 */
@Service
@Component
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Override
    public void addcardxinxi(AddCard addCard) {
        AddCard addCard1 = cardMapper.checkAddCard(addCard);
        if (addCard1==null){
            cardMapper.addcardxinxi(addCard);
        }
    }

    @Override
    public User selectUserAll(String userName) {
        return cardMapper.selectUserAll(userName);
    }

    @Override
    public AddCard selectAddCard(int uid) {
        return cardMapper.selectAddCard(uid);
    }

}
