package shop.mtcoding.projectjobplan.board;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.projectjobplan.skill.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final EntityManager entityManager;

    private static String buildWhereClause(List<String> keywords) {
        StringJoiner whereClause = new StringJoiner(" OR ");
        for (String keyword : keywords) {
            whereClause.add("s.name = '" + keyword + "'");
        }
        return whereClause.toString();
    }

    public List<Object[]> findWithSkill(List<Skill> skills) {
        List<String> skillNameList = new ArrayList<>();
        skills.forEach(skill -> skillNameList.add(skill.getName()));
        System.out.println("skill input test : " + skillNameList);
        String queryStart = """
                SELECT b.id, b.title, b.field, u.business_name FROM
                (SELECT s.board_id, COUNT(s.name) AS name_count
                FROM skill_tb AS s
                WHERE s.board_id IS NOT NULL
                AND (""";
        String whereClause = buildWhereClause(skillNameList);
        String queryEnd = """
                )
                GROUP BY s.board_id
                ORDER BY name_count DESC) s,
                board_tb b, user_tb u
                WHERE b.id = s.board_id
                AND
                b.user_id = u.id
                """;
        String limit = " LIMIT 3";
        String query = queryStart + whereClause + queryEnd + limit;

        return entityManager.createNativeQuery(query).getResultList();
    }
}
