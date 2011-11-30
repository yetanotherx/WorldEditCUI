package wecui;

import wecui.exception.InitializationException;

public interface InitializationFactory {
    
    public void initialize() throws InitializationException;
    
}
