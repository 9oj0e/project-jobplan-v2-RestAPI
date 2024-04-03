package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan._core.utils.ApiUtil;
import shop.mtcoding.projectjobplan.user.User;

@RestController
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    @GetMapping("/api/resumes/{resumeId}/offers")
    public ResponseEntity<?> offerForm(@PathVariable int resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferFormDTO respDTO = offerService.getResumeAndBoard(resumeId, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @PostMapping("/api/resumes/{resumeId}/offers")
    public ResponseEntity<?> offer(@PathVariable Integer resumeId, @RequestBody OfferRequest.OfferDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferDTO offerDTO= offerService.createOffer(requestDTO);
        return ResponseEntity.ok(new ApiUtil(offerDTO));
    }

    @PutMapping("/api/offers")
    public ResponseEntity<?> update(@RequestBody OfferRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.UpdateDTO updateDTO = offerService.updateOffer(requestDTO);

        return ResponseEntity.ok(new ApiUtil(updateDTO));
    }

    @DeleteMapping("/api/offers/{offerId}")
    public ResponseEntity<?> delete(@PathVariable int offerId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.removeOffer(offerId);
        return ResponseEntity.ok(new ApiUtil(null));
    }
}
