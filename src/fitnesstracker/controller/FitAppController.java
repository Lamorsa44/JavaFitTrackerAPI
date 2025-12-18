package fitnesstracker.controller;

import fitnesstracker.entity.fitapp.FitAppRequest;
import fitnesstracker.entity.fitapp.FitAppResponse;
import fitnesstracker.service.FitAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class FitAppController {
    private final FitAppService fitAppService;

    public FitAppController(FitAppService fitAppService) {
        this.fitAppService = fitAppService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public FitAppResponse registerApp(@RequestBody @Valid FitAppRequest fitAppRequest,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        return new FitAppResponse(fitAppService.registerApp(fitAppRequest, userDetails));
    }
}
