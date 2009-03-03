package codeandtell.camel.jboss.spring;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.xml.ParserContext;

import org.w3c.dom.Element;

import org.apache.log4j.Logger;

public class CamelNamespaceHandler extends org.apache.camel.spring.handler.CamelNamespaceHandler {

    private final static Logger log = Logger.getLogger(CamelNamespaceHandler.class);

    public void init() {
        super.init();
        registerParser("camelContext", new CamelContextBeanDefinitionParser(CamelContextFactoryBean.class));
    }

    @Override
    protected Object parseUsingJaxb(Element element, ParserContext parserContext) {
        Object obj = super.parseUsingJaxb(element, parserContext);
        return obj;
    }

    @Override
    protected JAXBContext createJaxbContext() throws JAXBException {
        StringBuilder packages = new StringBuilder();
        for (Class cl : getJaxbPackages()) {
            if (packages.length() > 0) {
                packages.append(":");
            }
            packages.append(cl.getName().substring(0, cl.getName().lastIndexOf('.')));
        }
        return JAXBContext.newInstance(packages.toString(), getClass().getClassLoader());
    }

    @Override
    protected Set<Class> getJaxbPackages() {
        Set<Class> classes = new HashSet<Class>();
        classes.add(codeandtell.camel.jboss.spring.CamelContextFactoryBean.class);
        classes.add(org.apache.camel.spring.CamelContextFactoryBean.class);
        classes.add(org.apache.camel.model.RouteType.class);
        classes.add(org.apache.camel.model.config.StreamResequencerConfig.class);
        classes.add(org.apache.camel.model.dataformat.DataFormatType.class);
        classes.add(org.apache.camel.model.language.ExpressionType.class);
        classes.add(org.apache.camel.model.loadbalancer.LoadBalancerType.class);
        return classes;
    }
}
