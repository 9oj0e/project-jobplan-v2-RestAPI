package shop.mtcoding.projectjobplan.rating;

import lombok.Data;

public class RatingRequest {

    @Data
    public static class RateBoardUser {
        private Integer raterId;
        private Integer boardId;
        private Integer rating;
    }

    @Data
    public static class RateResumeUser {
        private Integer raterId;
        private Integer resumeId;
        private Integer rating;
    }
}
