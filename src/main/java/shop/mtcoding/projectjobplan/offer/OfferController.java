package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.SessionUser;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    @GetMapping("/api/resumes/{resumeId}/offers")
    public ResponseEntity<?> offerForm(@PathVariable int resumeId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        OfferResponse.OfferFormDTO respDTO = offerService.getResumeAndBoard(resumeId, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/api/resumes/{resumeId}/offers")
    public ResponseEntity<?> offer(@PathVariable Integer resumeId, @RequestBody OfferRequest.OfferDTO requestDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        OfferResponse.OfferDTO offerDTO = offerService.createOffer(requestDTO);
        return ResponseEntity.ok(new ApiUtil(offerDTO));
    }

    @PutMapping("/api/users/{userId}/offers")
    public ResponseEntity<?> update(@PathVariable int userId, @RequestBody OfferRequest.UpdateDTO requestDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        OfferResponse.UpdateDTO updateDTO = offerService.updateOffer(requestDTO);

        return ResponseEntity.ok(new ApiUtil(updateDTO));
    }

    @DeleteMapping("/api/users/{userId}/offers/{offerId}")
    public ResponseEntity<?> delete(@PathVariable int userId, @PathVariable int offerId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        offerService.removeOffer(offerId);
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
