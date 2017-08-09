package cn.sxh.snowfox.view.multitype;

/**
 * Created by snow on 2017/8/10.
 */

public class ProviderNotFoundException extends RuntimeException {
    public ProviderNotFoundException(Class<?> clazz) {
        super("Do you have registered the provider for {className}.class in the adapter/pool?"
                .replace("{className}", clazz.getSimpleName()));
    }
}
