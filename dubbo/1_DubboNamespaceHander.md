
DubboNamespaceHandler 继承自 NamespaceHandlerSupport 实现自定义标签解析支持

init()方法内注册dubbo注解解析器:

    registerBeanDefinitionParser("application", new DubboBeanDefinitionParser(ApplicationConfig.class, true))
    registerBeanDefinitionParser("module", new DubboBeanDefinitionParser(ModuleConfig.class, true));
    registerBeanDefinitionParser("registry", new DubboBeanDefinitionParser(RegistryConfig.class, true));
	registerBeanDefinitionParser("monitor", new DubboBeanDefinitionParser(MonitorConfig.class, true));
	registerBeanDefinitionParser("provider", new DubboBeanDefinitionParser(ProviderConfig.class, true));
	registerBeanDefinitionParser("consumer", new DubboBeanDefinitionParser(ConsumerConfig.class, true));
	registerBeanDefinitionParser("protocol", new DubboBeanDefinitionParser(ProtocolConfig.class, true));
	registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(ServiceBean.class, true));
	registerBeanDefinitionParser("reference", new DubboBeanDefinitionParser(ReferenceBean.class, false));
	registerBeanDefinitionParser("annotation", new AnnotationBeanDefinitionParser());
