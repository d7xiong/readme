### com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig注解 配置与配置Bean属性绑定

## 加载流程
    dubbo-spring-boot-autoconfigure-0.2.0.jar包内META-INF/spring.factories配置spring-boot autoconfigure类路径org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration
	@EnableDubboConfig注解标注DubboAutoConfiguration.SingleDubboConfigConfiguration
	@Import(DubboConfigConfigurationSelector.class)
	Import DubboConfigConfiguration.Single.class
	@EnableDubboConfigBindings注解标注 DubboConfigConfiguration.Single.class
	@Import(DubboConfigBindingsRegistrar.class)
	DubboConfigBindingsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware
		registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry)
			DubboConfigBindingRegistrar.registerBeanDefinitions()

##EnableDubboConfigBindings
    @EnableDubboConfigBindings({
            @EnableDubboConfigBinding(prefix = "dubbo.application", type = ApplicationConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.module", type = ModuleConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.registry", type = RegistryConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.protocol", type = ProtocolConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.monitor", type = MonitorConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.provider", type = ProviderConfig.class),
            @EnableDubboConfigBinding(prefix = "dubbo.consumer", type = ConsumerConfig.class)
    })
    public static class Single {

    }

###DubboConfigBindingRegistrar

	registerDubboConfigBean(beanName, configClass, registry);
		注册每一个EnableDubboConfigBinding
	registerDubboConfigBindingBeanPostProcessor(prefix, beanName, multiple, registry);
		注册每个EnableDubboConfigBinding对象处理器DubboConfigBindingBeanPostProcessor，实现postProcessBeforeInitialization(Object bean, String beanName)，根据prefix注入值到配置bean中
		完成配置到配置bean绑定
