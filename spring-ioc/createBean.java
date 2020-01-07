
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
						// Obtain a reference for early access to the specified bean
						getEarlyBeanReference(beanName, mbd, bean)						  -> Object SmartInstantiationAwareBeanPostProcessor.getEarlyBeanReference(bean, beanName)
						populateBean(beanName, mbd, instanceWrapper);
							-> boolean InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)
							autowireByName(beanName, mbd, bw, newPvs);
							autowireByType(beanName, mbd, bw, newPvs);
							// 例:@Autowired注解由AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues()实现依赖注入
							// CommonAnnotationBeanPostProcessor:逻辑一样，它处理的JSR-250的注解，比如@Resource
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
							applyBeanPostProcessorsAfterInitialization(object, beanName);		-> Object BeanPostProcessor.postProcessAfterInitialization(result, beanName)

