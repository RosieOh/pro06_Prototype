package com.springbootstart.repository;

import com.springbootstart.entity.Member;
import com.springbootstart.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select p from Profile p where p.member =:member")
    Profile ProfileByMember(Member member);
}
