com.alibaba.dubbo.remoting.transport.netty.NettyClient


ChannelHandlers.getInstance().wrapInternal(handler, url);

return new MultiMessageHandler(new HeartbeatHandler(ExtensionLoader.getExtensionLoader(Dispatcher.class)
                .getAdaptiveExtension().dispatch(handler, url)));


=============================================总结==================================================================


SPI Dispatcher.class 获取指定的派发器，默认为AllDispatcher -> AllChannelHandler
NettyClient收到数据调用Dispatcher.received(channel, message)实现任务派发





