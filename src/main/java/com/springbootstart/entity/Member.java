package com.springbootstart.entity;


import com.springbootstart.constant.MemberRole;
import com.springbootstart.dto.member.MemberFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@Table(name = "member")
@ToString
public class Member{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDTO.getName());
        member.setEmail(memberFormDTO.getEmail());
        member.setAddress(memberFormDTO.getAddress());
        String password = passwordEncoder.encode(memberFormDTO.getPassword());
        member.setPassword(password);
        member.setRole(MemberRole.USER);
        return member;
    }
}
