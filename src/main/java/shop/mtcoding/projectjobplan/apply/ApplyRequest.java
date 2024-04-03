package shop.mtcoding.projectjobplan.apply;

import lombok.Data;

public class ApplyRequest {
    @Data // 이력서로 공고에 지원하기
    public static class ApplyDTO {
        private Integer resumeId;
        private Integer boardId;
    }

    @Data // applyId 찾아서 상태 변경
    public static class UpdateDTO {
        private Integer id;
        private Boolean status;
    }
}
