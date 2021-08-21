package com.teamvietdev.service;

import com.teamvietdev.dao.MemberDAO;
import com.teamvietdev.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TVD
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member login(String uname, String passwd) {
        return memberDAO.login(uname, passwd);
    }

}
