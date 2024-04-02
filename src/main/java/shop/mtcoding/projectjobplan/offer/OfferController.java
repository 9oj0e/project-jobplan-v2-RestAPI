package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.projectjobplan.user.User;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    // todo: getResumeAndBoard @GetMapping("/resumes/{resumeId}/offer-form")

    @PostMapping("/api/resumes/{resumeId}/offer")
    public String offer(@PathVariable Integer resumeId, OfferRequest.OfferDTO requestDTO) {
        offerService.createOffer(requestDTO);

        return "redirect:/resumes/" + resumeId;
    }

    @PutMapping("/api/offers")
    public String update(OfferRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.updateOffer(requestDTO);

        return "redirect:/users/" + sessionUser.getId();
    }

    @DeleteMapping("/api/offers/{offerId}")
    public String delete(@PathVariable int offerId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.removeOffer(offerId);
        return "redirect:/users/" + sessionUser.getId();
    }
}
