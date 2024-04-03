package shop.mtcoding.projectjobplan.rating;

import lombok.Data;

public class RatingRequest {

    @Data // 공고 주인 평가
    public static class RateBoardUser {
        private Integer raterId;
        private Integer boardId;
        private Integer rating;
    }

    @Data // 이력서 주인 평가
    public static class RateResumeUser {
        private Integer raterId;
        private Integer resumeId;
        private Integer rating;
    }
}
