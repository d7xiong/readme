

finishRefresh()

1.	创建lifecycleProcessor
2.	更新lifecycleProcessor为onRefresh
3.	派发容器刷新完成事件
4.	注册LiveBeansView


===============================================================================================================


1.	清空资源缓存
	clearResourceCaches()

2.	创建lifecycleProcessor
	initLifecycleProcessor()
		beanFactory中有lifecycleProcessor，直接赋值
		this.lifecycleProcessor = beanFactory.getBean(LIFECYCLE_PROCESSOR_BEAN_NAME, LifecycleProcessor.class);

		beanFactory中无lifecycleProcessor,创建DefaultLifecycleProcessor,注册到beanFactory中
		beanFactory.registerSingleton(LIFECYCLE_PROCESSOR_BEAN_NAME, this.lifecycleProcessor);

3.	派发容器刷新完成事件
	publishEvent(new ContextRefreshedEvent(this));

4.	注册LiveBeansView
	LiveBeansView.registerApplicationContext(this);


=============================================总结==================================================================





