package hello.proxy.app.v2;

public class OrderServiceV2 {
    private final OrderRepositoryV2 orederRepository;

    public OrderServiceV2(OrderRepositoryV2 orederRepositoryV1) {
        this.orederRepository = orederRepositoryV1;
    }

    public void orderItem(String itemId) {
        orederRepository.save(itemId);
    }
}
