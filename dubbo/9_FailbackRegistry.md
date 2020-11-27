

public abstract class FailbackRegistry extends AbstractRegistry extends Registry


======================================================================================================

public FailbackRegistry(URL url) {
    super(url);
    int retryPeriod = url.getParameter(Constants.REGISTRY_RETRY_PERIOD_KEY, Constants.DEFAULT_REGISTRY_RETRY_PERIOD);
    this.retryFuture = retryExecutor.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {
            // Check and connect to the registry
            try {
                retry();
            } catch (Throwable t) { // Defensive fault tolerance
                logger.error("Unexpected error occur at failed retry, cause: " + t.getMessage(), t);
            }
        }
    }, retryPeriod, retryPeriod, TimeUnit.MILLISECONDS);
}


