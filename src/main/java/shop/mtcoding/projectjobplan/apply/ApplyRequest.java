package shop.mtcoding.projectjobplan.apply;

import lombok.Data;

public class ApplyRequest {
    @Data
    public static class ApplyDTO {
        private Integer resumeId;
        private Integer boardId;
        // toEntity 가능?
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private Boolean status;
    }
}
