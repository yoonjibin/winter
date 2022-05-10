package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate (dataSource);
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query ("select*from member where id=?", memberRowMapper (), id);
        return result.stream ().findAny ();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query ("select*from member where name =?", memberRowMapper (), name);
        return result.stream ().findAny ();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query ("select * from member", memberRowMapper ());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member ();
            member.setId (rs.getLong ("id"));
            member.setName (rs.getString ("name"));
            return member;
        };
    }
}
