package shop.mtcoding.projectjobplan.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import shop.mtcoding.projectjobplan.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        @Size(min = 1,max = 20, message = "제목은 20자를 초과할 수 없습니다.")
        @NotEmpty
        private String title;
        @NotEmpty
        private String field;
        @NotEmpty
        private String position;
        @NotEmpty
        private String content;
        @NotEmpty
        private String salary;
        @NotEmpty
        private String openingDate;
        @NotEmpty
        private String closingDate;

        private List<String> skill = new ArrayList<>();

        public Board toEntity(User user) {
            return Board.builder()
                    .user(user)
                    .title(title)
                    .field(field)
                    .position(position)
                    .content(content)
                    .salary(salary)
                    .openingDate(openingDate)
                    .closingDate(closingDate)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        @Size(min = 1,max = 20, message = "제목은 20자를 초과할 수 없습니다.")
        @NotEmpty
        private String title;
        @NotEmpty
        private String field;
        @NotEmpty
        private String position;
        @NotEmpty
        private String salary;
        @NotEmpty
        private String content;
        @NotEmpty
        private String openingDate;
        @NotEmpty
        private String closingDate;

        private List<String> skill = new ArrayList<>();
    }
}
