

registerBeanPostProcessors


创建并注册所有的BeanPostProcessor

registerBeanPostProcessors(beanFactory) -> PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);

===============================================================================================================

1.	获取所有实现接口BeanPostProcessor的bean的定义信息
	beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

2.	添加BeanPostProcessorChecker
	addBeanPostProcessor(BeanPostProcessorChecker)

3.	创建并注册所有的BeanPostProcessor
	1).	先创建并注册实现PriorityOrdered接口的bean(排除实现MergedBeanDefinitionPostProcessor接口的bean)
	2).	再创建并注册实现Ordered接口的bean(排除实现MergedBeanDefinitionPostProcessor接口的bean)
	3). 再创建并注册其他的bean(排除实现MergedBeanDefinitionPostProcessor接口的bean)
	4).	最后创建并注册实现MergedBeanDefinitionPostProcessor接口的bean

4).	添加ApplicationListenerDetector
	beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));



=============================================总结==================================================================
先注册添加BeanPostProcessorChecker
再按顺序注册BeanPostProcessor
最后注册ApplicationListenerDetector










