

====================================ProxyInstance class===========================

package com.alibaba.dubbo.common.bytecode

// proxyInterfaces 为需要代理的接口
class com.alibaba.dubbo.common.bytecode.proxy0 implements proxyInterfaces,EchoService {
	private java.lang.reflect.InvocationHandler handler;

	public <init>(){}

	public <init>(java.lang.reflect.InvocationHandler arg0){
		handler=$1;
	}

	ReturnType interfaceName(interfaceParams){
		Object[] args = new Object[interfaceParams.length];
		args[0] = interfaceParam_1;
		args[...] = interfaceParam_...;
		Object ret = handler.invoke(this, methods[0], args);
		return (ReturnType)ret;
	}
}



====================================Proxy class===========================


public Object newInstance(java.lang.reflect.InvocationHandler h){
	return new com.alibaba.dubbo.common.bytecode.Proxy0(h);
}




