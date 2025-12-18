package fitnesstracker.controller;

import fitnesstracker.entity.user.FitUserDetails;
import fitnesstracker.entity.user.FitUserRequest;
import fitnesstracker.entity.user.FitUserResponse;
import fitnesstracker.exception.ForbiddenException;
import fitnesstracker.service.FitUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/developers")
public class FitUserController {
    private final FitUserService fitUserService;

    public FitUserController(FitUserService fitUserService) {
        this.fitUserService = fitUserService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid FitUserRequest fitUserRequest) {
        return ResponseEntity.created(URI.create("/api/developers/%d"
                .formatted(fitUserService.addFitUser(fitUserRequest).getId()))).build();
    }

    @GetMapping("/{id}")
    public FitUserResponse getFitUser(@PathVariable Long id,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof FitUserDetails details) {
            if (details.getId() != id) {
                throw new ForbiddenException("User id does not match");
            }
        }
        return new FitUserResponse(fitUserService.getUserById(id));
    }
}
