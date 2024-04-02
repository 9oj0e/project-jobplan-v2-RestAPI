package shop.mtcoding.projectjobplan.offer;

import lombok.Data;

public class OfferRequest {
    @Data
    public static class OfferDTO {
        private Integer resumeId;
        private Integer boardId;
    }

    @Data
    public static class UpdateDTO {
        private Integer id;
        private Boolean status;
    }
}
