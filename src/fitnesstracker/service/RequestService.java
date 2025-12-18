package fitnesstracker.service;

import fitnesstracker.entity.fitapp.FitAppCategory;
import fitnesstracker.exception.TooManyRequestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RequestService {
    private final FitAppService fitAppService;
    private final Set<Bucket> apiRequests = new ConcurrentSkipListSet<>();

    public RequestService(FitAppService fitAppService) {
        this.fitAppService = fitAppService;
    }

    public void consumeRequest(UUID apikey) {
        if (fitAppService.getFitApp(apikey)
                .getCategory().equals(FitAppCategory.PREMIUM)) {
            return;
        }
        
        apiRequests.stream()
                .filter(bucket -> bucket.apikey().equals(apikey))
                .findFirst().ifPresentOrElse(Bucket::consume,
                        () -> apiRequests.add(new Bucket(apikey).consume()));
    }

    @Scheduled(fixedRate = 1000)
    public void resetApiRequests() {
        apiRequests.forEach(Bucket::resetRequest);
    }

    public record Bucket(UUID apikey, AtomicLong requestLeft) implements Comparable<Bucket> {

        public Bucket(UUID apikey) {
            this(apikey, new AtomicLong(1));
        }

        public Bucket consume() {
            if (requestLeft.get() == 0) {
                throw new TooManyRequestException();
            }
            requestLeft.decrementAndGet();
            return this;
        }

        public void resetRequest() {
            requestLeft.set(1);
        }

        @Override
        public int compareTo(Bucket o) {
            return apikey.compareTo(o.apikey);
        }
    }
}
