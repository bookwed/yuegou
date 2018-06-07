package com.wei.service.homePage.impl;

import com.wei.dao.homePage.ContactMeDao;
import com.wei.model.homePage.Contactme;
import com.wei.service.homePage.ContactMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dong on 2017/11/16.
 */
@Service
public class ContactMeServiceImpl implements ContactMeService {

    @Autowired
    private ContactMeDao contactMeDao;

    public Integer add(Contactme contactme) {
        contactme.setCreatetime(new Date());
        contactMeDao.insert(contactme);
        return contactme.getId();
    }
}
