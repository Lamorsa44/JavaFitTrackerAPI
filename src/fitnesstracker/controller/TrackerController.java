package fitnesstracker.controller;

import fitnesstracker.entity.data.FitnessDataResponse;
import fitnesstracker.entity.data.FitnessRequest;
import fitnesstracker.exception.UnauthorizedException;
import fitnesstracker.service.FitnessService;
import fitnesstracker.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {
    private final FitnessService fitnessService;
    private final RequestService requestService;

    public TrackerController(FitnessService fitnessService, RequestService requestService) {
        this.fitnessService = fitnessService;
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addFitnessData(@RequestBody FitnessRequest fitnessRequest,
                               @RequestHeader(value = "X-API-Key", required = false)
                               UUID apikey) {
        if (apikey == null) {
            throw new UnauthorizedException("No X-API-Key header present");
        }
        fitnessService.addFitnessData(fitnessRequest, apikey);
    }

    @GetMapping
    public List<FitnessDataResponse> listFitnessData(
            @RequestHeader(value = "X-API-Key", required = false) UUID apikey) {
        if (apikey == null) {
            throw new UnauthorizedException("No X-API-Key header present");
        }
        requestService.consumeRequest(apikey);

        return fitnessService.listFitnessDataWithApiKey(apikey).reversed().stream()
                .map(FitnessDataResponse::new).toList();
    }
}
