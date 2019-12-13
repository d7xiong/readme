

initApplicationEventMulticaster

创建事件派发器


===============================================================================================================

1.	判断容器内是否有名称为applicationEventMulticaster的bean

2.	容器内有applicationEventMulticaster
	赋值给本地applicationEventMulticaster
	this.applicationEventMulticaster=beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);

3.	容器内没有applicationEventMulticaster
	创建一个默认派发器到容器中
	this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
	beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);



=============================================总结==================================================================
beanFactory中有，就使用beanFactory中的applicationEventMulticaster
没有就创建一个SimpleApplicationEventMulticaster,加入到beanFactory中





