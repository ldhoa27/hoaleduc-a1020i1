package com.teamvietdev.service;

import com.teamvietdev.model.Member;

/**
 *
 * @author TVD
 */
public interface MemberService {

    // login
    public Member login(String uname, String passwd);

}
