

Dubbo ExtensionLoader

=======================================================================================

使用事例
com.alibaba.dubbo.config.ServiceConfig<T>
ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();


=======================================================================================

//创建ExtensionLoader
ExtensionLoader.getExtensionLoader(Class<T> type)
	new ExtensionLoader<T>(type)
		ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension())
创建SPI Adaptive
ExtensionLoader.getAdaptiveExtension()
	createAdaptiveExtension();
		getAdaptiveExtensionClass().newInstance();
			createAdaptiveExtensionClass()
				createAdaptiveExtensionClassCode();	-> crente class named type.getSimpleName()$Adaptive
				findClassLoader()
				ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.compiler.Compiler.class).getAdaptiveExtension();
				compiler.compile(code, classLoader);

		injectExtension(T instance)	-> call setters method


// 获取扩展点对象
ExtensionLoader.getExtension(String name)
	createExtension(name);
		//获取传入的扩展点实现类字节码
		getExtensionClasses().get(name);
			loadExtensionClasses();
				loadDirectory(extensionClasses, DUBBO_INTERNAL_DIRECTORY);	-> internal/
		        loadDirectory(extensionClasses, DUBBO_DIRECTORY);			-> META-INF/dubbo/
		        loadDirectory(extensionClasses, SERVICES_DIRECTORY);		-> META-INF/services/
		        	classLoader.getResources(dir + type.getName())
		injectExtension(instance);
		// 如果有包装类，循环创建包装类返回 QosProtocolWrapper,ProtocolListenerWrapper,ProtocolFilterWrapper
		/**	QosProtocolWrapper
			if "registry".equals(invoker.getUrl().getProtocol()) startQosServer(url);
			protocol.refer(type, url);
		*/
		/** ProtocolListenerWrapper
			if "registry".equals(invoker.getUrl().getProtocol()) return protocol.refer(type, url);
			return new ListenerInvokerWrapper<T>(protocol.refer(type, url),Collections.unmodifiableList(ExtensionLoader.getExtensionLoader(InvokerListener.class).getActivateExtension(url, Constants.INVOKER_LISTENER_KEY)));
		*/
		/** ProtocolFilterWrapper
			if "registry".equals(invoker.getUrl().getProtocol()) return protocol.refer(type, url);
			return protocol.export(buildInvokerChain(invoker, Constants.SERVICE_FILTER_KEY, Constants.PROVIDER));
		*/
		injectExtension((T) wrapperClass.getConstructor(type).newInstance(instance));

		

================================ProxyFactory$Adaptive.java by createAdaptiveExtensionClassCode()============================

----> String extName = url.getParameter( "proxy", "javassist" );
----> getExtension(extName)

===================================================================================

package com.alibaba.dubbo.rpc;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
public class ProxyFactory$Adaptive implements com.alibaba.dubbo.rpc.ProxyFactory {
	public java.lang.Object getProxy( com.alibaba.dubbo.rpc.Invoker arg0 ) throws com.alibaba.dubbo.rpc.RpcException
	{
		if ( arg0 == null )
			throw new IllegalArgumentException( "com.alibaba.dubbo.rpc.Invoker argument == null" );
		if ( arg0.getUrl() == null )
			throw new IllegalArgumentException( "com.alibaba.dubbo.rpc.Invoker argument getUrl() == null" );
		com.alibaba.dubbo.common.URL	url	= arg0.getUrl();
		String extName = url.getParameter( "proxy", "javassist" );
		if ( extName == null )
			throw new IllegalStateException( "Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])" );
		com.alibaba.dubbo.rpc.ProxyFactory extension = (com.alibaba.dubbo.rpc.ProxyFactory)ExtensionLoader.getExtensionLoader( com.alibaba.dubbo.rpc.ProxyFactory.class ).getExtension( extName );
		return(extension.getProxy( arg0 ) );
	}


	public com.alibaba.dubbo.rpc.Invoker getInvoker( java.lang.Object arg0, java.lang.Class arg1, com.alibaba.dubbo.common.URL arg2 ) throws com.alibaba.dubbo.rpc.RpcException
	{
		if ( arg2 == null )
			throw new IllegalArgumentException( "url == null" );
		com.alibaba.dubbo.common.URL	url	= arg2;
		String extName = url.getParameter( "proxy", "javassist" );
		if ( extName == null )
			throw new IllegalStateException( "Fail to get extension(com.alibaba.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])" );
		com.alibaba.dubbo.rpc.ProxyFactory extension = (com.alibaba.dubbo.rpc.ProxyFactory)ExtensionLoader.getExtensionLoader( com.alibaba.dubbo.rpc.ProxyFactory.class ).getExtension( extName );
		return(extension.getInvoker( arg0, arg1, arg2 ) );
	}
}
