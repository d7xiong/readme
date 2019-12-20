com.alibaba.dubbo.config.spring.ServiceBean<T> extends ServiceConfig<T>

    doExport() -> diExportUrls() -> doExportUrlsFor1Protocol(ProtocolConfig protocolConfig, List<URL> registryURLs)

    loadConfigs -> loadRegistries -> export

    URL url = new URL(protocolName, host, port, (contextPath == null || contextPath.length() == 0 ? "" : contextPath + "/") + path, map);

    Invoker<?> invoker = proxyFactory.getInvoker(ref, (Class) interfaceClass, registryURL.addParameterAndEncoded(Constants.EXPORT_KEY, url.toFullString,DelegateProviderMetaDataInvoker wrapperInvoker = new DelegateProviderMetaDataInvoker(invoker, this);

    Exporter<?> exporter = protocol.export(wrapperInvoker);

        String key = serviceKey(url);
        DubboExporter<T> exporter = new DubboExporter<T>(invoker, key, exporterMap);
        exporterMap.put(key, exporter);
        //
        openServer(url);
            createServer()
                Exchangers.bind(url, requestHandler)    requestHandler -> DubboProtocol
                HeaderExchanger.bind(url, new DecodeHandler(new HeaderExchangeHandler(handler))))
                NettyTransporter.bind(url, channelHandler)
                    new NettyServer(url, channelHandler)
                        ChannelHandlers.wrap(handler, ExecutorUtil.setThreadName(url, SERVER_THREAD_POOL_NAME)))
                        ChannelHandlers.getInstance().wrapInternal(handler, url);
                        // SPI获取Server Dispatcher
                        new MultiMessageHandler(new HeartbeatHandler(ExtensionLoader.getExtensionLoader(Dispatcher.class).getAdaptiveExtension().dispatch(handler, url)));
        optimizeSerialization(url);



