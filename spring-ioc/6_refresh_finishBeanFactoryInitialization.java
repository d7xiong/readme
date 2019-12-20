

finishBeanFactoryInitialization(beanFactory) <- DefaultListableBeanFactory.class

1.	完成BeanFactory创建工作
2.	创建剩余的所有单实例Bean



===============================DefaultListableBeanFactory 继承关系==============================================

DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
extends FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry extends SimpleAliasRegistry

===============================================================================================================

1.	如果beanFactory中存在名称为conversionService的bean，向beanFactory中添加conversion service

2.	如果beanFactory中不存在字符串表达式解析器(原文是embedded value resolver, 此处理解为表达式解析器)
	beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));

3.	创建实现LoadTimeWeaverAware接口的所有bean

4.	冻结配置(冻结所有beanDefinition metadata)
	configurationFrozen=true

5.	创建剩余的所有非懒加载的单实例Bean
	beanFactory.preInstantiateSingletons();


preInstantiateSingletons()
	if(isFactoryBean(beanName))
		isEagerInit = (factory instanceof SmartFactoryBean &&((SmartFactoryBean<?>) factory).isEagerInit()) -> SmartFactoryBean.isEagerInit()
		if(isEagerInit)
			getBean(beanName)
	else
		getBean(beanName)
	// 执行SmartInitializingSingleton.afterSingletonsInstantiated()
	bean = getSingleton(beanName)
	bean instanceof SmartInitializingSingleton
		bean..afterSingletonsInstantiated()											-> SmartInitializingSingleton.afterSingletonsInstantiated()

=============================================创建bean流程==================================================================

	getBean(beanName)
		doGetBean(beanName)
			//Eagerly check singleton cache for manually registered singletons.
			//DefaultSingletonBeanRegistry.class
			getSingleton(beanName)
			getSingleton(beanName, objectFactory)
				//AbstractAutowireCapableBeanFactory.class
				createBean(beanName, mbd, args)
					//Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
					----> 此方法内如果返回一个bean实例，将不再走createBean内下方方法
					resolveBeforeInstantiation(beanName, mbd)
						applyBeanPostProcessorsBeforeInstantiation(beanClass, beanName) -> Object InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation(beanClass, beanName)
						// 此方法内postProcessAfterInitialization返回null对象会导致此方法返回null
						applyBeanPostProcessorsAfterInitialization(existingBean, beanName) -> void BeanPostProcessor.postProcessAfterInitialization(bean, beanName)
					doCreateBean(beanName, mbdToUse, args)
						createBeanInstance(beanName, mbd, args);
							//创建一个BeanWrapper包装的bean实例beanInstance
							new BeanWrapperImpl(beanInstance);
						applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName); -> void MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition(beanDefinition, beanType, beanName)
						populateBean(beanName, mbd, instanceWrapper);
							// 默认返回true，返回false不再执行populateBean后续方法
							// Give any InstantiationAwareBeanPostProcessors the opportunity to modify the
							// state of the bean before properties are set. This can be used, for example,
							// to support styles of field injection.
							-> boolean InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)
							autowireByName(beanName, mbd, bw, newPvs);
							autowireByType(beanName, mbd, bw, newPvs);
							// 例:@Autowired注解由AutowiredAnnotationBeanPostProcessor.postProcessProperties()实现依赖注入
							// CommonAnnotationBeanPostProcessor:逻辑一样，它处理的JSR-250的注解，比如@Resource
							-> PropertyValues InstantiationAwareBeanPostProcessor.postProcessProperties(propertyValues, bean, beanName)
							-> PropertyValues InstantiationAwareBeanPostProcessor.postProcessPropertyValues(pvs,filteredPds, beanInstance, beanName);
							applyPropertyValues(beanName, mbd, bw, pvs)

						initializeBean(beanName, exposedObject, mbd);
							invokeAwareMethods(beanName, bean);	-> BeanNameAware,BeanClassLoaderAware,BeanFactoryAware
							// 此方法内如果postProcessBeforeInitialization返回null，会直接导致方法返回null
							applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName); -> Object BeanPostProcessor.postProcessBeforeInitialization(result, beanName)
							invokeInitMethods(beanName, wrappedBean, mbd);
								((InitializingBean) bean).afterPropertiesSet();
								invokeCustomInitMethod(beanName, bean, mbd);
							// 此方法内postProcessAfterInitialization返回null对象会导致此方法返回null
							applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);	-> Object BeanPostProcessor.postProcessAfterInitialization(result, beanName)
						// AbstractBeanFactory.class
						registerDisposableBeanIfNecessary(beanName, bean, mbd);

			// beanName带&返回beanInstance,此时beanInstance为FactoryBean
			// beanName不带&, 如果beanInstance是FactoryBean,返回beanInstance.getObject()
			// beanName不带&, 如果beanInstance不是FactoryBean, 返回beanInstance
			// 总结:此方法根据传入的name是否有&判断返回FactoryBean还是bean实际对象
			// AbstractBeanFactory.class
			getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
				//FactoryBeanRegistrySupport.class
				super.getObjectForBeanInstance(beanInstance, name, beanName, mbd);
					if BeanFactoryUtils.isFactoryDereference(name)
						return beanInstance
					if !(beanInstance instanceof FactoryBean)
						return beanInstance;
					//beanInstance是FactoryBean需要返回实际bean，但是实际bean还未创建
					getObjectFromFactoryBean(factoryBean, beanName, !synthetic)
						doGetObjectFromFactoryBean(factoryBean, beanName)			-> FactoryBean.getObject();
							factoryBean.getObject();
						postProcessObjectFromFactoryBean(object, beanName)
							// 此方法内postProcessAfterInitialization返回null对象会导致此方法返回null
							applyBeanPostProcessorsAfterInitialization(object, beanName);		-> BeanPostProcessor.postProcessAfterInitialization(result, beanName)





=============================================总结==================================================================



































