package shop.mtcoding.projectjobplan.offer;

import lombok.Data;

public class OfferRequest {
    @Data // 제안 하기
    public static class OfferDTO {
        private Integer resumeId;
        private Integer boardId;
    }

    @Data // 제안 수락 및 거절
    public static class UpdateDTO {
        private Integer id;
        private Boolean status;
    }
}
