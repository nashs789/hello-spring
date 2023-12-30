package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원 가입
     */
    public long join(Member mem) {
        validateDuplicateMember(mem);  // 중복 회원 검증
        repository.save(mem);

        return mem.getId();
    }

    private void validateDuplicateMember(Member mem) {
        repository.findByName(mem.getName())
                  .ifPresent(m -> {
                                throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memId) {
        return repository.findById(memId);
    }
}