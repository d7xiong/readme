com.alibaba.dubbo.config.spring.ServiceBean<T> extends ServiceConfig<T>

doExport() -> diExportUrls() -> doExportUrlsFor1Protocol(ProtocolConfig protocolConfig, List<URL> registryURLs)

    loadConfigs -> loadRegistries -> export

    URL url = new URL(protocolName, host, port, (contextPath == null || contextPath.length() == 0 ? "" : contextPath + "/") + path, map);

    Invoker<?> invoker = proxyFactory.getInvoker(ref, (Class) interfaceClass, registryURL.addParameterAndEncoded(Constants.EXPORT_KEY, url.toFullString,DelegateProviderMetaDataInvoker wrapperInvoker = new DelegateProviderMetaDataInvoker(invoker, this);

    Exporter<?> exporter = protocol.export(wrapperInvoker);

        String key = serviceKey(url);
        DubboExporter<T> exporter = new DubboExporter<T>(invoker, key, exporterMap);
        exporterMap.put(key, exporter);
        openServer(url);
        optimizeSerialization(url);



