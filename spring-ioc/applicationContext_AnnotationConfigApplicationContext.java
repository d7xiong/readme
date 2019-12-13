
public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry, Environment environment) {
this.reader = new AnnotatedBeanDefinitionReader(this);
    AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
    register ConfigurationClassPostProcessor.class
    register AutowiredAnnotationBeanPostProcessor.class
    register CommonAnnotationBeanPostProcessor.class
    register EventListenerMethodProcessor.class
    register DefaultEventListenerFactory.class
    register org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor

}
