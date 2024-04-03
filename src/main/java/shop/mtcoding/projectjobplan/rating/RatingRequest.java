package shop.mtcoding.projectjobplan.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

public class RatingRequest {

    @Data // 공고 주인 평가
    public static class RateBoardUser {
        private Integer raterId;
        private Integer boardId;
        @NotEmpty
        @Min(1)
        @Max(5)
        private int rating;
    }

    @Data // 이력서 주인 평가
    public static class RateResumeUser {
        private Integer raterId;
        private Integer resumeId;
        @Min(1)
        @Max(5)
        private int rating;
    }
}
