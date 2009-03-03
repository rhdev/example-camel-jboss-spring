package codeandtell.camel.jboss.spring;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import codeandtell.camel.jboss.JbossResolverUtil;
import codeandtell.camel.jboss.JbossTypeConverter;

import org.apache.log4j.Logger;

import org.apache.camel.builder.ErrorHandlerBuilder;
import org.apache.camel.util.ResolverUtil;
import org.apache.camel.spring.SpringCamelContext;

@XmlRootElement(name = "camelContext")
@XmlAccessorType(XmlAccessType.FIELD)
public class CamelContextFactoryBean extends org.apache.camel.spring.CamelContextFactoryBean {

    private final static Logger log = Logger.getLogger(CamelContextFactoryBean.class);

    /**
     * The factory method to create the ResolverUtil
     * @return a new instance of ResolverUtil
     */
    @Override
    protected ResolverUtil createResolverUtil() {
        // return vfs aware resolver
        return new JbossResolverUtil();
    }

    @Override
    protected SpringCamelContext createContext() {
        // Cribbed from camel-spring
        SpringCamelContext ctx = super.createContext();
        ctx.setTypeConverter(new JbossTypeConverter(ctx.getInjector()));
        return ctx;
    }
}
