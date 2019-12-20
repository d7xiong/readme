

invokeBeanFactoryPostProcessors

===============================================================================================================

invokeBeanFactoryPostProcessors(beanFactory);
	PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());


===============================================================================================================

1.	如果beanFactory不是BeanDefinitionRegistry类型:执行已经注入beanFactory中的beanFactoryPostProcessors;
	如果beanFactory是BeanDefinitionRegistry类型:
		1)	先执行实现BeanDefinitionRegistryPostProcessor接口的已经注入beanFactory中的beanFactoryPostProcessors
			未实现接口的beanFactoryPostProcessor放在regularPostProcessors中，等待最后执行
		2)	再执行beanFactory中注册的所有实现BeanDefinitionRegistryPostProcessor接口的bean
			1).	先执行实现PriorityOrdered接口的bean
			2).	再执行实现Ordered接口的bean
			3). 循环执行所有的bean，直到不再产生新的bean为止
			4). 最后执行regularPostProcessors中的bean

2.	执行beanFactory中注册的所有实现BeanFactoryPostProcessor接口的bean
	1).	先执行实现PriorityOrdered接口的bean
	2).	再执行实现Ordered接口的bean
	3). 最后执行其他的bean



=============================================总结==================================================================
先执行BeanDefinitionRegistryPostProcessor.postProcessBeanFactory()
再执行BeanFactoryPostProcessor.postProcessBeanFactory()










