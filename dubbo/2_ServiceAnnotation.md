
com.alibaba.dubbo.config.annotation.Service注解Bean注册及服务暴露:

    1. dubbo-spring-boot-autoconfigure-0.2.0.jar包内META-INF/spring.factories配置spring-boot autoconfigure类路径

           org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration

    2. 自动配置类DubboAutoConfiguration中创建@Service注解后置处理器@bean:ServiceAnnotationBeanPostProcessor

    3. ServiceAnnotationBeanPostProcessor实现接口BeanDefinitionRegistryPostProcessor

           实现接口方法postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)完成@Service注解Bean的扫描及@Service BeanDefinition注册(Spring容器首先加载BeanDefinition，再通过BeanDefinition创建实例bean)

           方法注册两个beanDefination,分别为spring普通BeanDefinition和dubbo BeanDefinition，两者命名规则不同,注册类不同

           bean命名规则：普通bean名称由AnnotationBeanNameGenerator生成，dubbo bean名称生成规则为:ServiceBean.class.getSimpleName():annotatedServiceBeanName:interfaceClass.getName():interfaceClassName:service.version():service.group

           dubbo beanDefiniton:注册ServiceBean.class, propertyValues中ref指向之前注册的普通bean,interface指向之前普通bean实现的接口

    4. ServiceBean实现ApplicationListener<ContextRefreshedEvent>接口，监听ContextRefreshedEvent事件
           在onApplicationEvent(ContextRefreshedEvent event)中调用export()方法，执行服务暴露



