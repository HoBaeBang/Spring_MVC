package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제를 고려하지 않았음 실상용에는 HashMap보다는  ConcurrentHashMAp, AtomicLong 사용할것을 권장
 */

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    private static final MemberRepository instance = new MemberRepository();

    //기본생성자
    private MemberRepository() {}

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {

        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findByMember(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }


}
