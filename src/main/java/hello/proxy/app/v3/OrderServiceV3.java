package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {
    private final OrderRepositoryV3 orederRepository;

    public OrderServiceV3(OrderRepositoryV3 orederRepositoryV1) {
        this.orederRepository = orederRepositoryV1;
    }

    public void orderItem(String itemId) {
        orederRepository.save(itemId);
    }
}
