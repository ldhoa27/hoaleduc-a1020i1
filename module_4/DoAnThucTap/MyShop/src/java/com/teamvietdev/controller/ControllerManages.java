package com.teamvietdev.controller;

import com.teamvietdev.model.Member;
import com.teamvietdev.service.MemberService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author TVD
 */
@Controller
@RequestMapping(value = "manages")
public class ControllerManages {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "home.html", method = RequestMethod.GET)
    public String viewHome(ModelMap mm, HttpSession session) {
        Member mb = (Member) session.getAttribute("myLogin");
        if (mb == null) {
            return "redirect:login.html";
        }
        return "manages/index";
    }

    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String viewLogin(ModelMap mm) {
        mm.put("member", new Member());
        return "manages/login";
    }

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    public String doLogin(ModelMap mm, HttpSession session, @ModelAttribute("member") Member member) {
        System.out.println(member.getMemberLogin() + " - " + member.getMemberPass());
        Member mb = memberService.login(member.getMemberLogin(), member.getMemberPass());
        if (mb == null) {
            mm.put("member", new Member());
            mm.put("msg", "The username or password is incorrect!");
            session.setAttribute("myLogin", null);
            return "manages/login";
        }
        session.setAttribute("myLogin", mb);
        return "redirect:home.html";
    }

}
