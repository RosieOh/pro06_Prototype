package com.springbootstart.service;

import com.springbootstart.entity.Member;
import com.springbootstart.entity.Profile;

public interface ProfileService {

    int insertProfile(Profile profile);

    void updateProfile(Profile profile);

    Profile profileByMember(Member member);
}
