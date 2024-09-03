package hello.proxy.pureproxy.decorator.code;

public class Decorator implements Component{

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        return null;
    }
}
