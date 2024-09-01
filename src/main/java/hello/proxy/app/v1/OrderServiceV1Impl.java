package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderRepositoryV1 orederRepository;

    public OrderServiceV1Impl(OrderRepositoryV1 orederRepositoryV1) {
        this.orederRepository = orederRepositoryV1;
    }

    @Override
    public void orderItem(String itemId) {
        orederRepository.save(itemId);
    }
}
