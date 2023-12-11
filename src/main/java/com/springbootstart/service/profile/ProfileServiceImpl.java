package com.springbootstart.service.profile;

import com.springbootstart.entity.Member;
import com.springbootstart.entity.Profile;
import com.springbootstart.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService{
    private ModelMapper modelMapper = new ModelMapper();

    private final ProfileRepository profileRepository;


    @Override
    public int insertProfile(Profile profile) {
        Profile profile1 = modelMapper.map(profile, Profile.class);
        return profileRepository.save(profile1).getPno();
    }

    @Override
    public void updateProfile(Profile profile) {
        Optional<Profile> result = profileRepository.findById((long) profile.getPno());
        Profile profile1 = result.orElseThrow();
        profile1.change(profile.getIntro(), profile.getGitLink(), profile.getBlogLink(), profile.getInstaLink());
        profileRepository.save(profile1);
    }

    @Override
    public Profile profileByMember(Member member) {
        return profileRepository.ProfileByMember(member);
    }
}
