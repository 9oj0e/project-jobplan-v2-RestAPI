package shop.mtcoding.projectjobplan.resume;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class ResumeRequest {

    @Data // 이력서 작성하기
    public static class PostDTO {
        @Size(min = 1, max = 20, message = "제목은 20자를 초과할 수 없습니다.")
        @NotEmpty
        private String title;
        @NotEmpty
        private String content; // 자기소개서
        @NotEmpty
        private String career; // 경력 정보
    }

    @Data // 이력서 수정하기
    public static class UpdateDTO {
        @Size(min = 1, max = 20, message = "제목은 20자를 초과할 수 없습니다.")
        @NotEmpty
        private String title;
        @NotEmpty
        private String content;
        @NotEmpty
        private String career;
    }
}
