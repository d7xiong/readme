
com.alibaba.dubbo.config.annotation.Reference注解依赖注入:

    1. dubbo-spring-boot-autoconfigure-0.2.0.jar包内META-INF/spring.factories配置spring-boot autoconfigure类路径

           org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration

    2. 自动配置类DubboAutoConfiguration中创建@Reference注解后置处理器@bean:ReferenceAnnotationBeanPostProcessor

    3. ReferenceAnnotationBeanPostProcessor实现接口MergedBeanDefinitionPostProcessor
            实现接口方法postProcessMergedBeanDefinition(beanDefinition, beanType, beanName)在Bean创建前查找bean内@Reference依赖添加到beanDefinition中
            postProcessMergedBeanDefinition.postProcessMergedBeanDefinition(beanDefinition, beanType, beanName);


            实现接口方法postProcessMergedBeanDefinition.postProcessPropertyValues()在bean实例创建后注@Reference依赖的Bean
            实际注入的对象为ReferenceBean.getObject()
            postProcessPropertyValues()
                InjectionMetadata.inject()
                InjectedElement.inject()
                    buildReferenceBean()
                    ReferenceBeanBuilder.build()
                    doBuild() -> new ReferenceBean<Object>();
                    configureBean(bean);
                       preConfigureBean(annotation, bean);
                       configureRegistryConfigs(bean);
                       configureMonitorConfig(bean);
                       configureApplicationConfig(bean);
                       configureModuleConfig(bean);
                       postConfigureBean(annotation, bean);
                           ReferenceBean.afterPropertiesSet();
                               getObject()
                               get()
                               创建代理对象实例
                               init()
                field.set(bean, referenceBean.getObject());







# Bean实例创建后注入@Reference依赖的Bean
# 实际为根据接口创建代理类ProxyInstance
postProcessMergedBeanDefinition.postProcessPropertyValues()

