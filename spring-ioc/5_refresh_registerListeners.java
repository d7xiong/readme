

registerListeners

注册事件监听器


===============================================================================================================

1.	先获取beanFactory中的applicationListeners(beanFactory的一个属性,Set<ApplicationListener>集合)
	添加到事件派发器中
	getApplicationEventMulticaster().addApplicationListener(listener);

2.	获取所有实现接口ApplicationListener的bean的定义信息
	String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);

3.	向派发器中注册所有的事件监听器
	getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);

4.	派发早期事件
	getApplicationEventMulticaster().multicastEvent(earlyEvent);

=============================================总结==================================================================

先添加beanFactory中的监听器到派发器中
再添加实现ApplicationListener接口的bean到派发器中
最后派发早期事件



