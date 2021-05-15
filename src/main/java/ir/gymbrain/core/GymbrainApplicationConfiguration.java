package ir.gymbrain.core;

import java.util.List;

public class GymbrainApplicationConfiguration {
    private List<String> controllerBasePackages;

    public List<String> getControllerBasePackages() {
        return controllerBasePackages;
    }

    public GymbrainApplicationConfiguration setControllerBasePackages(List<String> controllerBasePackages) {
        this.controllerBasePackages = controllerBasePackages;
        return this;
    }
}
