package shop.mtcoding.projectjobplan.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.projectjobplan.user.User;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final HttpSession session;
    private final OfferService offerService;

    @GetMapping("/resumes/{resumeId}/offer-form")
    public String offerForm(@PathVariable int resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        OfferResponse.OfferFormDTO responseDTO = offerService.getResumeAndBoard(resumeId, sessionUser);
        request.setAttribute("offerForm", responseDTO);

        return "offer/offer-form";
    }

    @PostMapping("/resumes/{resumeId}/offer")
    public String offer(@PathVariable Integer resumeId, OfferRequest.OfferDTO requestDTO) {
        offerService.createOffer(requestDTO);

        return "redirect:/resumes/" + resumeId;
    }

    @PostMapping("/offer/update")
    public String update(OfferRequest.UpdateDTO requestDTO) { // 제안 받기
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.updateOffer(requestDTO);

        return "redirect:/users/" + sessionUser.getId();
    }

    @PostMapping("/offer/{offerId}/delete")
    public String delete(@PathVariable int offerId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        offerService.removeOffer(offerId);
        return "redirect:/users/" + sessionUser.getId();
    }
}
