com.alibaba.dubbo.config.spring.ReferenceBean<T> extends ServiceConfig<T> implements FactoryBean, ApplicationContextAware, InitializingBean, DisposableBean

    afterPropertiesSet()
        setConfigs()
        getObject()
            get()
            init()



====================================init()===================================================================

createProxy(Map<String, String> map)
    // SPI获取Protocol
    ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension().refer(interfaceClass, url)
    	getClients(url)
    		initClient(url);
    		Exchangers.connect(url, requestHandler); 										requestHandler -> DubboProtocol
    		ExtensionLoader.getExtensionLoader(Exchanger.class).getExtension(url.getParameter("exchanger", "header")).connect(url, handler);
    			new HeaderExchangeClient(Transporters.connect(url, new DecodeHandler(new HeaderExchangeHandler(handler))), true);  handler -> DecodeHandler
    				ExtensionLoader.getExtensionLoader(Transporter.class).getAdaptiveExtension()..connect(url, handler)
    				new NettyClient(url, listener)
    					ChannelHandlers.wrap(handler, url);
    						ChannelHandlers.getInstance().wrapInternal(handler, url);
    							// SPI获取 Dispatcher
    							new MultiMessageHandler(new HeartbeatHandler(ExtensionLoader.getExtensionLoader(Dispatcher.class)
                .getAdaptiveExtension().dispatch(handler, url)));

    ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension().getProxy(invoker)
    # Proxy.getProxy内生成代理类和代理对象, 生成内容为代码在DubboInvokerProxy.java
        (T) Proxy.getProxy(interfaces).newInstance(new InvokerInvocationHandler(invoker));






