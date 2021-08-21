package com.teamvietdev.dao;

import com.teamvietdev.model.Member;

/**
 *
 * @author TVD
 */
public interface MemberDAO {

    // login
    public Member login(String uname, String passwd);

}
