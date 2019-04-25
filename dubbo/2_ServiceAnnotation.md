
com.alibaba.dubbo.config.annotation.@Service注解bean注册流程:

1. springboot:

    自动配置类DubboAutoConfiguration中创建@service注解后置处理器@bean:ServiceAnnotationBeanPostProcessor
    ServiceAnnotationBeanPostProcessor实现接口BeanDefinitionRegistryPostProcessor，实现方法postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)扫描配置目录下，所有@Service注解标注的Java类，分别创建spring普通BeanDefinition和dubbo BeanDefinition
    区别为：普通bean名称由AnnotationBeanNameGenerator生成，dubbo bean名称生成规则为:ServiceBean.class.getSimpleName():annotatedServiceBeanName:interfaceClass.getName():interfaceClassName:service.version():service.group



